<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>회원가입</title>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<div class="container border rounded border-primary">
            <form action="/member/create" id="registerForm" method="post">
                <div class="row">
                    <div class="col-12 text-center border border-primary bg-primary text-light">
                        <h1>회원 가입 정보</h1>
                    </div>
                </div>
                <div class="row align-items-center mb-1">
                    <div class="col-2 text-end">아이디</div>
                    <div class="col-6">
                        <input type="text" id="input_id" name="id" class="w-100">
                    </div>
                    <div class="col-2">
                        <button type="button" id="btn_id_dup" class="btn btn-primary">
                            중복 확인</button>
                    </div>
                    <div class="col-2" id=dup_result>
                    </div>
                </div>
                <div class="row align-items-center mb-1">
                    <div class="col-2 text-end">패스워드</div>
                    <div class="col-6">
                        <input type="password" id="input_pw" name="pw" class="w-100">
                    </div>
                </div>
                <div class="row align-items-center mb-1">
                    <div class="col-2 text-end">패스워드확인</div>
                    <div class="col-6">
                        <input type="password" id="input_repw" class="w-100">
                    </div>
                    <div class="col-4 text-start" id="repw_result"></div>
                </div>
                <div class="row align-items-center mb-1">
                    <div class="col-2 text-end">이름</div>
                    <div class="col-6">
                        <input type="text" id="input_name" name="name" class="w-100">
                    </div>
                </div>
                <div class="row align-items-center mb-1">
                    <div class="col-2 text-end">전화번호</div>
                    <div class="col-6">
                        <input type="text" id="input_phone" name="phone" class="w-100">
                    </div>
                </div>
                <div class="row align-items-center mb-1">
                    <div class="col-2 text-end">이메일</div>
                    <div class="col-6">
                        <input type="text" id="input_email" name="email" class="w-100">
                    </div>
                </div>
                <div class="row align-items-center mb-1">
                    <div class="col-2 text-end">우편번호</div>
                    <div class="col-6">
                        <input type="text" id="input_postcode" name="zipcode" class="w-100" readonly>
                    </div>
                    <div class="col-2">
                        <button type="button" id="btn_addr" class="btn btn-primary">
                            찾기</button>
                    </div>
                </div>
                <div class="row align-items-center mb-1">
                    <div class="col-2 text-end">주소1</div>
                    <div class="col-10">
                        <input type="text" id="input_addr1" name="address1" class="w-100" readonly>
                    </div>
                </div>
                <div class="row align-items-center mb-1">
                    <div class="col-2 text-end">
                        상세주소<br>(직접입력)
                    </div>
                    <div class="col-10">
                        <input type="text" id="input_addr2" name="address2" class="w-100">
                    </div>
                </div>
                <div class="row align-items-center">
                    <div class="col-4 text-center">
                        <button type="submit" class="btn btn-primary">회원가입</button>
                    </div>
                    <div class="col-4 text-center">
                        <button type="button" id="btn_reWrite" class="btn btn-primary">다시 입력</button>
                    </div>
                    <div class="col-4 text-center">
                        <a href="/"><button type="button" class="btn btn-primary">돌아가기</button></a>
                    </div>
                </div>
            </form>
        </div>

        <script>
            //var idValidFlag = false;
            $("#input_id").focus();
            $("#registerForm").submit(function () {
                if (idValidFlag) {
                    let regex_id = /^[A-Za-z][A-Za-z0-9_]{6,12}$/;
                    //pw regex는 임의로 미추가
                    let regex_name = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,4}$/;
                    let regex_phone = /^01\d\d{3,4}\d{4}$/;
                    let regex_email = /.+?@.+?\./;
                    if (!regex_id.test($("#input_id").val())) {
                        swal.fire({
                            icon: "error",
                            title: "아이디 오류",
                            text: "입력한 아이디 및 규칙을 다시 확인해주세요.",
                            footer: "첫문자는 영단어, 나머지는 영단어 및 숫자와 _만 가능. 7글자~13글자."
                        });
                        return false;
                    } else if ($("#input_pw").val() != $("#input_repw").val()) {
                        swal.fire({
                            icon: "error",
                            title: "비밀번호 오류",
                            text: "두 비밀번호를 같게 입력해주세요.",
                        });
                        return false;
                    } else if (!regex_name.test($("#input_name").val())) {
                        swal.fire({
                            icon: "error",
                            title: "이름 오류",
                            text: "입력한 이름 및 규칙을 다시 확인해주세요.",
                            footer: "한글만. 2~4글자."
                        });
                        return false;
                    } else if (!regex_phone.test($("#input_phone").val())) {
                        swal.fire({
                            icon: "error",
                            title: "전화번호 오류",
                            text: "입력한 전화번호를 다시 확인해주세요."
                        });
                        return false;
                    } else if (!regex_email.test($("#input_email").val())) {
                        swal.fire({
                            icon: "error",
                            title: "이메일 오류",
                            text: "입력한 이메일을 다시 확인해주세요."
                        });
                        return false;
                    } else if ($("#input_postcode").val().length == 0 && $("#input_addr1").val().length == 0) {
                        swal.fire({
                            icon: "error",
                            title: "주소 오류",
                            text: "주소를 입력해주세요."
                        });
                        return false;
                    }
                } else {
                    swal.fire({
                       icon: "error",
                       title: "중복 확인 오류",
                       text: "중복 확인을 통과하셔야 합니다."
                    });
                    return false;
                }

            });

            $("#btn_addr").click(function () {
                new daum.Postcode({
                    oncomplete: function (data) {
                        $("#input_postcode").val(data.zonecode);
                        $("#input_addr1").val(data.roadAddress);
                        $("#input_addr2").focus();
                    }
                }).open();
            });

            $("#input_repw").keyup(function () {
                if ($(this).val() == $("#input_pw").val()) {
                    $("#repw_result").html("비밀번호가 일치합니다");
                    $("#repw_result").css("color", "blue");
                } else {
                    $("#repw_result").html("비밀번호가 일치하지 않습니다.");
                    $("#repw_result").css("color", "red");
                }
            });

            $("#btn_reWrite").click(function () {
                $("#input_id").val("");
                $("#input_pw").val("");
                $("#input_repw").val("");
                $("#repw_result").html("");
                $("#input_name").val("");
                $("#input_phone").val("");
                $("#input_email").val("");
                $("#input_postcode").val("");
                $("#input_addr1").val("");
                $("#input_addr2").val("");
            });

            $("#btn_id_dup").click(function () {
                //window.open("/idCheck.members?id=" + $("#input_id").val(), "", "width=400px, height=200px");
               $.ajax({
                	url : "/member/idCheck",
                	type: "get",
                	data: {
                		id : $("#input_id").val()
                	},
                	dataType : "json"
                }).done(function(resp){
                	//boolean
                	if(resp){
                		$("#dup_result").text("중복 검사를 통과하지 못하였습니다.");
                		idValidFlag = false;
                	}else{
                		$("#dup_result").text("중복 검사를 통과하셨습니다.");
                		idValidFlag = true;
                	}
                });
            });
            $("#input_id").keydown(function (e) {
                idValidFlag = false;
                if (e.key == "Enter") {
                    e.preventDefault();
                    $("#input_pw").focus();
                }
            });
            $("#input_pw").keydown(function (e) {
                if (e.key == "Enter") {
                    e.preventDefault();
                    $("#input_repw").focus();
                }
            });
            $("#input_repw").keydown(function (e) {
                if (e.key == "Enter") {
                    e.preventDefault();
                    $("#input_name").focus();
                }
            });
            $("#input_name").keydown(function (e) {
                if (e.key == "Enter") {
                    e.preventDefault();
                    $("#input_phone").focus();
                }
            });
            $("#input_phone").keydown(function (e) {
                if (e.key == "Enter") {
                    e.preventDefault();
                    $("#input_email").focus();
                }
            });
            $("#input_email").keydown(function (e) {
                if (e.key == "Enter") {
                    e.preventDefault();
                    $("#btn_addr").click();
                }
            });

        </script>
</body>
</html>