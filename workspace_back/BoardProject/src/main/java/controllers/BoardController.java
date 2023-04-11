package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardDTO;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		String cmd = request.getRequestURI();
		
		try {
			if(cmd.equals("/list.board")) {
				ArrayList<BoardDTO> list = BoardDAO.getInstance().readAll();
				request.setAttribute("list", list);
				request.getRequestDispatcher("/board/list.jsp").forward(request, response);
			}else if(cmd.equals("/write.board")) {
				String writer =(String)request.getSession().getAttribute("loginID");
				String title = request.getParameter("title");
				String contetns = request.getParameter("contents");
				int result = BoardDAO.getInstance().create(new BoardDTO(0, writer, title, contetns, 0, null));
				if(result > 0) {
					System.out.println("삽입 성공");
				}else {
					System.out.println("삽입 실패");
				}
				response.sendRedirect("/list.board");
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
