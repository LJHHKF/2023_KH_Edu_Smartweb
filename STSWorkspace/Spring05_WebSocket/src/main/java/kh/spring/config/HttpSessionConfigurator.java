package kh.spring.config;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

public class HttpSessionConfigurator extends Configurator {
	
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		// WebSocket 연결 과정에 포함될 HttpSession을 획득
		HttpSession session = (HttpSession)request.getHttpSession();
		// WebSocket으로 전달되는 데이터 맵 안에 HttpSession 을 첨부
		sec.getUserProperties().put("hSession", session);
	}
}
