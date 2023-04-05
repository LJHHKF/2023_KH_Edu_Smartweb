package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessagesDAO;
import dto.MessagesDTO;

@WebServlet("/ModifyMessage")
public class ModifyMessage extends HttpServlet {
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		//id 값만 유효성 검사 후 넘어옴. 나머지는 문자열이라 무관하긴 함.
		int id = Integer.parseInt(request.getParameter("modifyId"));
		String writer = request.getParameter("modifyWriter");
		String message = request.getParameter("modifyMessage");
		try {
			if(MessagesDAO.getInstance().update(new MessagesDTO(id, writer, message)) > 0) {
				System.out.println("수정에 성공했습니다.");
			}else {
				System.out.println("수정에 실패했습니다.");
			}
			response.sendRedirect("SelectMessage");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB 수정 사용 중 오류가 났습니다.");
			response.sendRedirect("error.html");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
