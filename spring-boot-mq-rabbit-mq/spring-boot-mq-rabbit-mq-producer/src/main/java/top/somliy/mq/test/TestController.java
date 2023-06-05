package top.somliy.mq.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.somliy.mq.constant.RabbitMqConstant;
import top.somliy.mq.producer.RabbitTemplatePush;

/**
 * 类名： @ClassName TestController 测试
 * 创建人：@author zhao dong
 * 类描述：@Description: 测试
 * 创建时间: 2023/6/1 14:52
 */
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {

    @Autowired
    private RabbitTemplatePush rabbitTemplatePush;

    @GetMapping("send")
    public void sendTest() {
        String string = "UUID.randomUUID().toString()" + System.currentTimeMillis();
        rabbitTemplatePush.syncSend(string, RabbitMqConstant.ROUTING_KEY_MESSAGE_PUSH);
        log.info("发送成功：" + string);
    }

    @GetMapping("sendDelayed")
    public void sendTestDelayed() {
        String string = "UUID.randomUUID().toString()" + System.currentTimeMillis();
        rabbitTemplatePush.syncSendDelayed(string, RabbitMqConstant.ROUTING_KEY_MESSAGE_PUSH_DELAYED, RabbitMqConstant.TEN_SECONDS);
        log.info("发送成功：" + string);
    }
}
