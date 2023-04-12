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
				list.get(0).getFormedJoinDate_list();
				list.get(2).getFormedJoinDate_list();
				request.setAttribute("list", list);
				request.getRequestDispatcher("/board/list.jsp").forward(request, response);
			}else if(cmd.equals("/write.board")) {
				String writer =(String)request.getSession().getAttribute("loginID");
				String title = request.getParameter("title");
				String contetns = request.getParameter("contents");
				int result = BoardDAO.getInstance().create(new BoardDTO(0, writer, title, contetns, 0, null));
				response.sendRedirect("/list.board");
			}else if(cmd.equals("/view.board")) {
				int seq = Integer.parseInt(request.getParameter("seq"));
				BoardDTO dto = BoardDAO.getInstance().readOne(seq);
				request.setAttribute("dto", dto);
				request.getRequestDispatcher("/board/viewContent.jsp").forward(request, response);
			}else if(cmd.equals("/update.board")) {
				int seq = Integer.parseInt(request.getParameter("seq"));
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
				int result = BoardDAO.getInstance().update(seq, title, contents);
				BoardDTO dto = BoardDAO.getInstance().readOne(seq);
				request.setAttribute("dto", dto);
				request.getRequestDispatcher("/board/viewContent.jsp").forward(request, response);
			}else if(cmd.equals("/delete.board")) {
				int seq = Integer.parseInt(request.getParameter("seq"));
				int result = BoardDAO.getInstance().delete(seq);
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
