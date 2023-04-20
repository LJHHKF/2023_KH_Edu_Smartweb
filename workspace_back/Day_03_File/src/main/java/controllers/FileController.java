package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
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
