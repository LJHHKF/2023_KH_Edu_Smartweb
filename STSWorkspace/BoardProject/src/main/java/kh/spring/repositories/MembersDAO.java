package kh.spring.repositories;

import org.springframework.beans.factory.annotation.Autowired;
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
}
