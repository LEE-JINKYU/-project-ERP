<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기</title>
<!-- Jquery 사용 -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- main CSS 적용 -->
<link rel="stylesheet"
	href="../../../resources/css/idsearch.css?version=1.5">
<script type="text/javascript" src="../../../resources/js/idsearch.js"></script>
</head>
<body>
	<div class="container">
		<div class="main">
			<div class="background">

				<div class="idsearch">
					<h1 id="logincss">사원번호/비밀번호 찾기</h1>
					<div class="search_select">
						<div class="radio_1">
							<input type="radio" name="찾기" checked="checked"
								onclick="search_check(1)"> <label>사원번호 찾기 </label>
						</div>
						<div class="radio_2">
							<input type="radio" name="찾기" onclick="search_check(2)">
							<label>비밀번호 찾기</label>
						</div>
					</div>

					<form action="/login/idsearch" method="post" id="searchId">
						<input type="text" name="name" maxlength='10' class="input" placeholder="이름를 입력하세요"> 
						<input type="text" name="phone"maxlength='11' class="input" placeholder="전화번호를 입력하세요">
						<input type="submit"value="확인" class="loginbutton">
					</form>
					<form action="/login/pwsearch" method="post" id="searchPw"
						style="display: none">
						<input type="text" name="empno" class="input" id="idchk"
							placeholder="사원번호를 입력해주세요"> <input type="text"
							name="name" class="input" placeholder="이름을 입력해주세요"> <input
							type="submit" value="확인" class="loginbutton">
					</form>
				</div>

			</div>
			<!-- background -->
			<div class="home">
				<a href="/login"><img
					src="../../../resources/image/apple_home.png"></a>
			</div>
		</div>
		<!-- main -->
	</div>
	<!-- container -->
</body>
</html>