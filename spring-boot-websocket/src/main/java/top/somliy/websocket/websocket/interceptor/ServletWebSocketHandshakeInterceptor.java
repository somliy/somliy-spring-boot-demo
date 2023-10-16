package top.somliy.websocket.websocket.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import top.somliy.websocket.websocket.constants.WebSocketConstants;

import java.net.URI;
import java.util.List;
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
                                   Map<String, Object> attributes) throws Exception {
        // 验证令牌
        log.info("beforeHandshake");
        // 获取认证信息并验证用户角色等权限信息
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception exception) {
        log.info("afterHandshake");
    }
}
