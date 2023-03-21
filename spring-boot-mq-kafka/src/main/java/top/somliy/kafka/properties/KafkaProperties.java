package top.somliy.kafka.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 类名： @ClassName KafkaTopicProperties kafka配置
 * 创建人：@author zhao dong
 * 类描述：@Description: kafka配置
 * 创建时间: 2023/3/12 22:07
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "project.kafka")
public class KafkaProperties {

    private String topicIds;

    private String groupId;
}
