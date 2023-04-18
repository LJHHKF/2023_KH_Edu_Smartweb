package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dao.ReplyDAO;
import dto.BoardDTO;
import dto.ReplyDTO;

/**
 * Servlet implementation class ReplyController
 */
@WebServlet("*.reply")
public class ReplyController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		String cmd = request.getRequestURI();
		
		try {
			//select는 BoardController에서 viewContets.jsp 할 떄 처리
			if(cmd.equals("/create.reply")) {
				int parent_seq = Integer.parseInt(request.getParameter("parent_seq"));
				String writer = request.getParameter("writer");
				String contents = request.getParameter("contents");
				int result = ReplyDAO.getInstance().insert(new ReplyDTO(0, writer, contents, null, parent_seq));
				BoardDTO dto = BoardDAO.getInstance().readOne(parent_seq);
				ArrayList<ReplyDTO> replyList = ReplyDAO.getInstance().selectPSeq(parent_seq);
				request.setAttribute("dto", dto);
				request.setAttribute("replyList", replyList);
				request.getRequestDispatcher("/board/viewContent.jsp").forward(request, response);
			}else if(cmd.equals("/delete.reply")){
				int parent_seq = Integer.parseInt(request.getParameter("parent_seq"));
				int deleteSeq = Integer.parseInt(request.getParameter("deleteSeq"));
				int result = ReplyDAO.getInstance().delete(deleteSeq);
				BoardDTO dto = BoardDAO.getInstance().readOne(parent_seq);
				ArrayList<ReplyDTO> replyList = ReplyDAO.getInstance().selectPSeq(parent_seq);
				request.setAttribute("dto", dto);
				request.setAttribute("replyList", replyList);
				request.getRequestDispatcher("/board/viewContent.jsp").forward(request, response);
			}else if(cmd.equals("/update.reply")) {
				int parent_seq = Integer.parseInt(request.getParameter("parent_seq"));
				int updateSeq = Integer.parseInt(request.getParameter("updateSeq"));
				String contents = request.getParameter("contents");
				int result = ReplyDAO.getInstance().update(updateSeq, contents);
				BoardDTO dto = BoardDAO.getInstance().readOne(parent_seq);
				ArrayList<ReplyDTO> replyList = ReplyDAO.getInstance().selectPSeq(parent_seq);
				request.setAttribute("dto", dto);
				request.setAttribute("replyList", replyList);
				request.getRequestDispatcher("/board/viewContent.jsp").forward(request, response);
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
