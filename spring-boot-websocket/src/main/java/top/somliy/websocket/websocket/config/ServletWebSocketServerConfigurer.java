package top.somliy.websocket.websocket.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import top.somliy.websocket.websocket.handler.ServletWebSocketServerHandler;
import top.somliy.websocket.websocket.interceptor.ServletWebSocketHandshakeInterceptor;


/**
 * 类名： @ClassName WebSocketConfig websocket配置
 * 创建人：@author zhao dong
 * 类描述：@Description: websocket配置
 * 创建时间: 2023/10/8 11:20
 */
@Slf4j
@Configuration
@EnableWebSocket
public class ServletWebSocketServerConfigurer implements WebSocketConfigurer {
    private static final String PREFIX = "/websocket";
    @Autowired
    private ServletWebSocketServerHandler servletWebSocketServerHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(servletWebSocketServerHandler, PREFIX)
                // 添加拦截器可实现用户链接前进行权限校验等操作
                .addInterceptors(new ServletWebSocketHandshakeInterceptor())
                // 设置允许跨域访问
                .setAllowedOrigins("*");
    }
}