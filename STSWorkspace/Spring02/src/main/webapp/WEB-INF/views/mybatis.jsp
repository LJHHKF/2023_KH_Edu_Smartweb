<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyBatis Practice</title>
</head>
<body>
	MyBatis 연습
	<fieldset>
		<legend>SelectByCon</legend>
		<form action="/selectByCon" method="post">
			<select name="column">
				<option value="title">제목</option>
				<option value="genre">장르</option>
			</select>
			<input type="text" name="value" placeholder="검색 키워드 입력">
			<button>검색</button>
		</form>
	</fieldset>
	<fieldset>
		<legend>SelectByMultiCon</legend>
		<form action="/selectByMultiCon" method="post">
			<input type="text" name="title" placeholder="영화 제목">
			<input type="text" name="genre" placeholder="영화 장르">
			<button>검색</button>
		</form>
	</fieldset>
</body>
</html>