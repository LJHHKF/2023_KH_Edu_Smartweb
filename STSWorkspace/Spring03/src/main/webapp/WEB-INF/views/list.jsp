<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message List</title>
</head>
<body>
	<table border="1" align="center">
		<tr>
			<th colspan="3">Message List</th>
		</tr>
		<tr>
			<th>SEQ</th>
			<th>Writer</th>
			<th>Message</th>
		</tr>
		<c:forEach var="i" items="${list}">
			<tr>
				<td>${i.seq }</td>
				<td>${i.writer }</td>
				<td>${i.message }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="3" align="center">
				<form action="/deleteProc" method="get">
					<input type="text" name="seq" placeholder="지울 메시지의 SEQ">
					<button type="submit">삭제</button>
				</form>
			</td>
		</tr>
		<form action="/modifyProc" method="get">
			<tr>
				<td colspan="3" align="center">
					<input type="text" placeholder="수정할 메시지 SEQ" name="seq">
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="text" placeholder="수정할 메시지 글쓴이" name="writer">
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="text" placeholder="수정할 메시지" name="message">
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="submit" value="수정">
				</td>
			</tr>
		</form>
	</table>
</body>
</html>