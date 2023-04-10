package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MembersDAO;

@WebServlet("/IdCheck")
public class IdCheck extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		boolean result = false;
		try {
			result = MembersDAO.getInstance().isIdExist(id);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB ID 조회 사용 중 오류가 났습니다.");
			response.sendRedirect("/error.html");
		}
		request.setAttribute("isDup", result);
		request.getRequestDispatcher("/member/dupresult.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
