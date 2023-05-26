package kh.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import commons.SecurityUtils;
import kh.spring.dto.MembersDTO;
import kh.spring.repositories.MembersDAO;

@Service
public class MembersService {
	
	@Autowired
	private MembersDAO membersDAO;
	
	public int insert(MembersDTO dto) throws Exception{
		dto.setPw(SecurityUtils.sha512(dto.getPw()));
		return membersDAO.insert(dto);
	}
	
	public int delete(String id) {
		return membersDAO.delete(id);
	}
	
	public int update(MembersDTO dto) throws Exception{
		dto.setPw(SecurityUtils.sha512(dto.getPw()));
		return membersDAO.update(dto);
	}
	
	public MembersDTO selectById(String id) {
		return membersDAO.selectById(id);
	}
	
	public boolean isIdExist(String id) {
		return membersDAO.isIdExist(id);
	}
	
	public boolean login(String id, String pw) throws Exception {
		pw = SecurityUtils.sha512(pw);
		return membersDAO.login(id, pw);
	}
	
	
	
}
