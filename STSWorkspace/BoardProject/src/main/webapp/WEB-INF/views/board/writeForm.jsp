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
<script src="https://cdn.ckeditor.com/ckeditor5/37.0.1/classic/ckeditor.js"></script>
</head>
<body>
			<div class="container border border-primary rounded">
                <div class="row">
                    <div class="col-12 border border-primary text-center">
                        게시글 작성
                    </div>
                </div>
                <form action="/board/write" method="post" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-12 border border-primary">
                            <input type="text" name="title" class="form-control" placeholder="글 제목을 입력하세요.">
                        </div>
                        <fieldset>
                        	<legend>파일 첨부</legend>
                        	<input type="file" name="files" class="form-control" multiple>
                        </fieldset>
                    </div>
                    <div class="row">
                        <div class="col-12 border border-primary">
                            <textarea id="editor" name="contents"></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 border border-primary text-end">
                            <a href="/board/list"><button type="button" class="btn btn-primary">목록으로</button></a>
                            <button class="btn btn-primary">작성완료</button>
                        </div>
                    </div>
                </form>
            </div>
            
            <script>
                ClassicEditor
                    .create(document.querySelector('#editor'))
                    .catch(error => {console.error(error)});
            </script>
</body>
</html>