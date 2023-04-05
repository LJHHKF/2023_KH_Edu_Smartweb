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
//			request.getRequestDispatcher("insertView.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB 삽입 사용 중 오류가 났습니다.");
			response.sendRedirect("error.html");
		}
		
		PrintWriter pw = response.getWriter();
		pw.append("<html>");
		pw.append("<head>");
		pw.append("</head>");
		
		pw.append("<body>");
		pw.append("<button id = 'toIndex'>");
		pw.append("to Index");
		pw.append("</button>");
		pw.append("<script>");
		pw.append("alert('Input Success');");
		pw.append("document.getElementById('toIndex').onclick = function(){");
		pw.append("location.href = 'index.html';");
		pw.append("}");
		pw.append("</script>");
		pw.append("</body>");
		pw.append("</html>");
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
