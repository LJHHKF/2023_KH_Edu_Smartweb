package kh.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.dto.ChatDTO;
import kh.spring.repositories.ChatDAO;

@Service
public class ChatService {
	
	@Autowired
	private ChatDAO chatDAO;
	
	public int insert(ChatDTO dto) {
		return chatDAO.insert(dto);
	}
	
	public List<ChatDTO> selectAll(){
		return chatDAO.selectAll();
	}

}
