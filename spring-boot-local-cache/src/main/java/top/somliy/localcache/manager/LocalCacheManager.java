package top.somliy.localcache.manager;

import com.meitu.commons.cache.local.GuavaLocalCache;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import top.somliy.localcache.entity.TestEntity;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 类名： @ClassName LocalCacheManager 本地缓存
 * 创建人：@author zhao dong
 * 类描述：@Description: 本地缓存
 * 创建时间: 2024-11-20 14:15
 */
@Component
public class LocalCacheManager implements InitializingBean {

    private GuavaLocalCache<Integer, Optional<TestEntity>> commonDataCache;

    @Override
    public void afterPropertiesSet() throws Exception {
        commonDataCache = new GuavaLocalCache<Integer, Optional<TestEntity>>("common-data-cache", 500, 1, 2, TimeUnit.MINUTES,
                key -> getPopupCommonDataFromBackend(key));
    }

    private Optional<TestEntity> getPopupCommonDataFromBackend(Integer key) {
        TestEntity testEntity = new TestEntity();
        testEntity.setKey(key);
        long millis = System.currentTimeMillis();
        testEntity.setTime(millis);
        testEntity.setData(millis + "-" + key);
        Optional<TestEntity> res = Optional.of(testEntity);
        return res;
    }

    public TestEntity getData(Integer key) {
        Optional<TestEntity> testEntity = commonDataCache.get(key);
        return testEntity.orElse(null);
    }
}
