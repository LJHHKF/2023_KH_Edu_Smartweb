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
import dto.BoardNaviDTO;
import dto.ReplyDTO;
import statics.Settings;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		String cmd = request.getRequestURI();
		
		try {
			if(cmd.equals("/list.board")) {
				int currentPage = Integer.parseInt(request.getParameter("cpage"));
				int start = currentPage * Settings.BOARD_RECORD_COUNT_PER_PAGE - (Settings.BOARD_NAVI_COUNT_PER_PAGE -1);
				int end = currentPage * Settings.BOARD_RECORD_COUNT_PER_PAGE;
				ArrayList<BoardDTO> list = BoardDAO.getInstance().selectBound(start, end);
//				String pageNavi = BoardDAO.getInstance().getPageNavi(currentPage);
				BoardNaviDTO pageNavi = BoardDAO.getInstance().getPageNavi(currentPage);
				
				request.setAttribute("list", list);
				request.getSession().setAttribute("curPage", currentPage);
				request.setAttribute("navi", pageNavi);
				request.getRequestDispatcher("/board/list.jsp").forward(request, response);
			}else if(cmd.equals("/write.board")) {
				String writer =(String)request.getSession().getAttribute("loginID");
				String title = request.getParameter("title");
				String contetns = request.getParameter("contents");
				int result = BoardDAO.getInstance().create(new BoardDTO(0, writer, title, contetns, 0, null));
				response.sendRedirect("/list.board?cpage=1");
			}else if(cmd.equals("/view.board")) {
				int seq = Integer.parseInt(request.getParameter("seq"));
				BoardDTO dto = BoardDAO.getInstance().readOne(seq);
				ArrayList<ReplyDTO> replyList = ReplyDAO.getInstance().selectPSeq(seq);
				int result = BoardDAO.getInstance().addViewCount(seq);
				request.setAttribute("dto", dto);
				request.setAttribute("replyList", replyList);
				request.getRequestDispatcher("/board/viewContent.jsp").forward(request, response);
			}else if(cmd.equals("/update.board")) {
				int seq = Integer.parseInt(request.getParameter("seq"));
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
				int result = BoardDAO.getInstance().update(seq, title, contents);
				BoardDTO dto = BoardDAO.getInstance().readOne(seq);
				ArrayList<ReplyDTO> replyList = ReplyDAO.getInstance().selectPSeq(seq);
				request.setAttribute("dto", dto);
				request.setAttribute("replyList", replyList);
				request.getRequestDispatcher("/board/viewContent.jsp").forward(request, response);
			}else if(cmd.equals("/delete.board")) {
				int seq = Integer.parseInt(request.getParameter("seq"));
				int result = BoardDAO.getInstance().delete(seq);
				response.sendRedirect("/list.board?cpage=1");
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
