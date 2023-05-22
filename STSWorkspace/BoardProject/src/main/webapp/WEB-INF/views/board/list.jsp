<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
                rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css"
				rel="stylesheet">
<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
</head>
<body>
			<div class="container border border-primary rounded">
                <div class="row">
                    <div class="col-12 text-center border border-primary">
                        <h1>자유게시판</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 border border-primary">
                        <table id="myTable" class="table table-primary table-striped">
                        <thead>
                            <tr>
                                <th width="5%">번호</th>
                                <th width="60%">제목</th>
                                <th width="15%">작성자</th>
                                <th width="15%">날짜</th>
                                <th width="5%">조회</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="i" items="${list}">
                                <tr>
                                    <td>${i.seq}</td>
                                    <td><a href="/board/viewContent?seq=${i.seq}">${i.title}</a></td>
                                    <td>${i.writer}</td>
                                    <td>${i.getFormedJoinDate_list()}</td>
                                    <td>${i.view_count}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                            <tr>
                                <td colspan="5" align="right">
                                    <a href="/"><button class="btn btn-primary">메인화면으로</button></a>
                                    <a href="/board/toWriteForm"><button class="btn btn-primary">작성하기</button></a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <script>
	            let table = new DataTable('#myTable', {
	                responsive: true
	            });
            </script>
</body>
</html>