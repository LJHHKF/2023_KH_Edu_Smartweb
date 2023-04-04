package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessagesDAO;

@WebServlet("/DeleteMessage")
public class DeleteMessage extends HttpServlet {

//	@Override
//	public void init() {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		}catch(Exception e) {
//			e.printStackTrace();
//			System.out.println("ojdbc를 못 찾았습니다.");
//		}
//	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// JS에서 isNaN 및 공백 체크 후(=유효성 검사 후) 넘어오는 값이므로 어지간해선 오류 안 날 것.
		int id = Integer.parseInt(request.getParameter("deleteId"));
		try {
			MessagesDAO dao = new MessagesDAO();
			if(dao.delete(id) > 0) {
				System.out.println("삭제에 성공했습니다.");
			}else {
				System.out.println("삭제에 실패했습니다.");
			}
			response.sendRedirect("SelectMessage");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("db 삭제 사용 중 오류가 발생했습니다.");
			response.sendRedirect("error.html");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
