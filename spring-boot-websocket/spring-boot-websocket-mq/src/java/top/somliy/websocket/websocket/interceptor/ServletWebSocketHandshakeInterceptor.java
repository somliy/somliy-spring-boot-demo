package top.somliy.websocket.websocket.interceptor;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import top.somliy.websocket.service.DemoService;

import java.util.Map;

/**
 * 类名： @ClassName ServletWebSocketHandshakeInterceptor websocket拦截器
 * 创建人：@author zhao dong
 * 类描述：@Description: websocket拦截器
 * 创建时间: 2023/10/8 16:13
 */
@Slf4j
@Component
public class ServletWebSocketHandshakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) {
        // 验证令牌
        log.debug("[websocket]连接前钩子");
        // 获取认证信息并验证用户角色等权限信息
        UriComponents uriComponents = UriComponentsBuilder.fromHttpRequest(request).build();
        MultiValueMap<String, String> queryParams = uriComponents.getQueryParams();
        String key = queryParams.getFirst("key");
        DemoService demoService = SpringUtil.getBean(DemoService.class);
        return demoService.judgeUserKey(key);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception exception) {
        log.debug("[websocket]连接后钩子");
    }
}
