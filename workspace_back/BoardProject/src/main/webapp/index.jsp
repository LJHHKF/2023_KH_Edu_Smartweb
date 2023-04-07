<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"></script>
	</head>

	<body>
		<div class="container">
			<form action="/LoginMembers" method="post">
				<div class="row justify-content-center">
					<div class="col-6 border rounded border-primary">
						<div class="row">
							<div class="col-12 text-center border border-primary">Login Box</div>
						</div>
						<div class="row">
							<div class="col-12 border border-primary">
								<div class="input-group">
									<span class="input-group-text">ID:</span>
									<input type="text" name="id" class="form-control" placeholder="id를 입력해주세요.">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-12 border border-primary">
								<div class="input-group">
									<span class="input-group-text">PW:</span>
									<input type="text" name="pw" class="form-control" placeholder="Password를 입력해주세요.">
								</div>
							</div>
						</div>
						<div class="row border border-primary">
							<div class="col-12 text-center">
								<button type="submit" class="btn btn-primary">로그인</button>
								<button type="button" id="toJoin" class="btn btn-primary">회원가입</button>
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
			$("#toJoin").on("click", function () {
				location.href = "/member/joinform.jsp";
			});
		</script>
		<c:if test="${param.state=='a_j'}">
			<script>
				alert("회원가입을 축하합니다.");
			</script>
		</c:if>
	</body>

	</html>