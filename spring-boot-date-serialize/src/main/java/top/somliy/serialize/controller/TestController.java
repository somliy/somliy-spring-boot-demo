package top.somliy.serialize.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 类名： @ClassName TestController 测试
 * 创建人：@author zhao dong
 * 类描述：@Description: 测试
 * 创建时间: 2023/7/25 17:45
 */
@RestController
@RequestMapping("test")
public class TestController {

    @PostMapping(value = "/testUpload.json")
    public String uploadTest(String name, @RequestParam(value = "multipartFile") MultipartFile multipartFile) {
        System.out.println(name);
        System.out.println(multipartFile.getOriginalFilename());

        return "success";
    }
}
