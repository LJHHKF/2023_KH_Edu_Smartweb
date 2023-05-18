<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie List</title>
</head>
<body>
	<table border="1" align="center">
		<tr>
			<th colspan="3">Movie List</th>
		</tr>
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>genre</th>
		</tr>
		<c:forEach var="i" items="${list}">
			<tr>
				<td>${i.id }</td>
				<td>${i.title }</td>
				<td>${i.genre }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="3" align="center">
				<form action="/deleteProc">
					<input type="text" placeholder="삭제할 영화 ID" name="id">
					<button>삭제하기</button>
				</form>
			</td>
		</tr>
		<form action="/modifyProc">
			<tr>
				<td colspan="3" align="center">
					<input type="text" placeholder="수정할 영화 ID" name="id">
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="text" placeholder="수정할 영화 제목" name="title">
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="text" placeholder="수정할 영화 장르" name="genre">
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