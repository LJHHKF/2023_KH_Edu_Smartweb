<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
                    rel="stylesheet">
<script
                    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/37.0.1/classic/ckeditor.js"></script>
</head>
<body>
<div class="container border bodrer-primary rounded">
                    <div class="row">
                        <div class="col-12 border border-primary text-center">
                            게시글
                        </div>
                    </div>
                    <form action="/board/update" method="get">
                        <input type="text" name="seq" value="${dto.seq}" style="display: none;" readonly>
                        <div class="row">
                            <div class="col-12 border border-primary">
                                <input type="text" name="title" class="form-control" value="${dto.title}" readonly>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12 border border-primary">
                                <!-- <textarea class="summernote" name="contents" readonly>${dto.contents}</textarea> -->
                                <textarea id="editor" name="contents" readonly>${dto.contents}</textarea>

                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12 border border-primary text-end" id="control">
                                <c:if test="${dto.writer eq loginID}">
                                    <button type="button" id="btn_update" class="btn btn-primary">수정하기</button>
                                    <button type="button" id="btn_delete" class="btn btn-primary">삭제하기</button>

                                    <script>
                                        $("#btn_update").click(function () {
                                            $("input, textarea").removeAttr("readonly");
                                            $("#btn_update, #btn_delete").css("display", "none");
                                            myEditor.disableReadOnlyMode("");
                                            // $(".summernote").summernote("enable");

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
                                                    //$(".summernote").summernote("disable");
                                                    myEditor.enableReadOnlyMode("");
                                                });

                                                $("#control").prepend(btn_confirm);
                                                $("#control").prepend(btn_cancel);
                                            } else {
                                                $("#btn_confirm, #btn_cancel").css("display", "inline-block");
                                            }
                                        });
                                        $("#btn_delete").click(function () {
                                            let seq = "<c:out value='${dto.seq}'></c:out>";
                                            location.href = "/board/delete?seq=" + seq;
                                        });
                                    </script>
                                </c:if>
                            </div>
                        </div>
                    </form>
                        <a href="/board/list">
                        	<button type="button" class="btn btn-primary">목록으로</button>
                        </a>
                </div>
                <!--  </div> 
                <hr class="border border-primary border-3 opacity-75">
                <form action="/create.reply" method="get">
                    <input type="text" style="display: none;" name="parent_seq" value="${dto.seq}">
                    <div class="row">
                        <div class="col-12 border border-primary text-center">
                            댓글
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 border border-primary">
                            <input type="text" name="writer" class="form-control" value="${loginID}" readonly>
                        </div>
                    </div>
                    <div class="row align-items-center">
                        <div class="col-10 border border-primary">
                            <textarea id="editor2" name="contents"></textarea>
                        </div>
                        <div class="col-2 text-center">
                            <button type="submit" class="btn btn-primary">작성완료</button>
                        </div>
                    </div>
                </form>
                <c:if test="${fn:length(replyList) > 0}">
                    <c:forEach var="i" items="${replyList}">
                        <div class="row">
                            <div class="col-12 border border-primary">
                                ${i.writer}
                            </div>
                        </div>
                        <c:choose>
                            <c:when test="${i.writer eq loginID}">
                                <form id="deleteForm${i.seq}" action="/delete.reply" method="get">
                                    <input type="text" style="display: none;" name="parent_seq" value="${dto.seq}">
                                    <input type="text" style="display: none;" name="deleteSeq" value="${i.seq}">
                                </form>
                                <form id="updateForm${i.seq}" action="/update.reply" method="get">
                                    <input type="text" style="display: none;" name="parent_seq" value="${dto.seq}">
                                    <input type="text" style="display: none;" name="updateSeq" value="${i.seq}">
                                    <div class="row align-items-center">
                                        <div class="col-10 border border-primary">
                                            <textarea name="contents" id="comment${i.seq}">${i.contents}</textarea>
                                        </div>
                                </form>
                                <div id="replyControl${i.seq}" class="col-2 text-center">
                                    <button type="button" id="btn_update${i.seq}" class="btn btn-primary">수정</button>
                                    <button type="button" id="btn_delete${i.seq}" class="btn btn-primary">삭제</button>
                                </div>
                                <script>
                                    var out = "#comment" + "<c:out value='${i.seq}'></c:out>";
                                    ClassicEditor
                                        .create(document.querySelector(out))
                                        .then(function (editor) {
                                            const toolbarElement = editor.ui.view.toolbar.element;
                                            toolbarElement.style.display = 'none';
                                            editor.enableReadOnlyMode('');
                                            let out2 = "#btn_update" + "<c:out value='${i.seq}'></c:out>";
                                            $(out2).click(function () {
                                                let out3 = "#btn_delete" + "<c:out value='${i.seq}'></c:out>";

                                                let out4 = "#btn_confirm" + "<c:out value='${i.seq}'></c:out>";
                                                let out5 = "#btn_cancel" + "<c:out value='${i.seq}'></c:out>";

                                                if (!($(out4).length > 0)) {

                                                    let out6 = "#replyControl" + "<c:out value='${i.seq}'></c:out>";
                                                    let out7 = "#updateForm" + "<c:out value='${i.seq}'></c:out>";
                                                    let btn_confirm = $("<button>");
                                                    let btn_cancel = $("<button>");

                                                    btn_confirm.text("수정완료");
                                                    btn_confirm.addClass("btn");
                                                    btn_confirm.addClass("btn-primary");
                                                    btn_confirm.attr("id", out4);
                                                    btn_confirm.attr("type", "button");
                                                    btn_confirm.click(function () {
                                                        $(out7).submit();
                                                    });

                                                    btn_cancel.attr("type", "button");
                                                    btn_cancel.attr("id", out5);
                                                    btn_cancel.text("취소");
                                                    btn_cancel.addClass("btn");
                                                    btn_cancel.addClass("btn-primary");
                                                    btn_cancel.click(function () {
                                                        $(out2 + "," + out3).css("display", "inline-block");
                                                        $(out4 + "," + out5).css("display", "none");
                                                        myEditor.enableReadOnlyMode("");
                                                    });

                                                    $(out6).prepend(btn_confirm);
                                                    $(out6).prepend(btn_cancel);
                                                } else {
                                                    $(out4 + "," + out5).css("display", "inline-block");
                                                }
                                                if (editor.isReadOnly) {
                                                    editor.disableReadOnlyMode('');
                                                    $(out2 + "," + out3).css("display", "none");
                                                } else {
                                                    editor.enableReadOnlyMode('');
                                                    $(out2 + "," + out3).css("display", "inline-block");
                                                }
                                            })
                                        })
                                        .catch(error => { console.error(error) });

                                    var out2 = "#btn_delete" + "<c:out value='${i.seq}'></c:out>";
                                    $(out2).click(function () {
                                        var out3 = "#deleteForm" + "<c:out value='${i.seq}'></c:out>";
                                        $(out3).submit();
                                    });
                                </script>
                            </c:when>
                            <c:otherwise>
                                <div class="row align-items-center">
                                    <div class="col-10 border border-primary">
                                        <textarea id="comment${i.seq}">${i.contents}</textarea>
                                    </div>
                                </div>
                                <script>
                                    var out = "#comment" + "<c:out value='${i.seq}'></c:out>";
                                    ClassicEditor
                                        .create(document.querySelector(out))
                                        .then(function (editor) {
                                            const toolbarElement = editor.ui.view.toolbar.element;
                                            toolbarElement.style.display = 'none';
                                            editor.enableReadOnlyMode('');
                                        })
                                        .catch(error => { console.error(error) });
                                </script>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:if>
                </div>
                -->

                <script>
                    var myEditor = null;
                    ClassicEditor
                        .create(document.querySelector('#editor'))
                        .then(function (editor) {
                            const toolbarElement = editor.ui.view.toolbar.element;
                            myEditor = editor;
                            editor.on('change:isReadOnly', (evt, propertyName, isReadOnly) => {
                                if (isReadOnly) {
                                    toolbarElement.style.display = 'none';
                                } else {
                                    toolbarElement.style.display = 'flex';
                                }
                            });
                            editor.enableReadOnlyMode('');
                        })
                        .catch(error => { console.error(error) });

                    ClassicEditor
                        .create(document.querySelector('#editor2'))
                        .then(function (editor) {
                            const toolbarElement = editor.ui.view.toolbar.element;
                            toolbarElement.style.display = 'none';
                        })
                        .catch(error => { console.error(error) });
                </script>
</body>
</html>