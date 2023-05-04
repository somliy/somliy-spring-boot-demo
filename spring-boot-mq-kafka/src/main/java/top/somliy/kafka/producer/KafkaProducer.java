package top.somliy.kafka.producer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import top.somliy.kafka.message.KafkaMessage;
import top.somliy.kafka.producer.properties.KafkaProducerProperties;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * 类名： @ClassName KafkaProducer
 * 创建人：@author zhao dong
 * 类描述：@Description: kafka 生产者
 * 创建时间: 2023/3/21 14:05
 */
@Slf4j
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Autowired
    private KafkaProducerProperties kafkaProducerProperties;

    /**
     * 发送同步消息
     * ps：消息是如何序列化的 （可以在 ui 后台上看到）
     * 在序列化时，我们使用了 JsonSerializer 序列化 Message 消息对象，它会在 Kafka 消息 Headers 的 __TypeId__ 上，值为 Message 消息对应的类全名。
     * 在反序列化时，我们使用了 JsonDeserializer 序列化出 Message 消息对象，它会根据 Kafka 消息 Headers 的 __TypeId__ 的值，反序列化消息内容成该 Message 对象。
     *
     * @param data 消息
     * @return 结果
     * @throws ExecutionException   异常
     * @throws InterruptedException 异常
     */
    public SendResult<Object, Object> syncSend(String data) throws ExecutionException, InterruptedException {
        KafkaMessage message = new KafkaMessage();
        message.setData(data);
        String uuid = UUID.randomUUID().toString();
        message.setId(uuid);
        message.setSendTime(LocalDateTime.now());
        // 同步发送消息，使用 ListenableFuture 对象的 get() 方法，阻塞等待发送结果，从而实现同步的效果
        return kafkaTemplate.send(kafkaProducerProperties.getTopic1(), message).get();
    }


    /**
     * 发送异步消息
     *
     * @param data 消息
     * @return 结果
     */
    public ListenableFuture<SendResult<Object, Object>> asyncSend(String data) {
        KafkaMessage message = new KafkaMessage();
        message.setData(data);
        String uuid = UUID.randomUUID().toString();
        message.setId(uuid);
        message.setSendTime(LocalDateTime.now());
        // 异步发送消息
        return kafkaTemplate.send(kafkaProducerProperties.getTopic1(), message);
    }
}
