package top.somliy.localcache.base.exception.global;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.somliy.localcache.base.result.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * 类名： @ClassName GlobalExceptionHandler 全局异常处理类
 * 创建人：@author zhao dong
 * 类描述：@Description: 全局异常处理类
 * 创建时间: 2023/8/7 16:49
 */
@Order(1)
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 用于标记需要处理的异常类型
     *
     * @param e 异常
     * @return 返回值
     */
    @ExceptionHandler(Exception.class)
    public Result<?> exceptionHandler(Exception e) {
        logger.error("全局异常处理:{}", e.getMessage(), e);
        return Result.error("系统异常，请稍后再试！");
    }

    /**
     * 异常信息打印
     *
     * @param request 请求头
     * @param result  结果
     * @param e       异常
     * @return 结果
     */
    private Result<?> printLogAndReturn(HttpServletRequest request, Result<?> result, Exception e) {
        String requestUrl = request.getRequestURL().toString() +
                (StringUtils.isEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString());
        String jsonString = JSON.toJSONString(result);
        logger.error("<-异常返回-> 请求接口:{} | 异常时间:{} | 异常结果:{}->", requestUrl, System.currentTimeMillis(),
                jsonString);
        logger.error("<--异常堆栈信息-->");
        logger.error(Throwables.getStackTraceAsString(e));
        return result;
    }
}
