<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<form action="" method="get">
			<div class="row justify-content-center">
				<div class="col-6 border">
					<div class="row">
						<div class="col-12 text-center border">Login Box</div>
					</div>
					<div class="row">
						<div class="col-3 border">ID:</div>
						<div class="col-9 border">
							<input type="text" name="id" placeholder="id를 입력해주세요."
								class="w-100" style="border-radius: 10px;">
						</div>
					</div>
					<div class="row">
						<div class="col-3 border">PW:</div>
						<div class="col-9 border">
							<input type="password" name="pw" placeholder="password를 입력해주세요."
								class="w-100" style="border-radius: 10px;">
						</div>
					</div>
					<div class="row border">
						<div class="col-12 text-center">
							<button type="submit">로그인</button>
							<button type="button" id="toJoin">회원가입</button>
						</div>
						<div class="col-12 text-center">
							<input type="checkbox">ID 기억하기
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script>
		$("#toJoin").on("click", function(){
			location.href = "/member/joinform.jsp";
		});
	</script>
</body>
</html>