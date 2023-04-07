<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ContactsList</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
    <table border="1" align="center">
        <tr>
            <th colspan="4">Contacts List</th>
        </tr>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Contact</th>
            <th>Birthday</th>
        </tr>
        <c:forEach var="i" items="${list}">
            <tr>
                <td>${i.id}</td>
                <td>${i.name}</td>
                <td>${i.contact}</td>
                <td>${i.getFormedBirthday()}</td>
            </tr>
        </c:forEach>
        <form action="UpdateContacts" method="get" id="updateForm">
            <tr>
                <td colspan="2">
                    <input type="text" name="updateID" placeholder="수정할 요소의 id를 입력해주세요.">
                </td>
                <td colspan="2" rowspan="4">
                    <button type="button" id="btn_update">수정</button>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="text" name="updateName" placeholder="수정할 이름 값을 입력해주세요.">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="text" name="updateContact" placeholder="수정할 연락처 값을 입력해주세요.">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="date" name="updateBirthday" required>
                </td>
            </tr>
        </form>
        <tr>
            <form action="DeleteContacts" method="get" id="deleteForm">
                <td colspan="2">
                    <input type="text" name="deleteID" placeholder="삭제할 요소의 id를 입력해주세요.">
                </td>
                <td colspan="2">
                    <button type="button" id="btn_delete">삭제</button>
                </td>
            </form>
        </tr>
        <tr>
            <td colspan="4" align="center"><a href="index.html">back</a></td>
        </tr>
    </table>

    <script>
        function idCheck(str){
            if(!str){
                alert("ID 값은 빈 값일 수 없습니다.");
                return false;
            }else if(str.split(" ").join("").length !== str.length){
                alert("ID 값은 공백을 포함할 수 없습니다.");
                return false;
            }else if(isNaN(str)){
                alert("ID 값은 숫자 값이어야 합니다.");
                return false;
            }else{
                return true;
            }
        }
        function dateCheck(date){
            if(!date){
                alert("날짜 값은 빈 값일 수 없습니다.");
                return false;
            }else{
                return true;
            }
        }
        let updateDate = document.getElementsByName('updateBirthday')[0];
        updateDate.value = new Date().toISOString().substring(0, 10);

        document.getElementById("btn_update").addEventListener("click", function(){
            let updateID = document.getElementsByName("updateID")[0];
            if(idCheck(updateID.value)){
                if(dateCheck(updateDate.value)){
                    document.getElementById("updateForm").submit();
                }else{
                    updateDate.value = new Date().toISOString().substring(0, 10);
                }
            }else{
                updateID.value ='';
            }
        });

        $("#btn_delete").click(function(){
            let deleteID = document.getElementsByName("deleteID")[0];
            if(idCheck(deleteID.value)){
                //document.getElementById("deleteForm").submit();
                location.href = "DeleteContacts?deleteID="+deleteID.value;
            }else{
                deleteID.value = "";
            }
        });

        $("[name='updateID']").keyup(function(e){
            if(e.key == "Enter"){
                document.getElementsByName("updateName")[0].focus();
            }
        });
        document.getElementsByName("updateName")[0].addEventListener("keyup", function(e){
            if(e.key == "Enter"){
                document.getElementsByName("updateContact")[0].focus();
            }
        });
        document.getElementsByName("deleteID")[0].addEventListener("keyup", function(e){
            if(e.key == "Enter"){
                document.getElementById("btn_delete").click();
            }
        });

    </script>
</body>
</html>