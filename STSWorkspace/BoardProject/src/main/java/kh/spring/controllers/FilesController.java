package kh.spring.controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.services.FilesService;

@Controller
@RequestMapping("/files/")
public class FilesController {
	
	@Autowired
	private FilesService filesService;
	@Autowired
	private HttpSession session;
	
	@RequestMapping("download")
	public void download(String oriName, String sysName, HttpServletResponse response) throws Exception{
		String realPath = session.getServletContext().getRealPath("upload");
		File target = new File(realPath+"/"+sysName);
		// 다운 받을 파일을 선택하여 File 객체로 생성
		
		//크롬이란 전제 하에 인코딩 방식을 변환시켜주고 있는 것.
		oriName = new String(oriName.getBytes("utf8"), "ISO-8859-1");
		response.reset();
		response.setHeader("Content-Disposition", "attachment;filename="+oriName);
		//응답 헤더에 보내려는 데이터가 첨부파일임을 밝히고, 적절히 인코딩 된 파일 이름을 지정
		
		try(	DataInputStream dis = new DataInputStream(new FileInputStream(target));
				DataOutputStream dos = new DataOutputStream(response.getOutputStream());){
			byte[] fileContents = dis.readAllBytes();
			dos.write(fileContents);
			dos.flush();
		}
		// 타겟 파일의 내용을 모두 추출해 response stream 으로 직접 출력
	}
}
