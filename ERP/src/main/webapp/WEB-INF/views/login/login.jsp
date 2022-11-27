<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<!-- Jquery 사용 -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- main CSS 적용 -->
<link rel="stylesheet"
	href="../../../resources/css/login.css?version=1.3">
</head>
<body>
	<div class="container">
		<div class="main">
			<div class="main_image"></div>
			<!-- main_image -->
			<div class="main_body">
				<h1 class="login_title">Login</h1>
				<form action="/login" method="post">
					<table>
						<tr>
							<td>사원번호</td>
							<td><input class="login_box" type="text" name="empno" id="idchk" placeholder="아이디를 입력해주세요"></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input class="login_box" type="password" name="password" id="pwchk"placeholder="비밀번호를 입력해주세요"></td>
						</tr>
						<tr>
							<td colspan="3"><a href="/idsearch" class="find_id">사원번호/비밀번호찾기</a>
						</tr>
						<tr>
							<td colspan="2"><input class="login_button" type="submit"
								value="로그인"></td>
						</tr>
					</table>
				</form>
			</div>
			<!-- main_body -->
		</div>
		<!-- main -->
	</div>
	<!-- container -->
</body>
</html>