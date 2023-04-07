package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessagesDAO;
import dto.MessagesDTO;

@WebServlet("/InsertMessage")
public class InsertMessage extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf8");
		String writer = request.getParameter("writer");
		String msg = request.getParameter("msg");
		System.out.println("writer : " + writer);
		System.out.println("msg : " + msg);
		
		try {
			int result = MessagesDAO.getInstance().insert(new MessagesDTO(0, writer, msg));
			if(result > 0) {
				System.out.println("입력 성공");
			}else {
				System.out.println("입력 실패");
			}
			response.sendRedirect("index.html");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB 삽입 사용 중 오류가 났습니다.");
			response.sendRedirect("error.html");
		}
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
