<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Practice</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
	<fieldset>
		<legend>File Upload</legend>
		<form action="/file/upload" method="post" enctype="multipart/form-data">
			<input type="text" placeholder="메시지 입력" name="message"><br>
			<input type="file" value="첨부파일" name="files" multiple><br>
			<button>제출</button>
		</form>
	</fieldset>
	
	<fieldset>
		<legend>File List</legend>
		<button type="button" id="getFiles">파일 목록 불러오기</button>
		<div id="files"></div>
	</fieldset>
	
	<script>
		$("#getFiles").on("click", function(){
			$.ajax({
				url:"/file/list",
			}).done(function(resp){
				$("#files").empty();
				
				for(let i = 0; i < resp.length; i++){
					let anker = $("<a>");
					anker.attr("href","/file/download?sysName="+resp[i].sysName+"&oriName="+resp[i].oriName);
					anker.text(resp[i].oriName);
					
					let line = $("<div>");
					line.append(anker);
					$("#files").append(line);
				}
			})
		});
	</script>
</body>
</html>