package top.somliy.websocket.websocket.dto;

/**
 * 类名： @ClassName FanoutDTO
 * 创建人：@author zhao dong
 * 类描述：@Description: 广播类
 * 创建时间: 2023/10/16 23:00
 */
public class FanoutDTO {
    private String key;
    private String uniqueId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public String toString() {
        return "FanoutDTO{" + "key='" + key + '\'' + ", uniqueId='" + uniqueId + '\'' + '}';
    }
}
