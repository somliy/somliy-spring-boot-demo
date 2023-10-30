package top.somliy.mq.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.somliy.mq.constant.RabbitMqConstant;
import top.somliy.mq.producer.RabbitMqProduceBean;

/**
 * 类名： @ClassName DemoController
 * 创建人：@author zhao dong
 * 类描述：@Description:
 * 创建时间: 2023/10/30 17:30
 */
@Slf4j
@RestController
@RequestMapping("demo")
public class DemoController {
    @Autowired
    private RabbitMqProduceBean rabbitMqProduceBean;

    @GetMapping("send")
    public void sendTest() {
        String string = "UUID.randomUUID().toString()" + System.currentTimeMillis();
        rabbitMqProduceBean.syncSend(string, RabbitMqConstant.ROUTING_KEY_MESSAGE_PUSH.getTopicRoutingKeySuffix());
        log.info("发送成功：" + string);
    }

    @GetMapping("sendDelayed")
    public void sendTestDelayed() {
        String string = "UUID.randomUUID().toString()" + System.currentTimeMillis();
        rabbitMqProduceBean.syncSendDelayed(string, RabbitMqConstant.ROUTING_KEY_MESSAGE_PUSH_DELAYED,
                RabbitMqConstant.TEN_SECONDS);
        log.info("发送成功：" + string);
    }
}
