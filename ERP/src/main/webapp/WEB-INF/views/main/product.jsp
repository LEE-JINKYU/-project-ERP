<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
<!-- Jquery 사용 -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- empinfo CSS 적용 -->
<link rel="stylesheet"
	href="../../../resources/css/product.css?version=1.8">
<!-- empinfo js -->
<script type="text/javascript"
	src="../../../resources/js/product.js?version=1.0"></script>
<script type="text/javascript">
	function delchk() {
		return confirm("삭제하시겠습니까?");
	}
</script>
</head>
<body>
	<div class="container">
		<div class="main">
			<div class="main_line">
				<div class="empinfo_header">
					<h1>상품 정보 시스템</h1>
					<div>
						<a href="/main/account" class="account_move">거래처 등록</a> <a
							href="/main/product" class="product_move">상품 등록</a>
					</div>
					<div class="windowclose" onClick='window.close()'>
						<div class="windowclose_1"></div>
						<div class="windowclose_2"></div>
					</div>
				</div>
				<div class="empinfo_body">
					<form action="/main/product_signup" method="post"
						onsubmit="return jsSubmit();" id="form">
						<div class="body_left">
							<h3>상품 정보 입력</h3>
							<table>
								<tr>
									<td>상품코드</td>
									<td><input type="text" id="idchk" name="productid"
										maxlength="20" class="box"><span><input
											type="button" value="중복확인" onclick="idCheck()"
											class="check_box"></span> <br> <span id="idmsg"
										class="msg"></span></td>
								</tr>
								<tr>
									<td>거래처선택</td>
									<td><select name="companyname" id="selectBox">
											<option>:: 업체 선택 ::</option>
											<c:forEach items="${companylist}" var="comlist">
												<option><c:out value="${comlist.companyname}" /></option>
											</c:forEach>
									</select> <input type="hidden" id="companyid" name="companyid"></td>
								</tr>
								<tr>
									<td>상품명</td>
									<td><input type="text" id="productname" name="productname"
										maxlength="10" class="box"></td>
								</tr>
								<tr>
									<td>가격</td>
									<td><input type="text" id="price" name="price"
										maxlength="10" class="box"></td>
								</tr>

								<tr>
									<td>상품 설명</td>
									<td><textarea rows="12" cols="28" id="productcnt"
											name="productcnt"></textarea></td>
								</tr>


							</table>

							<div class="button_div">
								<input type="submit" id="signup" value="등록" class="submit">
								<a href="/main/product"><input type="button" value="취소"
									class="cancle"></a>
							</div>
						</div>
					</form>

					<div class="body_right">
						<h3>상품 리스트</h3>
						<table>
							<tr>
								<td>상품코드</td>
								<td>거래처명</td>
								<td>상품명</td>
								<td>가격</td>
								<td>관리</td>
							</tr>
							<c:forEach items="${productlist}" var="prolist">
								<tr>
									<td>${prolist.productid}</td>
									<td>${prolist.companyname}</td>
									<td>${prolist.productname}</td>
									<td>${prolist.price}</td>
									<td><input type="button" value="상세" class="modify"
										onclick="return modify('${prolist.productid}');"> <a
										href="/main/product_delete?productid=${prolist.productid}"><input
											type="button" value="삭제" class="delete"
											onclick="return delchk();"></a></td>
								</tr>
							</c:forEach>

						</table>
						<div class="page">
							<!-- prev(이전)이 true이면 이전버튼 활성화 -->
							<c:if test="${paging.prev}">
								<a class="pagemove"
									href="/main/product?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${paging.startPage-1}&amount=${paging.cri.amount}"><</a>
							</c:if>
							<!-- begin(1)이 end(10) 될 동안 반복(1이 10 될 동안 반복) -->
							<c:forEach begin="${paging.startPage}" end="${paging.endPage}"
								var="num">

								<c:choose>
									<c:when test="${paging.cri.pageNum==num}">
										<a class="selectpage"
											href="/main/product?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${num}&amount=${paging.cri.amount}">${num}</a>
									</c:when>
									<c:otherwise>
										<a class="pagenum"
											href="/main/product?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${num}&amount=${paging.cri.amount}">${num}</a>
									</c:otherwise>
								</c:choose>

							</c:forEach>

							<!-- next(다음)이 true이면 다음버튼 활성화 -->
							<c:if test="${paging.next}">
								<a class="pagemove"
									href="/main/product?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${paging.endPage+1}&amount=${paging.cri.amount}">></a>
							</c:if>
						</div>
						<!-- page div -->
					</div>
					<!--  body_right -->

				</div>
			</div>
			<!-- main_line -->
		</div>
		<!-- main -->
	</div>
	<!-- container -->
</body>
</html>