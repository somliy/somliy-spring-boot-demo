package top.somliy.websocket.websocket.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * 类名： @ClassName ServletWebSocketServerHandler 消息处理器
 * 创建人：@author zhao dong
 * 类描述：@Description: 消息处理器
 * 创建时间: 2023/10/8 11:49
 */
@Slf4j
@Component
public class ServletWebSocketServerHandler extends AbstractWebSocketHandler {


    @Override
    public void onOpen(String key, WebSocketSession session) {

    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {

    }

    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {

    }
}
