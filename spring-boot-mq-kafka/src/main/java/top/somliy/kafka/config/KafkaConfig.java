package top.somliy.kafka.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import top.somliy.kafka.properties.KafkaProperties;

/**
 * 类名： @ClassName KafkaTopicConfig
 * 创建人：@author zhao dong
 * 类描述：@Description:
 * 创建时间: 2023/3/20 16:45
 */
@Slf4j
@Configuration
public class KafkaConfig implements InitializingBean {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Override
    public void afterPropertiesSet() {
        // 获取配置
        String topicIds = kafkaProperties.getTopicIds();
        String groupId = kafkaProperties.getGroupId();
        log.info("KafkaConfig 读取配置，topicIds：" + topicIds);
        log.info("KafkaConfig 读取配置，groupId：" + groupId);
        // 系统写入
        System.setProperty("topicIds", topicIds);
        System.setProperty("groupId", groupId);
    }
}
