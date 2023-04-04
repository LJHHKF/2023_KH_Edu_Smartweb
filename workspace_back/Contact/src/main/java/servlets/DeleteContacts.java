package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContactsDAO;

@WebServlet("/DeleteContacts")
public class DeleteContacts extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//공백 및 NaN형 포함한 유효성 검사를 JS에서 처리하고 옴.
		int id = Integer.parseInt(request.getParameter("deleteID"));
		try {
			ContactsDAO dao = new ContactsDAO();
			if(dao.delete(id) > 0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
			response.sendRedirect("ReadContacts");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB 삭제 사용 중 오류");
			response.sendRedirect("error.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
