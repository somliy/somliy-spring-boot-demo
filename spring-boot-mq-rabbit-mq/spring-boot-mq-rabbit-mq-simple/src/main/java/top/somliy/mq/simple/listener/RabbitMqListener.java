//package top.somliy.mq.simple.listener;
//
//import com.rabbitmq.client.Channel;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import top.somliy.mq.simple.constant.RabbitMqConstant;
//import top.somliy.mq.simple.rabbitmq.core.RabbitMqAdmin;
//import top.somliy.mq.simple.rabbitmq.core.RabbitMqMessageListener;
//
//import javax.annotation.PostConstruct;
//
///**
// * 类名： @ClassName RabbitMqListener rabbitMq监听
// * 创建人：@author zhao dong
// * 类描述：@Description: rabbitMq监听
// * 创建时间: 2023/10/20 21:02
// */
//@Slf4j
//@Component
//public class RabbitMqListener {
//    @Autowired
//    private RabbitMqAdmin rabbitMqAdmin;
//
//    @PostConstruct
//    public void init() {
//        this.testListener();
//    }
//
//    /**
//     * 测试监听
//     */
//    private void testListener() {
//        rabbitMqAdmin.bindingQueue(RabbitMqConstant.EXCHANGE, RabbitMqConstant.QUEUE_TYPE_MESSAGE_PUSH,
//                RabbitMqConstant.ROUTING_KEY_MESSAGE_PUSH);
//        rabbitMqAdmin.registerListenerManualAck(new RabbitMqMessageListener() {
//            @Override
//            public void onJsonMessage(Message message, Channel channel) {
//                log.info("message: {}", message.toString());
//            }
//        }, RabbitMqConstant.QUEUE_TYPE_MESSAGE_PUSH);
//    }
//}
