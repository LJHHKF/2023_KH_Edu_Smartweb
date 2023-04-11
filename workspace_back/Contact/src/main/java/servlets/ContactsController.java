package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContactsDAO;
import dto.ContactsDTO;

@WebServlet("*.contacts")
public class ContactsController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		String cmd = request.getRequestURI();

		try {
			if(cmd.equals("/create.contacts")) {
				String name = request.getParameter("name");
				String contact = request.getParameter("contact");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
				Timestamp birthday = new Timestamp(dateFormat.parse(request.getParameter("birthday")).getTime());

				int result = ContactsDAO.getInstance().insert(new ContactsDTO(0, name, contact, birthday));
				response.sendRedirect("index.html");
				
			}else if(cmd.equals("/read.contacts")) {
				ArrayList<ContactsDTO> result = ContactsDAO.getInstance().selectAll();
				request.setAttribute("list", result);
				request.getRequestDispatcher("list.jsp").forward(request, response);
				
			}else if(cmd.equals("/update.contacts")) {
				//공백 및 NaN형 포함한 유효성 검사를 JS에서 처리하고 옴.
				int id = Integer.parseInt(request.getParameter("updateID"));
				String name = request.getParameter("updateName");
				String contact = request.getParameter("updateContact");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
				Timestamp birthday = new Timestamp(dateFormat.parse(request.getParameter("updateBirthday")).getTime());
				int result = ContactsDAO.getInstance().update(new ContactsDTO(id, name, contact, birthday));
				response.sendRedirect("/read.contacts");
				
			}else if(cmd.equals("/delete.contacts")) {
				//공백 및 NaN형 포함한 유효성 검사를 JS에서 처리하고 옴.
				int id = Integer.parseInt(request.getParameter("deleteID"));
				int result = ContactsDAO.getInstance().delete(id);
				response.sendRedirect("/read.contacts");
				
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
