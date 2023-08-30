package top.somliy.mq.message;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 类名： @ClassName RabbitMqMessage
 * 创建人：@author zhao dong
 * 类描述：@Description: RabbitMqMessage消息
 * 创建时间: 2023/3/12 22:29
 */
public class RabbitMqMessage implements Serializable {

    private static final long serialVersionUID = -7835984207101134438L;

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
        return "RabbitMqMessage{" + "id='" + id + '\'' + ", data='" + data + '\'' + ", sendTime=" + sendTime + '}';
    }
}
