package top.somliy.websocket.websocket.core;

import javax.websocket.Session;
import java.io.IOException;

/**
 * 类名： @ClassName SessionExt
 * 创建人：@author zhao dong
 * 类描述：@Description: Session增强
 * 创建时间: 2023/10/8 09:51
 */
public class SessionExt {

    private Session session;

    private String uniqueId;

    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public boolean getSessionIsOpen() {
        return session.isOpen();
    }

    /**
     * 关闭session连接
     */
    public void closeSession() {
        boolean open = session.isOpen();
        if (open) {
            this.closeSession(session);
        }
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * 关闭session
     *
     * @param session session
     */
    private void closeSession(Session session) {
        try {
            session.close();
        } catch (IOException e) {
            throw new RuntimeException("[websocket]关闭session" + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "SessionExt{" + "session=" + session + ", uniqueId=" + uniqueId + '}';
    }
}
