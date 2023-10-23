package top.somliy.mq.simple.demo;

import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.somliy.mq.simple.constant.RabbitMqConstant;
import top.somliy.mq.simple.rabbitmq.core.RabbitMqPushBean;
import top.somliy.mq.simple.rabbitmq.message.RabbitMqMessage;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 类名： @ClassName DemoController 测试
 * 创建人：@author zhao dong
 * 类描述：@Description: 测试
 * 创建时间: 2023/10/20 21:04
 */
@RestController
@RequestMapping("demo")
public class DemoController {
    @Autowired
    private RabbitMqPushBean rabbitMqPushBean;

    @PostMapping("test")
    public String test(@RequestBody String string) {
        // 创建消息
        RabbitMqMessage message = new RabbitMqMessage();
        message.setData(string);
        String uuid = UUID.randomUUID().toString();
        message.setId(uuid);
        message.setSendTime(LocalDateTime.now());
        String messageStr = JSONUtil.toJsonStr(message);
        rabbitMqPushBean.send(RabbitMqConstant.ROUTING_KEY_MESSAGE_PUSH, messageStr);
        return "success";
    }
}

