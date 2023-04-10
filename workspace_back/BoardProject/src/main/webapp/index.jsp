<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<c:choose>
			<c:when test="${loginID == null}">
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
											<input type="password" name="pw" class="form-control" placeholder="Password를 입력해주세요.">
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
			</c:when>
			<c:otherwise>
				<div class="container">
					<table class="table table-primary table-striped table-borderd" align="center">
						<tr>
							<th colspan="3">${loginID}님 환영합니다.</th>
						</tr>
						<tr>
							<td align="center">
								<a href="/MyPage"><button>마이페이지</button></a>
							</td>
							<td align="center">
								<a href="/LogoutMemebers"><button id="Logout">로그아웃</button></a>
							</td>
							<td align="center">
								<button id="btn_outMembers">회원탈퇴</button>
							</td>
						</tr>
					</table>
				</div>
				<script>
					$("#btn_outMembers").click(function(){
						let result = confirm("정말로 탈퇴하시겠습니까?");
						if(result){
							location.href = "/OutMembers";
						}
					});
				</script>
			</c:otherwise>
		</c:choose>
	</body>

	</html>