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

@WebServlet("/UpdateContacts")
public class UpdateContacts extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		//공백 및 NaN형 포함한 유효성 검사를 JS에서 처리하고 옴.
		int id = Integer.parseInt(request.getParameter("updateID"));
		String name = request.getParameter("updateName");
		String contact = request.getParameter("updateContact");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		Timestamp birthday = null;
		try {
			birthday = new Timestamp(dateFormat.parse(request.getParameter("updateBirthday")).getTime());
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("날짜 문자 변환 중 오류");
			response.sendRedirect("error.html");
		}
		try {
			if(ContactsDAO.getInstance().update(new ContactsDTO(id, name, contact, birthday)) > 0) {
				System.out.println("수정 성공");
			}else {
				System.out.println("수정 실패");
			}
			response.sendRedirect("ReadContacts");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB 수정 사용 중 오류");
			response.sendRedirect("error.html");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
