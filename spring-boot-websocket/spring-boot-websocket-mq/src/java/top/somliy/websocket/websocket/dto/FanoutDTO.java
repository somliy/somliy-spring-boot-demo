package top.somliy.websocket.websocket.dto;

/**
 * 类名： @ClassName FanoutDTO
 * 创建人：@author zhao dong
 * 类描述：@Description: 广播类
 * 创建时间: 2023/10/16 23:00
 */
public class FanoutDTO {
    /**
     * 1开启 2关闭
     */
    private String type;
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "FanoutDTO{" + "type='" + type + '\'' + ", key='" + key + '\'' + '}';
    }
}
