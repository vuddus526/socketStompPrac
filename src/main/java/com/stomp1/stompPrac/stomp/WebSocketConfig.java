package com.stomp1.stompPrac.stomp;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration // spring configuration 클래스임을 명시한다
@EnableWebSocketMessageBroker // WebSocket의 메시지 브로커에 의해 웹소켓 메시지를 처리할 수 있다 의미
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // enableSimpleBroker()를 호출해서 간단한 메모리 기반의 메시지 브로커가
    // /topic prefix가 붙은 수신처(해당 prefix를 구독중인)의 클라이언트로 메시지를 전달할 수 있도록 한다
    // 또한 @MessageMapping 이 붙은 클래스의 Path(/app)도 정의한다
    // 예를 들어, /app/hello 는 GreetingCotroller.greeting() 메서드가 처리하도록 매핑된 엔드포인트

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    // registerStompEndpoints() 메서드는 /gs-guide-websocket 엔드포인트를 등록해서
    // 웹소켓을 사용할 수 없는 경우 ( ex/ 브라우저 미지원)
    // 웹소켓 대신 다른 전송방식을 사용할 수 있도록 SockJS 옵션을 활성화 한다
    // gs-guide-websocket에 연결해서 사용 가능한 최적의 전송방식
    // ex) 웹소켓, xhr-streaming, xhr-polling, etc / 을 사용할 수 있도록 시도 한다

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }
}
