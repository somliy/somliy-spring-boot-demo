package top.somliy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.somliy.complat.websocket.rabbitmq.producer.RabbitMqProduceBean;

/**
 * 类名： @ClassName TestController 测试
 * 创建人：@author zhao dong
 * 类描述：@Description: 测试
 * 创建时间: 2023/11/15 09:39
 */
@RestController
@RequestMapping("demo")
public class TestController {
    @Autowired
    private RabbitMqProduceBean rabbitMqProduceBean;

    @PostMapping("send")
    public void send(@RequestBody String str) {
        rabbitMqProduceBean.syncFanoutSend(str);
    }
}
