<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
    rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container border border-primary rounded">
        <div class="row">
            <div class="col-12 border border-primary">
            </div>
        </div>
        <form action="/write.board" method="get" id="writeForm">
            <div class="row">
                <div class="col-12 border border-primary">
                    <input type="text" name="title" class="form-control" placeholder="글 제목을 입력하세요.">
                </div>
            </div>
            <div class="row">
                <div class="col-12 border border-primary">
                    <textarea name="contents" cols="30" rows="5" class="form-control"></textarea>
                </div>
            </div>
            <div class="row">
                <div class="col-12 text-end">
                    <button>작성완료</button>
                    <a href="/board/list.jsp"><button type="button">목록으로</button></a>
                </div>
            </div>
        </form>
    </div>
</body>
</html>