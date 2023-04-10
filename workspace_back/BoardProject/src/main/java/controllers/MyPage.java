package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MembersDAO;
import dto.MembersDTO;


@WebServlet("/MyPage")
public class MyPage extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MembersDTO myDto = null; 
		try {
			myDto = MembersDAO.getInstance().getMyDto((String)request.getSession().getAttribute("loginID"));
			request.setAttribute("myDto", myDto);
			request.getRequestDispatcher("/member/mypage.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("마이페이지 조회 중 오류");
			response.sendRedirect("/error.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
