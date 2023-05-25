package kh.spring.repositories;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dto.FilesDTO;

@Repository
public class FilesDAO {
	
	@Autowired
	private SqlSessionTemplate db;
	
	public int insert(FilesDTO dto) {
		return db.insert("Files.insert", dto);
	}
	
	public List<FilesDTO> selectAll(){
		return db.selectList("Files.selectAll");
	}
}
