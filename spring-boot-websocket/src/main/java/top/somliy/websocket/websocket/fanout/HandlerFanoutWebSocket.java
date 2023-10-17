package top.somliy.websocket.websocket.fanout;

/**
 * 类名： @ClassName HandlerFanoutWebSocket
 * 创建人：@author zhao dong
 * 类描述：@Description: websocket广播抽象类
 * 创建时间: 2023/10/17 23:02
 */
public interface HandlerFanoutWebSocket {
    /**
     * 创建链接 后置处理
     *
     * @param key      用户连接标识
     * @param uniqueId 连接唯一标识
     */
    void handleOpenProcessing(String key, String uniqueId);
}
