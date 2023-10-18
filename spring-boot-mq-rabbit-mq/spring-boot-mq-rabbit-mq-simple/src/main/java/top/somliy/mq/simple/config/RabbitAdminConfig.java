package top.somliy.mq.simple.config;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 类名： @ClassName RabbitAdminConfig RabbitAdmin配置类
 * 创建人：@author zhao dong
 * 类描述：@Description: RabbitAdmin配置类
 * 创建时间: 2023/5/30 17:40
 */
@Component
public class RabbitAdminConfig {
    private RabbitAdmin rabbitAdmin;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        rabbitAdmin = initRabbitAdmin();
    }

    /**
     * 获取实体
     *
     * @return RabbitAdmin
     */
    public RabbitAdmin getRabbitAdmin() {
        if (rabbitAdmin == null) {
            rabbitAdmin = initRabbitAdmin();
        }
        return rabbitAdmin;
    }

    /**
     * RabbitAdmin 可以方便的操作声明交换机，声明队列，绑定，清除消息，发送消息
     *
     * @return RabbitAdmin
     */
    public RabbitAdmin initRabbitAdmin() {
        rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }
}
