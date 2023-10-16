package top.somliy.websocket.websocket.core;


import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.somliy.websocket.websocket.constants.WebSocketConstants;
import top.somliy.websocket.websocket.properties.WebSocketProperty;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类名： @ClassName AbstractWebSocketServer websocket抽象类
 * 创建人：@author zhao dong
 * 类描述：@Description: websocket抽象类
 * 创建时间: 2023/10/8 09:31
 */
@Slf4j
@Component
public abstract class AbstractWebSocketServer implements WebSocketServer {
    private static final WebSocketProperty WEB_SOCKET_PROPERTY = SpringUtil.getBean(WebSocketProperty.class);
    private static final SessionContainer SESSION_CONTAINER = SpringUtil.getBean(SessionContainer.class);

    @OnOpen
    @Override
    public void open(Session session, @PathParam("key") String key) {
        log.info("[WebSocket]创建链接key:" + key);
        SessionExt sessionExt = this.handleSession(session);
        SESSION_CONTAINER.addSessionExtAndClose(key, sessionExt);
        this.handleFanout(sessionExt, key);
        this.onOpen(session, key);
    }

    /**
     * 创建链接回调
     *
     * @param session session
     * @param key     key
     */
    public abstract void onOpen(Session session, String key);

    /**
     * 发送消息
     *
     * @param message message
     * @param key     key
     */
    public void send(String message, @PathParam("key") String key) {
        log.debug("[WebSocket]发送消息key:" + key);
        SessionExt sessionExt = SESSION_CONTAINER.getSessionExt(key);
        if (sessionExt != null && sessionExt.getSessionIsOpen()) {
            Session session = sessionExt.getSession();
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("[WebSocket]发送失败 原因为:" + e);
            }
        } else {
            log.info("[WebSocket]链接已关闭，无需发送");
        }
    }

    @OnMessage
    @Override
    public void message(Session session, String message, @PathParam("key") String key) {
        log.info("[WebSocket]接收消息key:" + key);
        this.onMessage(message, session, key);
    }

    /**
     * 接收消息回调
     *
     * @param message message
     * @param session session
     * @param key     key
     */
    public abstract void onMessage(String message, Session session, String key);

    @OnClose
    @Override
    public void close(Session session, @PathParam("key") String key) {
        log.info("[WebSocket]关闭链接key:" + key);
        this.onClose(session, key);
    }

    /**
     * 关闭链接回调
     *
     * @param session session
     * @param key     key
     */
    public abstract void onClose(Session session, String key);

    @OnError
    @Override
    public void error(Session session, Throwable throwable) {
        log.error("[WebSocket]异常:" + throwable);
        this.onError(session, throwable);
    }

    /**
     * 异常回调
     *
     * @param session   session
     * @param throwable throwable
     */
    public abstract void onError(Session session, Throwable throwable);

    /**
     * 处理session
     *
     * @param session session
     * @return session
     */
    private SessionExt handleSession(Session session) {
        Integer textMessageSize = WEB_SOCKET_PROPERTY.getTextMessageSize();
        if (textMessageSize != null) {
            session.setMaxTextMessageBufferSize(textMessageSize);
        }
        Integer binaryMessageSize = WEB_SOCKET_PROPERTY.getBinaryMessageSize();
        if (binaryMessageSize != null) {
            session.setMaxBinaryMessageBufferSize(binaryMessageSize);
        }
        Long timeoutPeriod = WEB_SOCKET_PROPERTY.getTimeoutPeriod();
        if (timeoutPeriod != null) {
            session.setMaxIdleTimeout(timeoutPeriod);
        } else {
            session.setMaxIdleTimeout(WebSocketConstants.TIME_OUT);
        }
        SessionExt sessionExt = new SessionExt();
        sessionExt.setSession(session);
        sessionExt.setVersion(WebSocketConstants.INT_1);
        String uuidString = IdUtil.fastSimpleUUID();
        sessionExt.setUniqueId(uuidString);
        return sessionExt;
    }

    /**
     * 处理广播
     *
     * @param sessionExt sessionExt
     * @param key        key
     */
    public abstract void handleFanout(SessionExt sessionExt, String key);
}
