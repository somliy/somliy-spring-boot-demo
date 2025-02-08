package top.somliy.localcache.test;

import cn.hutool.extra.servlet.ServletUtil;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.somliy.currentlimiting.annotation.Limit;
import top.somliy.currentlimiting.base.exception.CurrentLimitingException;
import top.somliy.currentlimiting.base.result.ResultCodeEnum;
import top.somliy.currentlimiting.enums.LimitType;
import top.somliy.currentlimiting.util.LuaUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;


/**
 * 类名： @ClassName LimitInterceptor 限流实现
 * 创建人：@author zhao dong
 * 类描述：@Description: 限流实现
 * 创建时间: 2023/7/25 17:42
 */
@Aspect
@Configuration
public class LimitInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LimitInterceptor.class);
    @Autowired
    private RedisTemplate<String, Serializable> limitRedisTemplate;

    /**
     * 处理器
     *
     * @param pjp 切面
     * @return 结果
     */
    @Around("execution(public * *(..)) && @annotation(top.somliy.currentlimiting.annotation.Limit)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Limit limitAnnotation = method.getAnnotation(Limit.class);
        LimitType limitType = limitAnnotation.limitType();
        String name = limitAnnotation.name();
        String key = "limit:";
        int limitPeriod = limitAnnotation.period();
        int limitCount = limitAnnotation.count();

        // 根据限流类型获取不同的key ,如果不传我们会以方法名作为key
        switch (limitType) {
            case IP:
                key += "ip_limit_" + getIpAddress();
                break;
            case PORT:
                key += "port_limit_" + limitAnnotation.key();
                break;
            default:
                key += "method_limit_" + StringUtils.upperCase(method.getName());
        }
        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(limitAnnotation.prefix(), key));
        // 获取lua表达式
        String luaScript = LuaUtil.buildLuaLimitScript();
        RedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript, Long.class);
        Long count = limitRedisTemplate.execute(redisScript, keys, limitCount, limitPeriod);
        logger.info("Access try count is {} for name={} and key = {}", count, name, key);
        if (count != null && count.intValue() <= limitCount) {
            try {
                return pjp.proceed();
            } catch (Throwable e) {
                throw new CurrentLimitingException(ResultCodeEnum.CURRENT_LIMITING_ERROR);
            }
        } else {
            throw new CurrentLimitingException(ResultCodeEnum.CURRENT_LIMITING_NUM_ERROR);
        }
    }

    /**
     * 使用hutool 获取到Ip
     *
     * @return Ip
     */
    public String getIpAddress() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return ServletUtil.getClientIP(request);
    }
}