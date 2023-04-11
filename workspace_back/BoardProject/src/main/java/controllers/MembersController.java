package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commons.EncryptionUtils;
import dao.MembersDAO;
import dto.MembersDTO;

@WebServlet("*.members")
public class MembersController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		String cmd = request.getRequestURI();

		try {
			if(cmd.equals("/create.members")) {
				String id = request.getParameter("id");
				String pw = EncryptionUtils.sha512(request.getParameter("pw"));
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String zipcode = request.getParameter("zipcode");
				String addr1 = request.getParameter("addr1");
				String addr2 = request.getParameter("addr2");

				int result = MembersDAO.getInstance().insert(new MembersDTO(id, pw, name, phone, email, zipcode, addr1, addr2, null));
				response.sendRedirect("/index.jsp?state=a_j");

			}else if(cmd.equals("/idCheck.members")) {
				String id = request.getParameter("id");
				boolean result = MembersDAO.getInstance().isIdExist(id);
				request.setAttribute("isDup", result);
				request.getRequestDispatcher("/member/dupresult.jsp").forward(request, response);

			}else if(cmd.equals("/login.members")) {
				request.setCharacterEncoding("utf8");
				String id = request.getParameter("id");
				String pw = EncryptionUtils.sha512(request.getParameter("pw"));

				boolean result = MembersDAO.getInstance().login(id, pw);
				if(result) {
					request.getSession().setAttribute("loginID", id);
				}
				response.sendRedirect("/index.jsp");

			}else if(cmd.equals("/logout.members")) {
				request.getSession().invalidate();
				response.sendRedirect("/index.jsp");

			}else if(cmd.equals("/myPage.members")) {
				MembersDTO myDto = MembersDAO.getInstance().getMyDto((String)request.getSession().getAttribute("loginID"));
				request.setAttribute("myDto", myDto);
				request.getRequestDispatcher("/member/mypage.jsp").forward(request, response);

			}else if(cmd.equals("/update.members")) {
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				if(!MembersDAO.getInstance().isPwNonChange(id, pw)) {
					pw = EncryptionUtils.sha512(pw);				
				}
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String zipcode = request.getParameter("zipcode");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

				int result = MembersDAO.getInstance().update(new MembersDTO(id, pw, name, phone, email, zipcode, address1, address2, null));
				response.sendRedirect("/myPage.members");

			}else if(cmd.equals("/out.members")) {
				int result = MembersDAO.getInstance().delete((String)request.getSession().getAttribute("loginID"));
				if(result > 0) {
					request.getSession().invalidate();
				}
				response.sendRedirect("/index.jsp");
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
