<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>회원가입</h1>
	<form action="<%=request.getContextPath()%>/join" method="post">
		아이디 : <input type="text" name="memberId" required><br>
		비밀번호 : <input type="password" name="memberPw" required><br>
		이름 : <input type="text" name="memberName" required><br>
		<input type="submit" value="전송">
	</form>
</body>
</html>