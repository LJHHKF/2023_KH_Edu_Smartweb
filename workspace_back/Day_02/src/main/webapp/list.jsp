<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>MessageList</title>
		</head>

		<body>
			<table border="1" align="center">
				<tr>
					<th colspan="3">Message List</th>
				</tr>
				<tr>
					<th>ID</th>
					<th>WRITER</th>
					<th>MESSAGE</th>
				</tr>
				<c:forEach var="i" items="${list}">
					<tr>
						<td>${i.id}</td>
						<td>${i.writer}</td>
						<td>${i.message}</td>
					</tr>
				</c:forEach>
				<form action="ModifyMessage" method="get" id="modifyForm">
					<tr>
						<td colspan="2">
							<input type="text" name="modifyId" placeholder="input id to modify">
						</td>
						<td rowspan="3">
							<button type="button" id="btn_modify">수정</button>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="text" name="modifyWriter" placeholder="input wirter to modify">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="text" name="modifyMessage" placeholder="input message to modify">
						</td>
					</tr>
				</form>
				<form action="DeleteMessage" method="get" id="deleteForm">
					<tr>
						<td colspan="2">
							<input type="text" name="deleteId" placeholder="Input id to Delete">
						</td>
						<td>
							<button type="button" id="btn_delete">삭제</button>
						</td>
					</tr>
				</form>
				<tr>
					<td colspan="3" align="center"><a href="index.html">back</a></td>
				</tr>
			</table>

			<script>
				function idCheck(str) {
					if (!str) {
						alert("ID 값은 빈 값일 수 없습니다.");
						return false;
					} else if (str.split(" ").join("").length !== str.length) {
						alert("ID 값은 공백을 포함할 수 없습니다.");
						return false;
					} else if (isNaN(str)) {
						alert("ID 값은 숫자 값이어야 합니다.");
						return false;
					} else {
						return true;
					}
				}

				document.getElementById("btn_modify").addEventListener("click", function(){
					let modifyID = document.getElementsByName("modifyId")[0];
					if(idCheck(modifyID.value)){
						document.getElementById("modifyForm").submit();
					}else{
						modifyID.value = "";
					}
				});

				document.getElementById("btn_delete").addEventListener("click", function(){
					let deleteID = document.getElementsByName("deleteId")[0];
					if(idCheck(deleteID.value)){
						document.getElementById("deleteForm").submit();
					}else{
						deleteID.value = "";
					}
				});

				document.getElementsByName("modifyId")[0].addEventListener("keyup", function(e){
					if(e.key == "Enter"){
						document.getElementsByName("modifyWriter")[0].focus();
					}
				});
				document.getElementsByName("modifyWriter")[0].addEventListener("keyup", function(e){
					if(e.key == "Enter"){
						document.getElementsByName("modifyMessage")[0].focus();
					}
				});
				document.getElementsByName("modifyMessage")[0].addEventListener("keyup", function(e){
					if(e.key == "Enter"){
						document.getElementById("btn_modify").click();
					}
				});
				document.getElementsByName("deleteId")[0].addEventListener("keyup",  function(e){
					if(e.key == "Enter"){
						document.getElementById("btn_delete").click;
					}
				});
				
			</script>
		</body>

		</html>