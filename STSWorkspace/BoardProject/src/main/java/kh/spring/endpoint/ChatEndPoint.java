package kh.spring.endpoint;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import kh.spring.config.HttpSessionConfigurator;

@ServerEndpoint(value="/chat", configurator = HttpSessionConfigurator.class)
public class ChatEndPoint {
	
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());
	private HttpSession hSession;
	private Gson gson = new Gson();
	
	@OnOpen
	public void onConnect(Session session, EndpointConfig config) {
		System.out.println("웹소켓 연결 확인");
		clients.add(session);
		hSession = (HttpSession)config.getUserProperties().get("hSession");
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("웹 소켓 연결 해제");
		clients.remove(session);
	}
	
	@OnError
	public void onError(Throwable t, Session session) {
		System.out.println("웹 소켓 통신 오류");
		clients.remove(session);
	}
	
	@OnMessage
	public void onMessage(String message) {
		Map<String, String> data = new HashMap<>();
		data.put("id", (String)hSession.getAttribute("loginID"));
		data.put("message", message);
		synchronized(clients) {
			for(Session item : clients) {
				try {
					item.getBasicRemote().sendText(gson.toJson(data));
				}catch(Exception e) {
				}
			}
		}
	}
}
