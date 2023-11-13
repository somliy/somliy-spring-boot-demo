package top.somliy.complat.websocket.interceptor;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import top.somliy.complat.websocket.util.WebSocketUtil;
import top.somliy.server.service.UserInfoService;

import java.net.URI;
import java.util.Map;

/**
 * 类名： @ClassName WebSocketHandshakeInterceptor websocket拦截器
 * 创建人：@author zhao dong
 * 类描述：@Description: websocket拦截器
 * 创建时间: 2023/10/8 16:13
 */
@Slf4j
@Component
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) {
        // 验证令牌
        log.debug("[websocket]连接前钩子");
        UriComponents uriComponents = UriComponentsBuilder.fromHttpRequest(request).build();
        URI uri = uriComponents.toUri();
        String key = WebSocketUtil.getUriPathKey(uri);
        UserInfoService userInfoService = SpringUtil.getBean(UserInfoService.class);
        return userInfoService.judgeUserKey(key);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception exception) {
        log.debug("[websocket]连接后钩子");
    }
}
