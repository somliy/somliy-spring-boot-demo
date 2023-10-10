//package top.somliy.websocket.websocket.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import top.somliy.websocket.websocket.handler.ServletWebSocketServerHandler;
//import top.somliy.websocket.websocket.interceptor.ServletWebSocketHandshakeInterceptor;
//
//import javax.annotation.Resource;
//
///**
// * mvc的ws配置
// *
// * @author shixiaodong
// * @date 2023-10-09
// */
//@Configuration
//@EnableWebSocket
//public class WebSocketConfiguration implements WebSocketConfigurer {
//    @Resource
//    ServletWebSocketServerHandler servletWebSocketServerHandler;
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        // 配置处理器
//        registry.addHandler(servletWebSocketServerHandler, "/")
//                // 配置拦截器
//                .addInterceptors(new ServletWebSocketHandshakeInterceptor())
//                // 解决跨域问题
//                .setAllowedOrigins("*");
//    }
//}
