package top.somliy.currentlimiting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.somliy.currentlimiting.annotation.Limit;
import top.somliy.currentlimiting.base.result.Result;
import top.somliy.currentlimiting.enums.LimitType;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类名： @ClassName TestController 测试
 * 创建人：@author zhao dong
 * 类描述：@Description: 测试
 * 创建时间: 2023/7/25 17:45
 */
@RestController
@RequestMapping("test")
public class TestController {
    private static final AtomicInteger ATOMIC_INTEGER_1 = new AtomicInteger();
    private static final AtomicInteger ATOMIC_INTEGER_2 = new AtomicInteger();
    private static final AtomicInteger ATOMIC_INTEGER_3 = new AtomicInteger();

    /**
     * 普通限流
     *
     * @return 结果
     */
    @Limit(key = "limitTest", period = 10, count = 3)
    @GetMapping("limitTest")
    public Result<Integer> testLimiter() {
        return Result.success(ATOMIC_INTEGER_1.incrementAndGet());
    }

    /**
     * port限制
     *
     * @return 结果
     */
    @Limit(key = "limitTestPort", period = 10, count = 3, limitType = LimitType.PORT)
    @GetMapping("limitTestPort")
    public Result<Integer> limitTestPort() {
        return Result.success(ATOMIC_INTEGER_2.incrementAndGet());
    }

    /**
     * Ip限制
     *
     * @return 结果
     */
    @Limit(key = "limitTestIp", period = 10, count = 3, limitType = LimitType.IP)
    @GetMapping("limitTestIp")
    public Result<Integer> limitTestIp() {
        return Result.success(ATOMIC_INTEGER_3.incrementAndGet());
    }
}
