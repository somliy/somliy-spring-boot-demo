package top.somliy.mq;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.somliy.mq.constant.RabbitMqConstant;
import top.somliy.mq.producer.RabbitMqProduceBean;

/**
 * 类名： @ClassName SpringBootRabbitMqProducerTest
 * 创建人：@author zhao dong
 * 类描述：@Description:
 * 创建时间: 2023/5/30 18:20
 */
@Slf4j
@SpringBootTest
public class SpringBootRabbitMqProducerTest {
    @Autowired
    private RabbitMqProduceBean rabbitMqProduceBean;

    @SneakyThrows
    @Test
    void testSyncSend() {
        String string = "UUID.randomUUID().toString()";
        rabbitMqProduceBean.syncSend(string, RabbitMqConstant.ROUTING_KEY_MESSAGE_PUSH.getTopicRoutingKeySuffix());
        log.info("发送成功：" + string);
    }
}
