<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
    <fieldset>
        <legend>1. 기본 AJAX</legend>
        <button id="ajax1">테스트</button>
    </fieldset>
    <fieldset>
        <legend>2. 파라미터를 전달하는 AJAX 통신</legend>
        <button id="ajax2">테스트</button>
    </fieldset>
    <fieldset>
        <legend>3. Response를 돌려받는 AJAX 통신</legend>
        <button id="ajax3">테스트</button>
    </fieldset>
    <fieldset>
        <legend>4. 배열을 돌려받는 AJAX 통신</legend>
        <button id="ajax4">테스트</button>
    </fieldset>
    <fieldset>
        <legend>5. 객체를 돌려받는 비동기 통신</legend>
        <button id="ajax5">테스트</button>
    </fieldset>
    <fieldset>
        <legend>6. 객체 배열을 돌려받는 비동기 통신</legend>
        <button id="ajax6">테스트</button>
    </fieldset>
    <fieldset>
        <legend>7. 여러 데이터를 돌려받는 비동기 통신</legend>
        <button id="ajax7">테스트</button>
    </fieldset>
    

    <script>
        //1. 가장 기본 통신
        $("#ajax1").click(function(){
            $.ajax({
                url:"/exam01.ajax",
            });
        })
        //2. 파라미터를 가지는 비동기 통신 : client -> server
        $("#ajax2").click(function(){
            $.ajax({
                url:"/exam02.ajax",
                type:"post",
                data:{
                    fruit:"Apple", // <input type="text" name="fruit" value="Apple"> 과 동일.
                    music:"Piano"
                }
            });
        });
        //3.Return 값을 가지는 비동기 통신 : server -> client
        $("#ajax3").click(function(){
            $.ajax({
                url:"/exam03.ajax"
            }).done(function(resp){
                console.log(resp);
            });
        });
        //4.배열을 돌려받는 비동기 통신 : server -> client
        $("#ajax4").click(function(){
            $.ajax({
                url:"/exam04.ajax",
                dataType:"json"
            }).done(function(resp){
                //역직렬화
                //resp = JSON.parse(resp);
                console.log(resp[0]);
            });
        });
        //5.객체를 돌려받는 비동기 통신 : server -> client
        $("#ajax5").click(function(){
            $.ajax({
                url : "/exam05.ajax",
                dataType : "json"
            }).done(function(resp){
                console.log(resp);
                console.log(resp.name);
            });
        });
        //6. 객체 배열을 돌려받는 비동기 통신 : server -> client
        $("#ajax6").click(function(){
        	$.ajax({
        		url : "/exam06.ajax",
        		dataType : "json"
        	}).done(function(resp){
        		console.log(resp);
        	});
        });
        //7. 여러 타입의 데이터를 돌려받는 비동기 통신
        $("#ajax7").click(function(){
            $.ajax({
                url:"/exam07.ajax",
                dataType : "json"
            }).done(function(resp){
                console.log(resp);
            });
        });
    </script>
</body>
</html>