package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContactsDAO;
import dto.ContactsDTO;

@WebServlet("/CreateContacts")
public class CreateContacts extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		
		String name = request.getParameter("name");
		String contact = request.getParameter("contact");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//		System.out.println(request.getParameter("birthday"));
		Timestamp birthday = null;
		try {
			birthday = new Timestamp(dateFormat.parse(request.getParameter("birthday")).getTime());
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("날짜 문자 변환 중 오류");
		}
		
		try {
			ContactsDAO dao = new ContactsDAO();
			if(dao.insert(new ContactsDTO(0, name, contact, birthday)) > 0) {
				System.out.println("입력 성공");
			}else {
				System.out.println("입력 실패");
			}
			response.sendRedirect("index.html");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("db 입력 사용 중 오류");
			response.sendRedirect("error.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
