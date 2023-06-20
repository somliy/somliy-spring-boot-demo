package top.somliy.serialize.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.somliy.serialize.entity.DateEntity;

/**
 * 类名： @ClassName SerializeController 序列化
 * 创建人：@author zhao dong
 * 类描述：@Description: 序列化
 * 创建时间: 2023/6/19 17:33
 */
@RestController
@RequestMapping("serialize")
public class SerializeController {

    /**
     * 日期序列化
     *
     * @param dateEntity 实体
     * @return 结果
     */
    @PostMapping("testDate")
    public DateEntity testDate(@RequestBody DateEntity dateEntity) {
        return dateEntity;
    }
}
