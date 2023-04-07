package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContactsDAO;
import dto.ContactsDTO;


@WebServlet("/ReadContacts")
public class ReadContacts extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ContactsDTO> result = null;
		try {
			result = ContactsDAO.getInstance().selectAll();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB 읽기 사용 중 오류");
			response.sendRedirect("error.html");
		}
		request.setAttribute("list", result);
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
