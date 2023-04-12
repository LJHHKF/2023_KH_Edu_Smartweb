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
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
</head>
<body>
    <div class="container border border-primary rounded">
        <div class="row">
            <div class="col-12 border border-primary text-center">
                게시글 작성
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
                    <textarea class="summernote" name="contents" cols="30" rows="5"></textarea>
                </div>
            </div>
            <div class="row">
                <div class="col-12 border border-primary text-end">
                    <a href="/list.board"><button type="button" class="btn btn-primary">목록으로</button></a>
                    <button class="btn btn-primary">작성완료</button>
                </div>
            </div>
        </form>
    </div>
    <script>
        $(document).ready(function(){
            $(".summernote").summernote({
                height:300,
                minHeight:null,
                maxHeight:null,
                focus:true,
                lang:"ko-KR",
                toolbar: [
                    ['style', ['style']],
                    ['font', ['bold', 'underline', 'clear']],
                    ['color', ['color']],
                    ['para', ['ul', 'ol', 'paragraph']],
                    ['table', ['table']],
                    ['insert', ['link', 'picture', 'video']],
                    ['view', ['fullscreen', 'codeview', 'help']]
                  ]
            });
        });
        </script>
</body>
</html>