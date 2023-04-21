<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	<table border=1 align="center">
        <tr>
            <th>파일 이름</th>
            <th>파일</th>
        </tr>
        <c:forEach var="i" begin="0" end="${fn:length(dtoList)-1}" step="1">
            <tr>
                <td>${dtoList.get(i).seq}</td>
                <!-- <td><img src="/upload/${dtoList.get(i).sysName}"></td> -->
                <td><a href="/download.file?sysName=${dtoList.get(i).sysName}&oriName=${dtoList.get(i).oriName}">${dtoList.get(i).oriName}</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>