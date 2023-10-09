package top.somliy.websocket.websocket.core;

import javax.websocket.Session;

/**
 * 类名： @ClassName SessionExt
 * 创建人：@author zhao dong
 * 类描述：@Description: Session增强
 * 创建时间: 2023/10/8 09:51
 */
public class SessionExt {

    private Session session;

    private Long timestamp;

    public boolean getSessionIsOpen() {
        return session.isOpen();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "SessionExt{" + "session=" + session + ", timestamp=" + timestamp + '}';
    }
}
