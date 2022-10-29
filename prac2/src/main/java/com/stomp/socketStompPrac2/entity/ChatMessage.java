package com.stomp.socketStompPrac2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK
    }

    private MessageType type; // 메세지 타입

    private String roomId; // 채팅방 ID

    private String sender; // 보내는 사람

    private String message; // 채팅 내용
}
