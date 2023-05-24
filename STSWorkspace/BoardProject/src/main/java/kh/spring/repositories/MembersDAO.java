package kh.spring.repositories;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MembersDTO;

@Repository
public class MembersDAO {
//	@Autowired
//	private JdbcTemplate jdbc;
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public boolean isIdExist(String id) {
		return mybatis.selectOne("Members.isIdExist", id);
	}
	
	public int insert(MembersDTO dto) {
		return mybatis.insert("Members.insert", dto);
	}
	
	public boolean login(String id, String pw) {
		Map<String, String> param = new HashMap<>();
		param.put("id", id);
		param.put("pw", pw);
		return mybatis.selectOne("Members.login", param);
	}
	
	public int delete(String id) {
		return mybatis.delete("Members.delete", id);
	}
	
	public MembersDTO selectById(String id) {
		return mybatis.selectOne("Members.selectByID", id);
	}
	
	public int update(MembersDTO dto) {
		return mybatis.update("Members.update", dto);
	}
}
