<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>

	<input type="text" id="message" placeholder="서버로 전송할 메시지 입력">
	<button type="button" id="send">전송</button>

	<script>
		const socket = new WebSocket("ws://192.168.50.61/chat");
		const stompClient = Stomp.over(socket); //데코레이터 패턴
		
		//( {header. 연결 설정 정보. 채팅에선 쓸 일 없으니 비워두면 됨.} , function(){ 스톰프 연결 성공했을 떄 실행할 콜백 } )
		stompClient.connect({},function(){
			//1번쨰 파라미터: 구독할 채널 이름 & 매핑
			//2번쨰 파라미터: 구독 후 메시지 이벤트 도착 시 마다 수행할 콜백
			//반환 값은 나중에 구독 취소하고 싶을 때 사용할 용도.
			const subscription = stompClient.subscribe("/topic/test", function(message){
				console.log(message);
				if(typeof JSON.parse(message.body) == "object"){
					console.log("object");
					console.log(JSON.parse(message.body).message);
				}else{
					console.log("message");
					console.log(message.body);
				}
			});
		},function(error){
			console.log("연결 실패");
			console.log(error);
		});
		
		$("#send").click(function(){
			let message = $("#message").val();
			// /app은 'WebSocketConfig'에서 설정한 값임. 위의 /topic과 마찬가지.
			const destination = "/app/message";
			const header = {};
			const body = JSON.stringify(
						{name:"Tester1234", message:message}
					);
			stompClient.send(destination, header, body);
		});
	</script>
</body>
</html>