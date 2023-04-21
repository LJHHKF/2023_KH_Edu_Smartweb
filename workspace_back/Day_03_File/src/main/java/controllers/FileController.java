package controllers;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.FilesDAO;
import dto.FilesDTO;


@WebServlet("*.file")
public class FileController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getRequestURI();
		System.out.println("요청 된 URL : " + cmd);
		try {
			if(cmd.equals("/uploadForm.file")) {
				response.sendRedirect("/file/upload.jsp");
			}else if(cmd.equals("/upload.file")) {
				String realPath = request.getServletContext().getRealPath("upload");
				System.out.println(realPath);
				File realPathFile = new File(realPath);
				if(!realPathFile.exists()) {
					realPathFile.mkdir();
				}
				//1024(1KB) * 1024(1MB) * 10(10MB)
				MultipartRequest multi = new MultipartRequest(request,realPath,1024*1024*10,"utf8",new DefaultFileRenamePolicy());
				
				String message = multi.getParameter("message");
				
				//업로드 시킬 때 당시의 원본 이름
				String oriName = multi.getOriginalFileName("file");
				//업로드 되어 RenamePolicy 영향을 받은 후 이름.
				String sysName = multi.getFilesystemName("file");
				// 부모가 되는 게시글의 시퀀스(parent_seq)
				
				FilesDAO.getInstance().insert(new FilesDTO(0, oriName, sysName, 0));
				response.sendRedirect("/");
			}else if(cmd.equals("/list.file")) {
				String realPath = request.getServletContext().getRealPath("upload");
				ArrayList<FilesDTO> list = FilesDAO.getInstance().selectAll();
				request.setAttribute("dtoList", list);
				request.getRequestDispatcher("/file/list.jsp").forward(request, response);
			}else if(cmd.equals("/download.file")) {
				String oriName = request.getParameter("oriName");
				oriName = new String(oriName.getBytes("utf8"), "ISO-8859-1"); //크롬이 ISO-8859-1. 다른 브라우저면 변경해줘야 함.
				response.reset();
				response.setHeader("Content-Disposition", "attachment;filename="+oriName);
				
				String uploadPath = request.getServletContext().getRealPath("upload");
				String sysName = request.getParameter("sysName");
				
				File target = new File(uploadPath + "/" + sysName);
				try(	FileInputStream fis = new FileInputStream(target);
						DataInputStream dis = new DataInputStream(fis);
						ServletOutputStream sos = response.getOutputStream();){
					byte[] fileContents = new byte[(int)target.length()];
					dis.readFully(fileContents);
					sos.write(fileContents);
					sos.flush();		
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
