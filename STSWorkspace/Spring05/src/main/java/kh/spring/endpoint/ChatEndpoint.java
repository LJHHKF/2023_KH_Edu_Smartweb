package kh.spring.endpoint;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.ApplicationContext;

import com.google.gson.Gson;

import kh.spring.config.HttpSessionConfigurator;
import kh.spring.config.SpringProvider;
import kh.spring.dto.ChatDTO;
import kh.spring.services.ChatService;

@ServerEndpoint(value="/chat", configurator = HttpSessionConfigurator.class)
public class ChatEndpoint {

	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());
	//private static EvictingQueue<ChatDTO> messages = EvictingQueue.create(100);
	private HttpSession hSession;
	private Gson gson = new Gson();
	private ApplicationContext ctx = SpringProvider.getApplicationContext();

	@OnOpen
	public void onConnect(Session session, EndpointConfig config) {
		System.out.println("웹소켓 연결 확인");
		clients.add(session);
		hSession = (HttpSession)config.getUserProperties().get("hSession");
		
//		try {
//			session.getBasicRemote().sendText(gson.toJson(messages));
//		}catch(Exception e) {
//			try {
//				session.getBasicRemote().sendText(gson.toJson(new ChatDTO(0 ,"System", "지난 메시지 기록을 불러오는데 실패했습니다.", new Timestamp(System.currentTimeMillis()))));				
//			}catch(Exception e2) {
//			}
//		}
		
		try {
			session.getBasicRemote().sendText(gson.toJson(ctx.getBean(ChatService.class).selectAll()));
		}catch(Exception e) {
			try {
				session.getBasicRemote().sendText(gson.toJson(new ChatDTO(0 ,"System", "지난 메시지 기록을 불러오는데 실패했습니다.", new Timestamp(System.currentTimeMillis()))));				
			}catch(Exception e2) {
			}
		}
		
	}

	@OnMessage
	public void onMessage(String message) {
		
		String id = (String)hSession.getAttribute("ip");
		ChatDTO dto = new ChatDTO(0, id, message, null);
		//messages.add(dto);
		
		ChatService service = ctx.getBean(ChatService.class);
		service.insert(dto);
		
//		Map<String, String> data = new HashMap<>();
//		data.put("ip", (String)hSession.getAttribute("ip"));
//		data.put("message", message);
		//String ip = (String)hSession.getAttribute("ip");
		synchronized(clients) {			
			for(Session item : clients) {
				try {
					item.getBasicRemote().sendText(gson.toJson(dto)); //핵심						
				}catch(Exception e) {
				}
			}		
		}
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
}
