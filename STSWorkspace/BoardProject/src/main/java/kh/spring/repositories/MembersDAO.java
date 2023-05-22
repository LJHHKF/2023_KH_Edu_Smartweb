package kh.spring.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MembersDTO;

@Repository
public class MembersDAO {
	@Autowired
	private JdbcTemplate jdbc;
	
	public boolean isIdExist(String id) {
		String sql = "select count(*) from MEMBERS where ID=?";
		return jdbc.queryForObject(sql, Boolean.class, id);
	}
	
	public int insert(MembersDTO dto) {
		String sql = "insert into MEMBERS(ID, PW, NAME, PHONE, EMAIL, ZIPCODE, ADDRESS1, ADDRESS2, JOIN_DATE)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		return jdbc.update(sql, dto.getId(), dto.getPw(), dto.getName(), dto.getPhone(), dto.getEmail(), dto.getZipcode(), dto.getAddress1(), dto.getAddress2());
	}
	
	public boolean login(String id, String pw) {
		String sql = "select count(*) from MEMBERS where ID=? and PW=?";
		return jdbc.queryForObject(sql, Boolean.class, id, pw);
	}
	
	public int delete(String id) {
		String sql = "delete from MEMBERS where ID=?";
		return jdbc.update(sql, id);
	}
	
	public MembersDTO selectById(String id) {
		String sql = "select * from MEMBERS where ID=?";
		return jdbc.queryForObject(sql, new BeanPropertyRowMapper<MembersDTO>(MembersDTO.class), id);
	}
	
	public int update(MembersDTO dto) {
		String sql = "update MEMBERS"
				+ " set PW=?, NAME=?, PHONE=?, EMAIL=?, ZIPCODE=?, ADDRESS1=?, ADDRESS2=?"
				+ " where ID=?";
		return jdbc.update(sql, dto.getPw(), dto.getName(), dto.getPhone(), dto.getEmail(), dto.getZipcode(), dto.getAddress1(), dto.getAddress2(), dto.getId());
	}
}
