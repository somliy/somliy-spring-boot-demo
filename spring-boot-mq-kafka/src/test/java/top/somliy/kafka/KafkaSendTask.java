package top.somliy.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.somliy.kafka.producer.KafkaProducer;

import java.util.concurrent.ExecutionException;

/**
 * 类名： @ClassName KafkaSendTask
 * 创建人：@author zhao dong
 * 类描述：@Description: kafka定时发送任务
 * 创建时间: 2023/3/22 15:32
 */
@Slf4j
@SpringBootTest
public class KafkaSendTask {

    @Autowired
    private KafkaProducer kafkaProducer;


    @Scheduled(cron = "0/10 * *  * * ? ")
    public void execute2() throws ExecutionException, InterruptedException {
//        String string = "{\"msgList\":[{\"clientType\":\"\",\"iD\":\"111111111q1111111123\",\"idCard\":\"1f5fadbf7cdc4d2bd69c6c18d0e491ec501d50c35f4ab40e361221e6120a6a9d111\",\"msgDate\":\"2023-03-27T10:22:48\",\"msgInfo\":\"消息体（可带HTML标签）\",\"msgOperate\":[{\"operateName\":\"操作名称\",\"redirectUrl\":\"消息操作跳转地址（需注册到资源分发网关）\"},{\"operateName\":\"操作名称11\",\"redirectUrl\":\"消息操作跳转地址（需注册到资源分发网关）11\"}],\"msgSource\":\"来源单位\",\"msgTitle\":\"消息标题\",\"msgType\":\"66\",\"userType\":\"1\"}],\"serviceId\":\"1234\"}";
        kafkaProducer.syncSend("testring");
    }
}
