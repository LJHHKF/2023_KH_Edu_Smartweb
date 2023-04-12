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
            <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
        </head>

        <body>
            <div class="container border bodrer-primary rounded">
                <div class="row">
                    <div class="col-12 border border-primary text-center">
                        게시글
                    </div>
                </div>
                <form action="/update.board" method="get">
                    <input type="text" name="seq" value="${dto.seq}" style="display: none;" readonly>
                    <div class="row">
                        <div class="col-12 border border-primary">
                            <input type="text" name="title" class="form-control" value="${dto.title}" readonly>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 border border-primary">
                            <textarea class="summernote" name="contents" readonly>${dto.contents}</textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 border border-primary text-end" id="control">
                            <c:choose>
                                <c:when test="${dto.writer eq loginID}">
                                    <button type="button" id="btn_update" class="btn btn-primary">수정하기</button>
                                    <button type="button" id="btn_delete" class="btn btn-primary">삭제하기</button>

                                    <script>
                                        $("#btn_update").click(function () {
                                            $("input, textarea").removeAttr("readonly");
                                            $("#btn_update, #btn_delete").css("display", "none");
                                            $(".summernote").summernote("enable");

                                            if (!($("#btn_confirm").length > 0)) {
                                                let btn_confirm = $("<button>");
                                                let btn_cancel = $("<button>");

                                                btn_confirm.text("수정완료");
                                                btn_confirm.addClass("btn");
                                                btn_confirm.addClass("btn-primary");
                                                btn_confirm.attr("id", "btn_confirm");

                                                btn_cancel.attr("type", "button");
                                                btn_cancel.attr("id", "btn_cancel");
                                                btn_cancel.text("취소");
                                                btn_cancel.addClass("btn");
                                                btn_cancel.addClass("btn-primary");
                                                btn_cancel.click(function () {
                                                    $("input, textarea").attr("readonly", "true");
                                                    $("#btn_update, #btn_delete").css("display", "inline-block");
                                                    $("#btn_confirm, #btn_cancel").css("display", "none");
                                                    $(".summernote").summernote("disable");
                                                });

                                                $("#control").prepend(btn_confirm);
                                                $("#control").prepend(btn_cancel);
                                            } else {
                                                $("#btn_confirm, #btn_cancel").css("display", "inline-block");
                                            }
                                        });
                                        $("#btn_delete").click(function () {
                                            let seq = "<c:out value='${dto.seq}'></c:out>";
                                            location.href = "/delete.board?seq=" + seq;
                                        });
                                    </script>
                                </c:when>
                                <c:otherwise>

                                </c:otherwise>
                            </c:choose>
                            <a href="/list.board"><button type="button" class="btn btn-primary">목록으로</button></a>
                        </div>
                    </div>
                </form>
            </div>

            <script>
                $(document).ready(function () {
                    $(".summernote").summernote({
                        height: 300,
                        minHeight: null,
                        maxHeight: null,
                        focus: true,
                        lang: "ko-KR",
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
                    $(".summernote").summernote('disable');
                });
            </script>
        </body>

        </html>