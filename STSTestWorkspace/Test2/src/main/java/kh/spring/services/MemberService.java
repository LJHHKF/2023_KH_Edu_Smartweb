package kh.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.spring.model.vo.MemberVO;
import kh.spring.repositories.MemberDAO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Transactional
	public int insertMember(MemberVO vo) {
		return memberDAO.insertMember(vo);
	}
}
