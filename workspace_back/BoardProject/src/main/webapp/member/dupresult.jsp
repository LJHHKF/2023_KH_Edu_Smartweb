<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
    <c:choose>
        <c:when test="${isDup}">
            id가 중복되었습니다.<br>
        </c:when>
        <c:otherwise>
            id가 중복되지 않았습니다.<br>
        </c:otherwise>
    </c:choose>
    <button type="button" id="btn_close">닫기</button>

    <script>
        $("#btn_close").click(function(){
            window.close();
        });
    </script>
</body>
</html>