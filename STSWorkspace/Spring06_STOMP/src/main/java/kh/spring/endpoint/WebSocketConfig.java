package kh.spring.endpoint;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic");
		// 구독할 수 있는 Endpoint(Channel) URL의 prefix [Server -> Client ]
		
		registry.setApplicationDestinationPrefixes("/app");
		//클라이언트가 메세지를 보낼 때 사용할 URL의 prefix [ Client -> Server ]
	}
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat").addInterceptors(new HttpSessionHandshakeInterceptor()).setAllowedOrigins("*");
		//*. 어디에서나 다 접근 허용. CORS 까지 허용.
		//여기에 본인의 IP를 적으면 자신의 IP(서버)로만 접근 가능. 기본 설정이 이것일 것임.
		//회사 IP만 알고 있다면, 회사 내부 IP에만 허용한다 같은 방식도 가능.
	}
}

//STOMP - 구독 시스템
