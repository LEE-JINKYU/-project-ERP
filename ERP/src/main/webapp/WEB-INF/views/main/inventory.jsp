<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고 관리</title>
<!-- Jquery 사용 -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- main CSS 적용 -->
<link rel="stylesheet"
	href="../../../resources/css/inventory.css?version=1.5">
<!-- inventory js 적용 -->
<script type="text/javascript"
	src="../../../resources/js/inventory.js?version=1.1"></script>
</head>
<body>
	<div class="container">
		<div class="main">
			<div class="main_line">
				<div class="header">
					<h1>재고 관리 시스템</h1>
					
					<div class="windowclose" onClick='window.close()'>
						<div class="windowclose_1"></div>
						<div class="windowclose_2"></div>
					</div>
				</div>
				<div class="search_area">

					<table class="up_table">

						<tr>
							<th>거래처선택</th>
							<td class="me"><select name="companyname" id="selectBox1">
									<option value="">::업체를 선택하세요::</option>
									<c:forEach items="${productlist}" var="prolist">
										<option><c:out value="${prolist.companyname}" /></option>
									</c:forEach>
							</select> <a href="/main/inventory" class="clear"><img src="../../../resources/image/clear.png" class="clear"></a></td>
							<th>상품선택</th>
							<td><select name="productname" id="productname">
									<option value="">::상품을 선택하세요::</option>
							</select><input type="hidden" id="productid" name="productid"></td>
						</tr>

						<tr>
							<th>검색</th>
							<td><form action="/main/inventory" id="searchForm"
									method="get">
									<div class="rightdiv">
										<select name="type" class="selectbox">
											<option value="C">거래처명</option>
											<option value="P">상품명</option>
										</select> <span><input name="keyword" type="text"
											placeholder="검색어 입력" class="search"></span> <span><input
											type="submit" value="검색" class="searchbtn"></span>
									</div>
								</form></td>
						</tr>

					</table>

				</div>
				<!-- search_area -->
				<div class="table_area">
					<table class="down_table">
							<tr>
								<td>상품코드</td>
								<td>업체명</td>
								<td>상품명</td>
								<td>가격</td>
								<td>수량</td>
							</tr>
							<c:forEach items="${inventory}" var="inventory">
								<tr>
									<td>${inventory.productid}</td>
									<td>${inventory.companyname}</td>
									<td>${inventory.productname}</td>
									<td>${inventory.price}</td>
									<td>${inventory.sumcount}</td>
								</tr>
							</c:forEach>
					</table>
					<div class="page">
						<!-- prev(이전)이 true이면 이전버튼 활성화 -->
						<c:if test="${paging.prev}">
							<a class="pagemove"
								href="/main/inventory?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${paging.startPage-1}&amount=${paging.cri.amount}"><</a>
						</c:if>
						<!-- begin(1)이 end(10) 될 동안 반복(1이 10 될 동안 반복) -->
						<c:forEach begin="${paging.startPage}" end="${paging.endPage}"
							var="num">

							<c:choose>
								<c:when test="${paging.cri.pageNum==num}">
									<a class="selectpage"
										href="/main/inventory?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${num}&amount=${paging.cri.amount}">${num}</a>
								</c:when>
								<c:otherwise>
									<a class="pagenum"
										href="/main/inventory?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${num}&amount=${paging.cri.amount}">${num}</a>
								</c:otherwise>
							</c:choose>

						</c:forEach>

						<!-- next(다음)이 true이면 다음버튼 활성화 -->
						<c:if test="${paging.next}">
							<a class="pagemove"
								href="/main/inventory?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${paging.endPage+1}&amount=${paging.cri.amount}">></a>
						</c:if>
					</div>
				</div>
				<!-- table_area -->
			</div>
			<!-- main_line -->
		</div>
		<!-- main -->
	</div>
	<!-- container -->
</body>
</html>