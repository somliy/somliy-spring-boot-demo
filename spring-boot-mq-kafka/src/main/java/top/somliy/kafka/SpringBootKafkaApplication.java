package top.somliy.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 类名： @ClassName SpringBootKafkaApplication
 * 创建人：@author zhao dong
 * 类描述：@Description: 启动类
 * 创建时间: 2023/3/21 17:37
 */
@EnableScheduling
@SpringBootApplication
public class SpringBootKafkaApplication {
    /**
     * SpringBootKafkaApplication
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootKafkaApplication.class, args);
    }
}
