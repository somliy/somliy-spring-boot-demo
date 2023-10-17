package top.somliy.websocket.websocket.handler;

import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import top.somliy.websocket.websocket.constants.WebSocketConstants;
import top.somliy.websocket.websocket.core.SessionContainer;
import top.somliy.websocket.websocket.core.SessionExt;
import top.somliy.websocket.websocket.properties.WebSocketProperty;
import top.somliy.websocket.websocket.util.WebSocketUtil;

import java.net.URI;
import java.util.Map;

/**
 * 类名： @ClassName AbstractWebSocketHandler websocket抽象类
 * 创建人：@author zhao dong
 * 类描述：@Description: websocket抽象类
 * 创建时间: 2023/10/17 17:28
 */
@Slf4j
public abstract class AbstractWebSocketHandler implements WebSocketHandler {
    private static final WebSocketProperty WEB_SOCKET_PROPERTY = SpringUtil.getBean(WebSocketProperty.class);
    private static final SessionContainer SESSION_CONTAINER = SpringUtil.getBean(SessionContainer.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.debug("[websocket]连接成功回调");
        URI uri = session.getUri();
        Map<String, String> paramsFrom = WebSocketUtil.getParamsFromURI(uri);
        SessionExt sessionExt = this.handleSession(session);
        String key = paramsFrom.get(WebSocketConstants.STR_KEY);
        SESSION_CONTAINER.addSessionExtAndClose(key, sessionExt);
//        this.handleFanout(sessionExt, key);
        this.onOpen(key, session);
    }

    /**
     * 连接后回调
     *
     * @param key     key
     * @param session session
     */
    public abstract void onOpen(String key, WebSocketSession session);

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.debug("[websocket]处理消息");
        if (message instanceof TextMessage) {
            this.handleTextMessage(session, (TextMessage) message);
        } else if (message instanceof BinaryMessage) {
            this.handleBinaryMessage(session, (BinaryMessage) message);
        }
    }

    /**
     * 处理文本消息
     *
     * @param session session
     * @param message 消息
     */
    public abstract void handleTextMessage(WebSocketSession session, TextMessage message);

    /**
     * 处理二进制消息
     *
     * @param session session
     * @param message 消息
     */
    public abstract void handleBinaryMessage(WebSocketSession session, BinaryMessage message);

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.debug("[websocket]异常");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.debug("[websocket]连接关闭回调");
    }

    @Override
    public boolean supportsPartialMessages() {
        // 是否支持接收不完整的消息
        return false;
    }

    /**
     * 处理session
     *
     * @param session session
     * @return session
     */
    private SessionExt handleSession(WebSocketSession session) {
        Integer textMessageSize = WEB_SOCKET_PROPERTY.getTextMessageSize();
        if (textMessageSize != null) {
            session.setTextMessageSizeLimit(textMessageSize);
        }
        Integer binaryMessageSize = WEB_SOCKET_PROPERTY.getBinaryMessageSize();
        if (binaryMessageSize != null) {
            session.setBinaryMessageSizeLimit(binaryMessageSize);
        }
        SessionExt sessionExt = new SessionExt();
        sessionExt.setSession(session);
        sessionExt.setVersion(WebSocketConstants.INT_1);
        String uuidString = IdUtil.fastSimpleUUID();
        sessionExt.setUniqueId(uuidString);
        return sessionExt;
    }
}
