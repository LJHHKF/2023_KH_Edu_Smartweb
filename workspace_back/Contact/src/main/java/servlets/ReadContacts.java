package servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
		response.setContentType("text/html; charset=utf8");
		PrintWriter pw = response.getWriter();
		
		pw.append("<html>");
		pw.append("<head>");
		pw.append("</head>");
		pw.append("<body>");
		
		pw.append("<table border='1' align='center'>");
		
		pw.append("<tr>");
		pw.append("<th>ID</th>");
		pw.append("<th>Name</th>");
		pw.append("<th>Contact</th>");
		pw.append("<th>birthday</th>");
		pw.append("</tr>");
		
		try {
			ContactsDAO dao = new ContactsDAO();
			for(ContactsDTO curDto : dao.selectAll()) {
				pw.append("<tr>");
				pw.append("<td>"+ curDto.getId() +"</td>");
				pw.append("<td>"+ curDto.getName() + "</td>");
				pw.append("<td>"+ curDto.getContact()+"</td>");
				pw.append("<td>"+ curDto.getFormedBirthday()+"</td>");
				pw.append("</tr>");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB 읽기 사용 중 오류");
			response.sendRedirect("error.html");
		}
		
		pw.append("<form id='updateForm'>");
		
		pw.append("<tr>");
		pw.append("<td colspan='2'>");
		pw.append("<input type='text' name='updateID' id='updateID' placeholder='수정할 요소의 id를 입력해주세요.'>");
		pw.append("</td>");
		pw.append("<td colspan='2'>");
		pw.append("<button type='button' id='btn_update'>수정</button>");
		pw.append("</td>");
		pw.append("</tr>");
		
		pw.append("<tr>");
		pw.append("<td>");
		pw.append("<input type='text' name='updateName' placeholder='수정할 이름 값을 입력해주세요.'>");
		pw.append("</td>");
		pw.append("<td>");
		pw.append("<input type='text' name='updateContact' placeholder='수정할 연락처 값을 입력해주세요.'>");
		pw.append("</td>");
		pw.append("<td>");
		pw.append("<input type='date' name='updateBirthday' required>");
		pw.append("</td>");
		//1열은 넣을 값 없어서 생략
		pw.append("</tr>");
		
		pw.append("</form>");
		
		pw.append("<tr>");
		pw.append("<td colspan='2'>");
		pw.append("<form id='deleteForm'>");
		pw.append("<input type='text' name='deleteID' id='deleteID' placeholder='삭제할 id를 입력해주세요.'>");
		pw.append("</form>");
		pw.append("</td>");
		pw.append("<td colspan='2'>");
		pw.append("<button type='button' id='btn_delete'>삭제</button>");
		pw.append("</td>");
		pw.append("</tr>");
		
		pw.append("<tr>");
		pw.append("<td colspan='4' align='center'>");
		pw.append("<a href='index.html'>back</a>");
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
		
		pw.append("function dateCheck(date){");
		pw.append("if(!date){alert('날짜값은 빈 값일 수 없습니다.');return false;}");
		pw.append("else{return true;}");
		pw.append("}");		
		
		pw.append("function curDate(){");
		pw.append("return new Date().toISOString().substring(0, 10);");
		pw.append("}");
		pw.append("document.getElementsByName('updateBirthday')[0].value = curDate();");
		
		pw.append("document.getElementById('btn_delete').onclick = function(){");
		pw.append("let deleteID = document.getElementById('deleteID');");
		pw.append("if(idCheck(deleteID.value)){");
		pw.append("let deleteForm = document.getElementById('deleteForm');");
		pw.append("deleteForm.method = 'get';");
		pw.append("deleteForm.action = 'DeleteContacts';");
		pw.append("deleteForm.submit();");
		pw.append("}else{deleteID.value='';}");
		pw.append("};");
		
		pw.append("document.getElementById('btn_update').onclick = function(){");
		pw.append("let updateID = document.getElementById('updateID');");
		pw.append("if(idCheck(updateID.value) && dateCheck(document.getElementsByName('updateBirthday')[0].value)){");
		pw.append("let updateForm = document.getElementById('updateForm');");
		pw.append("updateForm.method = 'get';");
		pw.append("updateForm.action = 'UpdateContacts';");
		pw.append("updateForm.submit();");
		pw.append("}else{updateID.value='';document.getElementsByName('updateBirthday')[0].value=curDate();}");
		pw.append("};");
		
		pw.append("</script>");
		pw.append("</body>");
		pw.append("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
