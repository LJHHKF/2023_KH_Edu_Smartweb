package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessagesDAO;
import dto.MessagesDTO;

@WebServlet("/SelectMessage")
public class SelectMessage extends HttpServlet {
	
//	@Override
//	public void init() {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		}catch(Exception e) {
//			e.printStackTrace();
//			System.out.println("ojdbc를 못 찾았습니다.");
//		}
//	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf8");
		PrintWriter pw = response.getWriter();
		pw.append("<html>");
		pw.append("<head>");
		pw.append("</head>");
		pw.append("<body>");
		pw.append("<table border='1' align='center'>");
		pw.append("<tr>");
		pw.append("<th>id</th>");
		pw.append("<th>writer</th>");
		pw.append("<th>message</th>");
		pw.append("</tr>");
		try {
			MessagesDAO dao = new MessagesDAO();
			ArrayList<MessagesDTO> list = dao.select();
			for(MessagesDTO curDto : list) {
				pw.append("<tr>");
				pw.append("<td>"+ curDto.getId() +"</td>");
				pw.append("<td>"+ curDto.getWriter() +"</td>");
				pw.append("<td>"+ curDto.getMessage() +"</td>");
				pw.append("</tr>");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB 조회 사용 중 오류가 났습니다.");
			response.sendRedirect("error.html");
		}
		pw.append("<tr>");
		pw.append("<td>");
		//자습으로 익힌 방법을 통해, Submit 타이밍을 Script서 조절.
		pw.append("<form id='modifyForm'>");
		pw.append("<input type='text' placeholder='input id to modify' name='modifyId' id='input_id_modify'><br>");
		pw.append("<input type='text' placeholder='input writer to modify' name='modifyWriter'><br>");
		pw.append("<input type='text' placeholder='input message to modify' name='modifyMessage'>");
		pw.append("</td>");
		pw.append("<td>");
		pw.append("<button type='button' id='btn_modify'>Modify</button>");
		pw.append("</td>");
		pw.append("</form>");
		pw.append("</tr>");
		
		pw.append("<tr>");
		pw.append("<td colspan='3'>");
		pw.append("<form id='deleteForm'>");
		pw.append("<input type='text' placeholder='Input ID to Delete' name='deleteId' id='input_id_delete'>");
		pw.append("<button type='button' id='btn_delete'>Delete</button>");
		pw.append("</form>");
		pw.append("</td>");
		pw.append("</tr>");
		
		pw.append("<tr>");
		pw.append("<td colspan='3' align='center'>");
		pw.append("<button type='button' id='btn_toIndex'>back</button>");
		pw.append("</td>");
		pw.append("</tr>");
		pw.append("</table>");
		
		pw.append("<script>");
		
		pw.append("function idCheck(str){");
		pw.append("if(!str){alert('ID 값은 빈값일 수 없습니다.');return false;}");
		pw.append("else if(str.split(' ').join('').length !== str.length){alert('ID값엔 공백이 포함될 수 없습니다.');return false;}");
		pw.append("else if(isNaN(str)){alert('ID 값은 숫자 값이어야 합니다.');return false;}");
		pw.append("else{return true;}");
		pw.append("}");
		
		pw.append("document.getElementById('btn_toIndex').onclick = function() {");
		pw.append("location.href = 'index.html';");
		pw.append("};");
		
		pw.append("document.getElementById('btn_modify').onclick = function() {");
		pw.append("let input_id = document.getElementById('input_id_modify');");
		pw.append("if(!idCheck(input_id.value)){");
		pw.append("input_id.value='';");
		pw.append("document.getElementsByName('modifyWriter')[0].value ='';");
		pw.append("document.getElementsByName('modifyMessage')[0].value ='';");
		pw.append("}else{");
		pw.append("let modifyForm = document.getElementById('modifyForm');");
		pw.append("modifyForm.method = 'get';");
		pw.append("modifyForm.action = 'ModifyMessage';");
		pw.append("modifyForm.submit();");
		pw.append("}");
		pw.append("};");
		
		pw.append("document.getElementById('btn_delete').onclick = function(){");
		pw.append("let input = document.getElementById('input_id_delete');");
		pw.append("if(idCheck(input.value)){");
		//이하 방식은 따로, '<자바 웹을 다루는 기술>'이란 서적의 6장 부근 참고하여 작성함.
		pw.append("let deleteForm = document.getElementById('deleteForm');");
		pw.append("deleteForm.method = 'get';");
		pw.append("deleteForm.action = 'DeleteMessage';");
		pw.append("deleteForm.submit();");
		pw.append("}else{input.value='';}");
		
		pw.append("};");
		pw.append("</script>");
		pw.append("</body>");
		pw.append("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
