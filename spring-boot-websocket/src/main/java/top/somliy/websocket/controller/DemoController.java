package top.somliy.websocket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.somliy.websocket.websocket.properties.WebSocketProperty;

/**
 * 类名： @ClassName DemoController demo
 * 创建人：@author zhao dong
 * 类描述：@Description: demo
 * 创建时间: 2023/10/8 14:13
 */
@RestController
@RequestMapping("front/demo")
public class DemoController {

    @Autowired
    private WebSocketProperty webSocketProperty;

    @PostMapping("demo01")
    public void test() {
        Long timeoutPeriod = webSocketProperty.getTimeoutPeriod();
        System.out.println(timeoutPeriod);
    }

}
