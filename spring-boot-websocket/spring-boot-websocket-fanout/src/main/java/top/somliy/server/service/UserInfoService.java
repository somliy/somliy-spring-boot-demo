package top.somliy.server.service;

import org.springframework.stereotype.Service;

/**
 * 类名： @ClassName UserInfoService 用户信息
 * 创建人：@author zhao dong
 * 类描述：@Description: 用户信息
 * 创建时间: 2023/11/3 17:46
 */
@Service
public class UserInfoService {
    /**
     * 验证连接key正确
     *
     * @param key key
     * @return 结果
     */
    public boolean judgeUserKey(String key) {
        return "123".equals(key) || "12".equals(key);
    }
}
