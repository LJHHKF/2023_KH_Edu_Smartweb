<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 채팅방</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<style>
	*{
		box-sizing: border-box;
	}
	div{
		border: 1px solid black;
	}
	.chat-container{
		width:800px;
		height:600px;
		margin:auto;
	}
	#message-area{
		height: 80%;
        width: 100%;
        overflow-y: auto;
        word-wrap: break-word;
        padding:10px;
	}
	.chat-container .chat-body{
		width:100%;
		height:20%;
		overflow:hidden;
	}
	#input-area{
		height:100%;
		width:100%;
		overflow-y:auto;
		word-wrap:break-word;
		float:left;
	}
	
	 #message-area>.row {
            overflow:hidden;
            width: 100%;
            border: none;
   }
        
     #message-area>.row>.message {
            float: left;
            min-width:30%;
            max-width: 90%;
            background-color: yellow;
            border-radius: 10px;
   }

    #message-area>.row>.dateBox {
            float: right;
            max-width: 10%;
            float: right;
            border: none;
   }
        
   .util-buttons{
   		text-align:right;
   }
</style>
</head>
<body>
	<div class="chat-container">
		<div id="message-area"></div>
		<div class="chat-body">
			<div id="input-area" contenteditable="true"></div>
		</div>
		<div class="util-buttons">
			<a href="/">
				<button type="button">돌아가기</button>
			</a>
		</div>
	</div>
	
	<script>
	
    	function updateScroll_down() {
        	let element = document.getElementById("message-area");
        	element.scrollTop = element.scrollHeight;
    	}
	
		$(function(){
			const ws = new WebSocket("ws://192.168.50.61/chat");
			
			$("#input-area").keydown(function(e){
				if(e.key == "Enter"){
					e.preventDefault();
					if(e.shiftKey){
						document.execCommand("insertLineBreak");
					}
				}
			})
			
			$("#input-area").keyup(function(e){
				if(e.key == "Enter" && !e.shiftKey){
					let message = $(this).html();
					ws.send(message);
					$(this).text("");
					$(this).focus();
				}
			});
			
			ws.onmessage = function(e){
				console.log(e.data);
				
				const row = $("<div>").addClass("row");
				const message = $("<div>").addClass("message");
				const dateBox = $("<div>").addClass("dateBox");
				const date = new Date();
				
				const data = JSON.parse(e.data);
				let tempMessage = data.id + " : " + data.message;
				dateBox.append(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
				message.append(tempMessage);
				row.append(message, dateBox);
				
				$("#message-area").append(row);
				updateScroll_down();
			}
		})
	</script>
</body>
</html>