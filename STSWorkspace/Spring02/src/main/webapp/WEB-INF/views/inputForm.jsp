<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<td><input type="text" name="title" placeholder="영화 제목"></td>
			</tr>
			<tr>
				<td><input type="text" name="genre" placeholder="영화 장르"></td>
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