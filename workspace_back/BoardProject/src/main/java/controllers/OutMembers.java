package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MembersDAO;


@WebServlet("/OutMembers")
public class OutMembers extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			int result = MembersDAO.getInstance().delete((String)request.getSession().getAttribute("loginID"));
			if(result > 0) {
				request.getSession().invalidate();
				System.out.println("회원 탈퇴에 성공했습니다.");
			}else {
				System.out.println("회원 탈퇴에 실패했습니다.");
			}
			response.sendRedirect("/index.jsp");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB 삭제 접근 중 오류가 났습니다.");
			response.sendRedirect("/error.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
