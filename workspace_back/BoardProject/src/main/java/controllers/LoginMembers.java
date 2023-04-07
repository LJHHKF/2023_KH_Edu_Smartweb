package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commons.EncryptionUtils;
import dao.MembersDAO;

@WebServlet("/LoginMembers")
public class LoginMembers extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		String id = request.getParameter("id");
		String pw = null;
		try {
			pw = EncryptionUtils.sha512(request.getParameter("pw"));
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("암호화 중 오류 발생");
			response.sendRedirect("error.html");
		}
		
		try {
			boolean result = MembersDAO.getInstance().login(id, pw);
			System.out.println("로그인 성공 여부 : " + result);
			response.sendRedirect("index.jsp");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB 로그인 사용 중 오류");
			response.sendRedirect("error.html");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
