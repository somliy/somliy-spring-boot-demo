package top.somliy.kafka.message;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 类名： @ClassName Message
 * 创建人：@author zhao dong
 * 类描述：@Description: kafka消息
 * 创建时间: 2023/3/12 22:29
 */
@Data
@ToString
public class KafkaMessage implements Serializable {
    private String id;

    private String data;

    private LocalDateTime sendTime;
}
