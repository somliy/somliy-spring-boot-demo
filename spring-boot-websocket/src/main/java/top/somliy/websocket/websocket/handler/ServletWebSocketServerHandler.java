package top.somliy.websocket.websocket.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * 类名： @ClassName ServletWebSocketServerHandler 消息处理器
 * 创建人：@author zhao dong
 * 类描述：@Description: 消息处理器
 * 创建时间: 2023/10/8 11:49
 */
@Slf4j
@Component
public class ServletWebSocketServerHandler implements WebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("afterConnectionEstablished");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("handleMessage");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("handleTransportError");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("afterConnectionClosed");
    }

    @Override
    public boolean supportsPartialMessages() {
        // 是否支持接收不完整的消息
        return false;
    }
}
