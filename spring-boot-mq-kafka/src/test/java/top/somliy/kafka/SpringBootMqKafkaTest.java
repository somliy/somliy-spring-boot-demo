package top.somliy.kafka;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import top.somliy.kafka.producer.KafkaProducer;

import java.util.UUID;

/**
 * 类名： @ClassName SpringBootMqKafkaTest
 * 创建人：@author zhao dong
 * 类描述：@Description: 测试类
 * 创建时间: 2023/3/21 17:31
 */
@Slf4j
@SpringBootTest
public class SpringBootMqKafkaTest {

    @Autowired
    private KafkaProducer kafkaProducer;

    @SneakyThrows
    @Test
    void testSyncSend() {
        String string = UUID.randomUUID().toString();
        SendResult<Object, Object> objectObjectSendResult = kafkaProducer.syncSend(string);
        System.out.println(objectObjectSendResult);
    }

    @SneakyThrows
    @Test
    void testAsyncSend() {
        String string = UUID.randomUUID().toString();
        kafkaProducer.asyncSend(string).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
            @Override
            public void onFailure(Throwable e) {
                log.info("[testAsyncSend][发送data：[{}] 发送异常]]", string, e);
            }

            @Override
            public void onSuccess(SendResult<Object, Object> result) {
                log.info("[testAsyncSend][发送data：[{}] 发送成功，结果为：[{}]]", string, result);
            }
        });
    }
}
