package com.stomp.socketStompPrac2.service;

import com.stomp.socketStompPrac2.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private Map<String, ChatRoom> chatRooms;

    // 의존관게 주입완료되면 실행되는 코드
    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    // 채팅방 불러오기
    public List<ChatRoom> findAllRoom() {
        List<ChatRoom> result = new ArrayList<>(chatRooms.values()); // 채팅방 최근 생성 순으로 반환
        Collections.reverse(result);

        return result;
    }

    // 채팅방 하나 불러오기
    public ChatRoom findById(String roomId) {
        return chatRooms.get(roomId);
    }

    // 채팅방 생성
    public ChatRoom createRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}
