package top.somliy.websocket.service;

import org.springframework.stereotype.Service;

/**
 * 类名： @ClassName DemoService demo
 * 创建人：@author zhao dong
 * 类描述：@Description: demo
 * 创建时间: 2023/10/9 14:28
 */
@Service
public class DemoService {
    /**
     * 判断用户key是否正确
     *
     * @param key key
     * @return 结果
     */
    public boolean judgeUserKey(String key) {
        return true;
    }
}
