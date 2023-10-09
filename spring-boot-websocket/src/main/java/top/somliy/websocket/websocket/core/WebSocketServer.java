package top.somliy.websocket.websocket.core;

import javax.websocket.Session;
import javax.websocket.server.PathParam;

/**
 * 类名： @ClassName WebSocketServer
 * 创建人：@author zhao dong
 * 类描述：@Description:
 * 创建时间: 2023/10/7 17:33
 */
public interface WebSocketServer {
    /**
     * 创建链接
     *
     * @param session session
     * @param key     key
     */
    void open(Session session, @PathParam("key") String key);

    /**
     * 接收消息
     *
     * @param session session
     * @param message 消息
     * @param key     key
     */
    void message(Session session, String message, @PathParam("key") String key);

    /**
     * 关闭链接
     *
     * @param session session
     * @param key     key
     */
    void close(Session session, @PathParam("key") String key);

    /**
     * 链接异常
     *
     * @param session   session
     * @param throwable 异常
     */
    void error(Session session, Throwable throwable);
}

