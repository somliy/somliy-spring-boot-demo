package top.somliy.websocket.websocket.handler;

import org.springframework.web.socket.WebSocketHandler;
import top.somliy.websocket.websocket.core.SessionExt;

/**
 * 类名： @ClassName FanoutWebSocketHandler
 * 创建人：@author zhao dong
 * 类描述：@Description: websocket广播类
 * 创建时间: 2023/10/17 22:15
 */

public interface FanoutWebSocketHandler extends WebSocketHandler {

    /**
     * 处理广播信息
     *
     * @param key        key
     * @param sessionExt sessionExt
     */
    void handleFanoutMessage(String key, SessionExt sessionExt);
}
