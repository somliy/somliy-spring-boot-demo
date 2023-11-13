package top.somliy.complat.websocket.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.ServletWebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import top.somliy.complat.websocket.core.handler.MqWebSocketHandler;
import top.somliy.complat.websocket.interceptor.WebSocketHandshakeInterceptor;

/**
 * 类名： @ClassName ServletWebSocketServerConfigurer websocket配置类
 * 创建人：@author zhao dong
 * 类描述：@Description: websocket配置类
 * 创建时间: 2023/11/13 10:17
 */
@Slf4j
@Configuration
@EnableWebSocket
public class ServletWebSocketServerConfigurer implements WebSocketConfigurer {
    private static final String PREFIX = "/websocket";
    @Autowired
    private MqWebSocketHandler mqWebSocketHandler;
    @Autowired
    private WebSocketHandshakeInterceptor webSocketHandshakeInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        if (registry instanceof ServletWebSocketHandlerRegistry) {
            //替换UrlPathHelper
            ((ServletWebSocketHandlerRegistry) registry).setUrlPathHelper(new PrefixUrlPathHelper(PREFIX));
        }

        registry.addHandler(mqWebSocketHandler, PREFIX + "/**")
                // 添加拦截器可实现用户链接前进行权限校验等操作
                .addInterceptors(webSocketHandshakeInterceptor)
                // 设置允许跨域访问
                .setAllowedOrigins("*");
    }
}