package top.somliy.websocket.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名： @ClassName DemoController demo
 * 创建人：@author zhao dong
 * 类描述：@Description: demo
 * 创建时间: 2023/10/8 14:13
 */
@RestController
@RequestMapping("front/demo")
public class DemoController {

    public static void main(String[] args) {
        System.out.println("123");
    }

    @PostMapping("send")
    public void send() {

    }
}
