package top.somliy.complat.websocket.core.fanout;

/**
 * 类名： @ClassName FanoutWebSocketHandler
 * 创建人：@author zhao dong
 * 类描述：@Description: websocket广播类
 * 创建时间: 2023/10/17 22:15
 */

public interface FanoutWebSocketHandler {
    /**
     * 创建链接 后置处理
     *
     * @param key      用户连接标识
     * @param uniqueId 连接唯一标识
     */
    void handleOpenProcessing(String key, String uniqueId);

    /**
     * 关闭链接 后置处理
     *
     * @param key      用户连接标识
     * @param uniqueId 连接唯一标识
     */
    void handleCloseProcessing(String key, String uniqueId);

    /**
     * 广播处理消息
     *
     * @param key  用户连接标识
     * @param data 消息
     */
    void handleSendMessage(String key, String data);
}
