package top.somliy.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
     * @param consumerRecord 消息
     */
    @KafkaListener(topics = "${project.kafka.consumer.topic1}", groupId = "${project.kafka.consumer.group1}")
    public void onMessage(ConsumerRecord<Object, Object> consumerRecord) {
        log.info("【kafka】接收到消息:" + consumerRecord.value());
        try {
            Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
            if (kafkaMessage.isPresent()) {
                Object message = consumerRecord.value();
                log.info("【kafka】接收到消息" + message.toString());
            } else {
                log.error("【kafka】接收到消息为空");
            }
        } catch (Exception e) {
            log.error("【kafka】接收到消息为空" + e.getMessage());
        }
    }
}


