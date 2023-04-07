package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessagesDAO;
import dto.MessagesDTO;

@WebServlet("/SelectMessage")
public class SelectMessage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf8");
		ArrayList<MessagesDTO> result = null;
		try {
			result = MessagesDAO.getInstance().select();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB 조회 사용 중 오류가 났습니다.");
			response.sendRedirect("error.html");
		}
		request.setAttribute("list", result);
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
