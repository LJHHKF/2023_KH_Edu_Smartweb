package kh.spring.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MessageDTO;

@Repository
public class MessageDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insert(String name, String message) {
		Map<String, String> param = new HashMap<>();
		param.put("name", name);
		param.put("message", message);
		return mybatis.insert("Message.insert", param);
	}
	
	public List<MessageDTO> selectAll() {
		return mybatis.selectList("Message.selectAll");
	}
}
