package top.somliy.websocket.websocket.constants;

/**
 * 类名： @ClassName RedisConstants 变量
 * 创建人：@author zhao dong
 * 类描述：@Description: 变量
 * 创建时间: 2023/10/16 15:24
 */
public final class RedisConstants {

    /**
     * websocket广播
     */
    public static final String CHANNEL_WEBSOCKET_FANOUT = "channel_websocket_fanout";


    private RedisConstants() {
        throw new IllegalStateException("Utility class");
    }
}
