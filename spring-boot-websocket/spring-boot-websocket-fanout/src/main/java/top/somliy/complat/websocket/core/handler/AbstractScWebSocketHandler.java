package top.somliy.complat.websocket.core.handler;

import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import top.somliy.complat.websocket.core.SessionContainer;
import top.somliy.complat.websocket.core.SessionExt;
import top.somliy.complat.websocket.util.WebSocketUtil;

import java.io.IOException;

/**
 * 类名： @ClassName AbstractScWebSocketHandler
 * 创建人：@author zhao dong
 * 类描述：@Description: websocket实现类
 * 创建时间: 2023/10/30 22:55
 */
@Slf4j
public abstract class AbstractScWebSocketHandler extends AbstractWebSocketHandler {
    private static final SessionContainer SESSION_CONTAINER = SpringUtil.getBean(SessionContainer.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("[websocket]afterConnectionEstablished");
        String key = WebSocketUtil.getUriPath(session);
        SessionExt sessionExt = this.handleSession(session);
        SESSION_CONTAINER.addSessionExtAndClose(key, sessionExt);
        this.handleOpenFanout(sessionExt, key);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("[websocket]afterConnectionClosed");
        String key = WebSocketUtil.getUriPath(session);
        SessionExt sessionExt = SESSION_CONTAINER.getSessionExt(key);
        if (sessionExt != null) {
            this.handleCloseFanout(sessionExt, key);
        }
    }

    /**
     * 处理session
     *
     * @param session session
     * @return session
     */
    private SessionExt handleSession(WebSocketSession session) {
        SessionExt sessionExt = new SessionExt();
        sessionExt.setSession(session);
        String uuidString = IdUtil.fastSimpleUUID();
        sessionExt.setUniqueId(uuidString);
        return sessionExt;
    }

    /**
     * 处理广播，开启
     *
     * @param sessionExt sessionExt
     * @param key        key
     */
    public abstract void handleOpenFanout(SessionExt sessionExt, String key);

    /**
     * 处理广播，关闭
     *
     * @param sessionExt sessionExt
     * @param key        key
     */
    public abstract void handleCloseFanout(SessionExt sessionExt, String key);

    /**
     * 发送消息
     *
     * @param key key
     * @param msg msg
     */
    public void send(String key, String msg) {
        SessionExt sessionExt = SESSION_CONTAINER.getSessionExt(key);
        if (sessionExt == null) {
            throw new RuntimeException("发送失败");
        }
        WebSocketSession session = sessionExt.getSession();
        try {
            session.sendMessage(new TextMessage(msg));
        } catch (IOException e) {
            log.error("[WebSocket]发送失败 原因为:" + e);
        }
    }
}
