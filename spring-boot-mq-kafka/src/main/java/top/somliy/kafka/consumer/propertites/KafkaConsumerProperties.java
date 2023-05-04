package top.somliy.kafka.consumer.propertites;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 类名： @ClassName KafkaConsumerProperties 消费者配置文件
 * 创建人：@author zhao dong
 * 类描述：@Description: 消费者配置文件
 * 创建时间: 2023/5/4 18:15
 */
@Configuration
@ConfigurationProperties(prefix = "project.kafka.consumer")
public class KafkaConsumerProperties {
    private String topic1;

    private String group1;

    public String getTopic1() {
        return topic1;
    }

    public void setTopic1(String topic1) {
        this.topic1 = topic1;
    }

    public String getGroup1() {
        return group1;
    }

    public void setGroup1(String group1) {
        this.group1 = group1;
    }
}
