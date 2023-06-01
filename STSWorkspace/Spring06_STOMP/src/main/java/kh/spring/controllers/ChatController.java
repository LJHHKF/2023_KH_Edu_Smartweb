package kh.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import kh.spring.dto.ChatDTO;

@Controller
public class ChatController {
	
	@Autowired
	private SimpMessagingTemplate writer;
	
	@EventListener
	public void onOpen(SessionSubscribeEvent e) {
		System.out.println(SimpMessageHeaderAccessor.wrap(e.getMessage()).getDestination());
		
		System.out.println("연결 확인");
		writer.convertAndSend("/topic/test", "Hello STOMP");
	}
	
	@MessageMapping("/message")
	public void message(ChatDTO dto, SimpMessageHeaderAccessor smha) {
		writer.convertAndSend("/topic/" + "test", dto.getMessage());
	}
	
//	@SendTo("/topic/test")
//	@MessageMapping("/message")
//	public ChatDTO message(ChatDTO dto, SimpMessageHeaderAccessor smha) {
//		//String loginID = (String)smha.getSessionAttributes().get("loginID");
//		System.out.println(dto.getName() +" : "+ dto.getMessage());
//		return dto;
//	}
	
	
}
