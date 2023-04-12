<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                    <div class="col-12 text-center border border-primary">
                        <h1>자유게시판</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 border border-primary">
                        <table class="table table-primary table-striped">
                            <tr>
                                <th width="5%">번호</th>
                                <th width="60%">제목</th>
                                <th width="15%">작성자</th>
                                <th width="15%">날짜</th>
                                <th width="5%">조회</th>
                            </tr>
                            <c:forEach var="i" items="${list}">
                                <tr>
                                    <td>${i.seq}</td>
                                    <td><a href="/view.board?seq=${i.seq}">${i.title}</a></td>
                                    <td>${i.writer}</td>
                                    <td>${i.getFormedJoinDate_list()}</td>
                                    <td>${i.view_count}</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="5" align="center">1 2 3 4 5 6 7 8 9 10</td>
                            </tr>
                            <tr>
                                <td colspan="5" align="right">
                                    <a href="/index.jsp"><button class="btn btn-primary">메인화면으로</button></a>
                                    <a href="/board/writeform.jsp"><button class="btn btn-primary">작성하기</button></a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </body>

        </html>