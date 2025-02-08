package top.somliy.localcache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.somliy.localcache.entity.TestEntity;
import top.somliy.localcache.manager.LocalCacheManager;

/**
 * 类名： @ClassName TestService 测试
 * 创建人：@author zhao dong
 * 类描述：@Description: 测试
 * 创建时间: 2024-11-20 14:10
 */
@Service
public class TestService {

    @Autowired
    private LocalCacheManager localCacheManager;

    public TestEntity getData(Integer key) {
        return localCacheManager.getData(key);
    }
}
