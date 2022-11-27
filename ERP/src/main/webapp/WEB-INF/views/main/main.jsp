<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
<!-- Jquery 사용 -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- main CSS 적용 -->
<link rel="stylesheet"
	href="../../../resources/css/main.css?version=2.0">
<!-- main JS 적용 -->
</head>
<body>
	<%
		String empno = (String) session.getAttribute("empno");

		session.setAttribute("empno", empno);
	%>
	<div class="container">
		<div class="main">
			<div class="main_line">
				<div class=header>
					<div class="date_bar">
						<p class="clock"></p>
						<script type="text/javascript" src="../../../resources/js/main.js"></script>
					</div>
					<div class="google_search">
						<form name="form" action="https://www.google.com/search" method="get" target="_blank">
						<div class="google_search_area">
							<input type="text" name="q" placeholder="구글에서 검색하세요." class="google_search_blank">
							<button type="submit" class="google_button">🔍</button>
							</div>
						</form>
					</div>
					<div class="member">
						<span class="loginid">${empno} 님 환영합니다!</span>
					</div>
					<div class="logout">
						<a href="/logout">로그아웃</a>
					</div>
				</div>

				<div class="empinfo">
					<a href="/main/mypage"
						onclick="window.open(this.href, '_blank', 'width=1300, height=810');return false;"><span
						class="text">내 정보</span></a>
				</div>

				<div class="purchase">
					<a href="/main/purchase"
						onclick="window.open(this.href, '_blank', 'width=1300, height=810');return false;"><span
						class="text">구매관리</span></a>
				</div>
				<!-- 업체관리 -->

				<div class="inventory">
					<a href="/main/inventory"
						onclick="window.open(this.href, '_blank', 'width=1300, height=810');return false;"><span
						class="text">자재관리</span></a>
				</div>
				<!-- 자재관리 -->
			</div>
			<!-- main_line -->
		</div>
		<!-- main -->
	</div>
	<!-- container -->
</body>
</html>