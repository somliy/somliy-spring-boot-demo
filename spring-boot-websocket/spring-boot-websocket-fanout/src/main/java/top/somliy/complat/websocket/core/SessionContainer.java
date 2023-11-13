package top.somliy.complat.websocket.core;

import org.springframework.stereotype.Component;
import top.somliy.complat.websocket.constants.WebSocketConstants;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 类名： @ClassName SessionContainer
 * 创建人：@author zhao dong
 * 类描述：@Description: session管理
 * 创建时间: 2023/10/16 22:50
 */
@Component
public class SessionContainer {
    private static final ConcurrentHashMap<String, SessionExt> SESSION_MAP =
            new ConcurrentHashMap<>(WebSocketConstants.INT_16);

    /**
     * 获取 session 数据
     *
     * @param key key
     * @return session
     */
    public SessionExt getSessionExt(String key) {
        return SESSION_MAP.getOrDefault(key, null);
    }

    /**
     * 添加 session 数据
     *
     * @param key        key
     * @param sessionExt sessionExt
     */
    public void addSessionExt(String key, SessionExt sessionExt) {
        SESSION_MAP.put(key, sessionExt);
    }

    /**
     * 添加 session 数据
     *
     * @param key        key
     * @param sessionExt sessionExt
     */
    public void addSessionExtAndClose(String key, SessionExt sessionExt) {
        SessionExt sessionExtOld = SESSION_MAP.get(key);
        if (sessionExtOld != null) {
            sessionExtOld.closeSession();
        }
        SESSION_MAP.put(key, sessionExt);
    }

    /**
     * 删除 session 数据
     *
     * @param key key
     */
    public void delSessionExt(String key) {
        SESSION_MAP.remove(key);
    }
}
