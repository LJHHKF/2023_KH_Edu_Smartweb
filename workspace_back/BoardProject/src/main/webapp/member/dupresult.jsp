<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<style>
    table{
        width:100%;
        height:95vh;
        text-align: center;
    }
</style>
</head>
<body>
    <c:choose>
        <c:when test="${isDup}">
            <table class="table table-primary table-bordered table-striped align-middle">
                <tr>
                    <th>중복 검사 결과</th>
                </tr>
                <tbody class="table-group-divider">
	                <tr>
	                    <td>이미 사용중인 ID입니다.</td>
	                </tr>
	                <tr>
	                    <td><button class="btn_close btn btn-primary">닫기</button></td>
	                </tr>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <table class="table table-primary table-bordered table-striped align-middle">
                <tr>
                    <th colspan="2">중복 검사 결과</th>
                </tr>
                <tbody class="table-group-divider">
	                <tr>
	                    <td colspan="2">
	                        사용 가능한 ID입니다.<br>
	                        사용하시겠습니까?
	                    </td>
	                </tr>
	                <tr>
	                    <td><button id="btn_use" class="btn btn-primary">사용</button></td>
	                    <td><button class="btn_close btn btn-primary">닫기</button></td>
	                </tr>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
    <!-- <button type="button" id="btn_close">닫기</button> -->

    <script>
        $("#btn_use").click(function(){
        	opener.idValidFlag = true;
            opener.document.getElementById("input_pw").focus();
            window.close();
        });
        $(".btn_close").click(function(){
            opener.document.getElementById("input_id").value ="";
            window.close();
        });
    </script>
</body>
</html>