package kh.spring.repositories;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.model.vo.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insertMember(MemberVO vo) {
		return mybatis.insert("mybatis.insertMember", vo);
	}
	
}
