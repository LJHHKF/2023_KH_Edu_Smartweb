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
                                <c:choose>
                                    <c:when test="${isSearch}">
                                        <td colspan="5" align="center">
                                            <c:if test="${navi.needPrev}">
                                                <a href="/list.board?cpage=${navi.naviList.get(0) - 1}"><</a>
                                            </c:if>
                                            <form id="searchForm" method="get">
                                                <input type="text" style="display: none;" name="option" value="${option}">
                                                <input type="text" style="display: none;" name="keyword" value="${keyword}">
                                                <input type="text" id="input_cpage" style="display: none;" name="cpage" value="1">
                                                <c:forEach var="i" items="${navi.naviList}">
                                                    <a href="#none" id="cpage${i.intValue()}">${i.intValue()}</a> 
                                                    <script>
                                                        let out = "#cpage" + "<c:out value='${i.intValue()}'></c:out>";
                                                        $(out).click(function(){
                                                            let out2 = "<c:out value='${i.intValue()}'></c:out>";
                                                            $("#input_cpage").value(out2);
                                                            $("#searchForm").action("/search.board");
                                                            $("#searchForm").submit();
                                                        });
                                                    </script>
                                                </c:forEach>
                                            </form>
                                            <c:if test="${navi.needNext}">
                                                <a href="/list.board?cpage=${navi.naviList.get(navi.naviList.length) + 1}">></a>
                                            </c:if>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td colspan="5" align="center">
                                            <c:if test="${navi.needPrev}">
                                                <a href="/list.board?cpage=${navi.naviList.get(0) - 1}"><</a>
                                            </c:if>
                                            <c:forEach var="i" items="${navi.naviList}">
                                                <a href="/list.board?cpage=${i.intValue()}">${i.intValue()}</a>
                                            </c:forEach>
                                            <c:if test="${navi.needNext}">
                                                <a href="/list.board?cpage=${navi.naviList.get(navi.naviList.length) + 1}">></a>
                                            </c:if>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td colspan="5" align="center">
                                    <form action="/search.board" method="get">
                                        <input type="text" name="cpage" value="1" style="display: none;">
                                        <select name="option" class="form-select">
                                            <option value="title">제목</option>
                                            <option value="writer">작성자</option>
                                        </select>
                                        <input type="text" name="keyword" class="form-control">
                                        <button class="btn btn-primary">검색</button>
                                    </form>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5" align="right">
                                    <c:if test="${isSearch}">
                                        <a href="/list.board?cpage=1"><button class="btn btn-primary">검색 필터 해제</button></a>
                                    </c:if>
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