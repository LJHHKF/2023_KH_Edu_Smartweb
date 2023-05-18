<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Input Form</title>
</head>
<body>
	<form action="/inputProc" method="post">
		<table border="1" align="center">
			<tr>
				<th>Input</th>
			</tr>
			<tr>
				<td><input type="text" name="writer" placeholder="글쓴이"></td>
			</tr>
			<tr>
				<td><input type="text" name="message" placeholder="메시지"></td>
			</tr>
			<tr>
				<td align="center">
					<button type="submit">제출</button>
					<a href="/"><button type="button">Back</button></a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>