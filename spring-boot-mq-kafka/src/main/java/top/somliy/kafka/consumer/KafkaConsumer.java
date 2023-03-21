package top.somliy.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import top.somliy.kafka.message.KafkaMessage;

/**
 * 类名： @ClassName KafkaConsumer
 * 创建人：@author zhao dong
 * 类描述：@Description: kafka消费者
 * 创建时间: 2023/3/21 11:47
 */
@Slf4j
@Component
public class KafkaConsumer {

    /**
     * kafka监听消息
     *
     * @param kafkaMessage 消息
     */
    @KafkaListener(topics = "#{'${topicIds}'.split(',')}", groupId = "${groupId}")
    public void onMessage(KafkaMessage kafkaMessage) {
        log.info("[线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), kafkaMessage);
    }
}


