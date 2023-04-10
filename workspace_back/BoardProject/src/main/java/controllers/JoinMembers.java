package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commons.EncryptionUtils;
import dao.MembersDAO;
import dto.MembersDTO;

@WebServlet("/JoinMembers")
public class JoinMembers extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//8개
		
		request.setCharacterEncoding("utf8");
		
		String id = request.getParameter("id");
		String pw = null;
		try {
			pw = EncryptionUtils.sha512(request.getParameter("pw"));
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("비밀번호 암호화 중 오류가 났습니다.");
			response.sendRedirect("error.html");
			return;
		}
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String zipcode = request.getParameter("zipcode");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		
		try {
			int result = MembersDAO.getInstance().insert(new MembersDTO(id, pw, name, phone, email, zipcode, addr1, addr2, null));
			if(result > 0) {
				System.out.println("회원가입 삽입 성공");
			}else {
				System.out.println("회원가입 삽입 실패");
			}
			response.sendRedirect("/index.jsp?state=a_j");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB 삽입 사용 중 오류가 났습니다.");
			response.sendRedirect("/error.html");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
