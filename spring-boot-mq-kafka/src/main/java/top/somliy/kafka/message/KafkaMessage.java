package top.somliy.kafka.message;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 类名： @ClassName Message
 * 创建人：@author zhao dong
 * 类描述：@Description: kafka消息
 * 创建时间: 2023/3/12 22:29
 */
public class KafkaMessage implements Serializable {
    private static final long serialVersionUID = -918965225683254278L;
    private String id;

    private String data;

    private LocalDateTime sendTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "KafkaMessage{" + "id='" + id + '\'' + ", data='" + data + '\'' + ", sendTime=" + sendTime + '}';
    }
}
