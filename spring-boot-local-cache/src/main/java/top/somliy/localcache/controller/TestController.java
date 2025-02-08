package top.somliy.localcache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.somliy.localcache.base.result.Result;
import top.somliy.localcache.entity.TestEntity;
import top.somliy.localcache.service.TestService;

/**
 * 类名： @ClassName TestController 测试
 * 创建人：@author zhao dong
 * 类描述：@Description: 测试
 * 创建时间: 2024-11-20 14:02
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestService testService;

    /**
     * 测试
     *
     * @return 结果
     */
    @GetMapping("getData")
    public Result<TestEntity> getData(@RequestParam(name = "key", defaultValue = "0") Integer key) {
        return Result.success(testService.getData(key));
    }
}
