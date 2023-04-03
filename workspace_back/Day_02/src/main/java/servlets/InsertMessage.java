package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertMessage")
public class InsertMessage extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String writer = request.getParameter("writer");
		String msg = request.getParameter("msg");
		String result_writer = "writer : " + writer;
		String result_msg = "msg : " + msg;
		System.out.println(result_writer);
		System.out.println(result_msg);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ojdbc를 못 찾았습니다.");
		}
		
		try {
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbId = "kh";
			String dbPw = "kh";
			String sql = "Insert into messages values (msg_seq.nextval, ?, ?)";
			try(	Connection con = DriverManager.getConnection(dbUrl, dbId, dbPw);
					PreparedStatement pstat = con.prepareStatement(sql);){
				pstat.setString(1, writer);
				pstat.setString(2, msg);
				int result = pstat.executeUpdate();
				con.commit();
				if(result > 0) {
					System.out.println("메시지 DB 등록에 성공했습니다.");
				}
//				response.sendRedirect("success.html");
				
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
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB 사용 중 오류가 났습니다.");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
