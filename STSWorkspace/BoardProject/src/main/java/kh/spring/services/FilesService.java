package kh.spring.services;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kh.spring.dto.FilesDTO;
import kh.spring.repositories.FilesDAO;

@Service
public class FilesService {
	
	@Autowired
	private FilesDAO filesDAO;
	
	//엄밀히 따지면 MultpartFile은 Web tier라서 여기오면 안되지만, 불필요한 코드 추가되는 것이 있으므로 트레이드 오프 따져서 어느정도 들어갈지-.
	public void insert(MultipartFile[] files, String realPath, int parent_seq) throws Exception {
//		int result = 0;
		
		File realPathFile = new File(realPath);
		if(!realPathFile.exists()) {realPathFile.mkdir();}
		
		if(files != null) {
			for(MultipartFile file : files) {
				if(file.isEmpty()) {continue;}
				
				String oriName = file.getOriginalFilename();
				String sysName = UUID.randomUUID() + "_" + oriName;
				
				file.transferTo(new File(realPath+"/"+sysName));
			    filesDAO.insert(new FilesDTO(0, oriName, sysName, parent_seq));				
			}
		}
//		return result;
	}
	
	public List<FilesDTO> selectByParentSeq(int seq){
		return filesDAO.selectByParentSeq(seq);
	}
}
