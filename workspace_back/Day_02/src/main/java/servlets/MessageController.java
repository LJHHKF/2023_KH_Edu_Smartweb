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

@WebServlet("*.message")
public class MessageController extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		String cmd = request.getRequestURI();

		try {
			if(cmd.equals("/insert.message")) {
				String writer = request.getParameter("writer");
				String msg = request.getParameter("msg");
				System.out.println("writer : " + writer);
				System.out.println("msg : " + msg);

				int result = MessagesDAO.getInstance().insert(new MessagesDTO(0, writer, msg));
				response.sendRedirect("index.html");
				
			}else if(cmd.equals("/select.message")){
				ArrayList<MessagesDTO> result = MessagesDAO.getInstance().select();
				request.setAttribute("list", result);
				request.getRequestDispatcher("list.jsp").forward(request, response);
				
			}else if(cmd.equals("/update.message")) {
				//id 값만 유효성 검사 후 넘어옴. 나머지는 문자열이라 무관하긴 함.
				int id = Integer.parseInt(request.getParameter("modifyId"));
				String writer = request.getParameter("modifyWriter");
				String message = request.getParameter("modifyMessage");
				int result = MessagesDAO.getInstance().update(new MessagesDTO(id, writer, message));
				response.sendRedirect("/select.message");
				
			}else if(cmd.equals("/delete.message")) {
				//유효성 검사 후 넘어옴.
				int id = Integer.parseInt(request.getParameter("deleteId"));
				int result = MessagesDAO.getInstance().delete(id); 
				response.sendRedirect("/select.message");
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
