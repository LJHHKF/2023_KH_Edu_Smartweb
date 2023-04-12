<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>MyPage</title>
            <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
                rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
            <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        </head>

        <body>
            <div class="container border rounded border-primary p-0">
                <form action="/update.members" method="post">
                    <div class="row align-items-center">
                        <div class="col-12">
                            <table class="table table-primary table-striped table-bordered">
                                <tr>
                                    <th>ID:</th>
                                    <td><input type="text" name="id" value="${myDto.id}" class="form-control" readonly></td>
                                </tr>
                                <tr>
                                    <th>Password:</th>
                                    <td><input type="password" name="pw" value="${myDto.pw}" class="form-control" readonly></td>
                                </tr>
                                <tr>
                                    <th>name:</th>
                                    <td><input type="text" name="name" value="${myDto.name}" class="form-control" readonly></td>
                                </tr>
                                <tr>
                                    <th>Phone:</th>
                                    <td><input type="text" name="phone" value="${myDto.phone}" class="form-control" readonly></td>
                                </tr>
                                <tr>
                                    <th>E-Mail:</th>
                                    <td><input type="text" name="email" value="${myDto.email}" class="form-control" readonly></td>
                                </tr>
                                <tr>
                                    <th>ZipCode:</th>
                                    <td>
                                        <input type="text" name="zipcode" value="${myDto.zipcode}" class="form-control" readonly>
                                        <button type="button" id="btn_address" style="display:none;" class="btn btn-primary">주소찾기</button>
                                    </td>
                                    
                                </tr>
                                <tr>
                                    <th>address1:</th>
                                    <td><input type="text" name="address1" value="${myDto.address1}" class="form-control" readonly></td>
                                </tr>
                                <tr>
                                    <th>adress2:</th>
                                    <td><input type="text" name="address2" value="${myDto.address2}" class="form-control" readonly></td>
                                </tr>
                                <tr>
                                    <th>Join Date:</th>
                                    <td><input type="date" name="join_date" class="form-control" readonly></td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center" id="control">
                                        <button id="btn_update" type="button" class="btn btn-primary">정보수정</button>
                                        <button type="button" id="btn_back" class="btn btn-primary">돌아가기</button>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </form>
            </div>
            <script>
                let val = "<c:out value='${myDto.join_date}'></c:out>";
                $("[name='join_date']").val(val.substring(0,10));

                $("#btn_update").click(function () {
                    $("input").removeAttr("readonly");
                    $("#btn_update, #btn_back").css("display", "none");

                    $("[name='id']").attr("readonly", "true");
                    $("[name='zipcode']").attr("readonly","true");
                    $("[name='address1']").attr("readonly","true");
                    $("[name='join_date']").attr("readonly","true");
                    $("#btn_address").css("display", "block");

                    let btn_updateComplete = $("<button>");
                    btn_updateComplete.text("수정완료");
                    btn_updateComplete.addClass("btn");
                    btn_updateComplete.addClass("btn-primary");

                    let btn_cancel = $("<button>");
                    btn_cancel.attr("type", "button");
                    btn_cancel.text("취소");
                    btn_cancel.addClass("btn");
                    btn_cancel.addClass("btn-primary");
                    btn_cancel.click(function () {
                        location.reload();
                    });

                    $("#control").prepend(btn_updateComplete);
                    $("#control").append(btn_cancel);

                    btn_updateComplete.click(function () {
                        let regex_id = /^[A-Za-z][A-Za-z0-9_]{6,12}$/;
                        //pw regex는 임의로 미추가
                        let regex_name = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,4}$/;
                        let regex_phone = /^01\d\d{3,4}\d{4}$/;
                        let regex_email = /.+?@.+?\./;
                        if (!regex_id.test($("[name='id']").val())) {
                            swal.fire({
                                icon: "error",
                                title: "아이디 오류",
                                text: "입력한 아이디 및 규칙을 다시 확인해주세요.",
                                footer: "첫문자는 영단어, 나머지는 영단어 및 숫자와 _만 가능. 7글자~13글자."
                            });
                            return false;
                        }else if (!regex_name.test($("[name='name']").val())) {
                            swal.fire({
                                icon: "error",
                                title: "이름 오류",
                                text: "입력한 이름 및 규칙을 다시 확인해주세요.",
                                footer: "한글만. 2~4글자."
                            });
                            return false;
                        } else if (!regex_phone.test($("[name='phone']").val())) {
                            swal.fire({
                                icon: "error",
                                title: "전화번호 오류",
                                text: "입력한 전화번호를 다시 확인해주세요."
                            });
                            return false;
                        } else if (!regex_email.test($("[name='email']").val())) {
                            swal.fire({
                                icon: "error",
                                title: "이메일 오류",
                                text: "입력한 이메일을 다시 확인해주세요."
                            });
                            return false;
                        } else if ($("[name='zipcode']").val().length == 0 && $("name='address1'").val().length == 0) {
                            swal.fire({
                                icon: "error",
                                title: "주소 오류",
                                text: "주소를 입력해주세요."
                            });
                            return false;
                        }
                    });
                });
                $("#btn_back").click(function(){
                    location.href = "/index.jsp";
                });

                $("#btn_address").click(function () {
                new daum.Postcode({
                    oncomplete: function (data) {
                        $("[name='zipcode']").val(data.zonecode);
                        $("[name='address1']").val(data.roadAddress);
                        $("[name='address2']").focus();
                    }
                }).open();
            });
            </script>
        </body>

        </html>