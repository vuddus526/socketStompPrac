package com.stomp.socketStompPrac2.controller;

import com.stomp.socketStompPrac2.entity.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;

    // @MessageMapping()의 경로가 "/chat/message"이지만
    // ChatConfig의 setApplicationDestinationPrefixes()를 통해 prefix를 "/app"으로 해줬기 때문에
    // 실제 경로는 "/app/chat/message"가 된다
    // 클라이언트에서 "/app/chat/message"의 경로로 메시지를 보내는 요청을 하면
    // 메시지 Controller가 받아서 "topic/chat/room/{roomId}"를 구독하고 있는 클라이언트에게 메시지를 전달하게 된다
    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender()+"님이 입장하였습니다.");
        }
        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(), message);
    }
}
