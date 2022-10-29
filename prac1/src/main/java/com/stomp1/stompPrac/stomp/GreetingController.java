package com.stomp1.stompPrac.stomp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @MessageMapping("/hello") // 목적지가 path와 일치하는 메시지를 수신했을경우 해당 메서드를 호출
    @SendTo("/topic/greetings") // 해당 path의 모든 구독자들에게 반환값이 브로드캐스트된다
    public Greeting greeting (HelloMessage helloMessage) throws Exception {
        Thread.sleep(1000); // 1초 쉬었다가 전환되도록 지연처리
        return new Greeting(HtmlUtils.htmlEscape(helloMessage.getName()));
    }
}