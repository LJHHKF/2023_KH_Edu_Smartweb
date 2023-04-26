package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.FilesDAO;
import dto.ContactDTO;
import dto.FilesDTO;

@WebServlet("*.ajax")
public class AjaxController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getRequestURI();
		Gson g = new Gson();
		
		if(cmd.equals("/exam01.ajax")) {
			System.out.println("비동기 통신 요청 확인");
		}else if(cmd.equals("/exam02.ajax")) {
			String fruit = request.getParameter("fruit");
			String music = request.getParameter("music");
			
			System.out.println(fruit + " : " + music);
		}else if(cmd.equals("/exam03.ajax")) {
			response.getWriter().append("Hello AJAX");
		}else if(cmd.equals("/exam04.ajax")) {
			String[] arr = new String[] {"Apple", "Orange", "Mango"};
			
			//라이브러리 활용 직렬화. 5번서도 쓸거니 gson은 위에 올림.
			String resp = g.toJson(arr);
			
			//배열->문자열 직렬화 과정
//			StringBuilder sb = new StringBuilder();
//			sb.append("[");
//			for(int i = 0; i < arr.length; i++) {
//				sb.append("\""+arr[i]+"\"");
//				if(i != arr.length-1) {
//					sb.append(",");
//				}
//			}
//			sb.append("]");
//          String resp = sb.toString();
			
			response.getWriter().append(resp);
		}else if(cmd.equals("/exam05.ajax")) {
			ContactDTO dto = new ContactDTO(100, "Ryan", "01012344321");
			//라이브러리 활용 직렬화
			String resp = g.toJson(dto);
			//클래스 -> 문자열 직렬화 하려면 []가 아닌 {}.
			response.getWriter().append(resp);
		}else if(cmd.equals("/exam06.ajax")) {
			List<ContactDTO> list = new ArrayList<>();
			list.add(new ContactDTO(1001, "Tom", "01012344321"));
			list.add(new ContactDTO(1002, "Ryan", "01043214000"));
			list.add(new ContactDTO(1003, "becket", "01012123232"));
			
			String resp = g.toJson(list);
			response.getWriter().append(resp);
		}else if(cmd.equals("/exam07.ajax")) {
			String[] arr = new String[] {"Apple", "Orange", "Mango"};
			ContactDTO dto = new ContactDTO(100, "Ryan", "01012344321");
			
//			String arrJson = g.toJson(arr);
//			String dtoJson = g.toJson(dto);
//			
//			JsonObject resp = new JsonObject();
//			resp.addProperty("arr", arrJson);
//			resp.addProperty("dto", dtoJson);
			
			JsonObject jsonObject = new JsonObject();
			jsonObject.add("arr", g.toJsonTree(arr));
			jsonObject.add("dto", g.toJsonTree(dto));
			
			response.getWriter().append(g.toJson(jsonObject));
		}else if(cmd.equals("/exam08.ajax")) {
			String realPath = request.getServletContext().getRealPath("upload");
			System.out.println(realPath);
			File realPathFile = new File(realPath);
			if(!realPathFile.exists()) {
				realPathFile.mkdir();
			}
			int maxSize = 1024 * 1024 * 10; //1024(1KB) * 1024(1MB) * 10(10MB)
			MultipartRequest multi = new MultipartRequest(request,realPath,maxSize,"utf8",new DefaultFileRenamePolicy());
			
//			Enumeration<String> names = multi.getFileNames();
//			while(names.hasMoreElements()) {
//				String fileName = names.nextElement();
//				if(multi.getFile(fileName) != null) {
//					String oriName = multi.getOriginalFileName(fileName);
//					String sysName = multi.getFilesystemName(fileName);
//					FilesDAO.getInstance().insert(new FilesDTO(0, oriName, sysName, 0));
//				}
//			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
