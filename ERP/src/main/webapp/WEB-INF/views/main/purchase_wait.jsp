<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매 신청</title>
<!-- Jquery 사용 -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- empinfo CSS 적용 -->
<link rel="stylesheet"
	href="../../../resources/css/purchase_wait.css?version=1.8">
<!-- empinfo js -->
<script type="text/javascript"
	src="../../../resources/js/purchase_wait.js?version=1.8"></script>
<script type="text/javascript">
	function delchk() {
		return confirm("삭제하시겠습니까?");
	}
</script>
</head>
<body>
	<%
		String empno = (String) session.getAttribute("empno");
		session.setAttribute("empno", empno);
	%>
	<div class="container">
		<div class="main">
			<div class="main_line">
				<div class="empinfo_header">
					<h1>신청 승인 시스템</h1>
					<div>

						<a href="/main/purchase_wait" class="product_move">신청 대기</a> <a
							href="/main/purchase_all_list" class="product_move">주문 내역</a>

					</div>
					<div class="windowclose" onClick='window.close()'>
						<div class="windowclose_1"></div>
						<div class="windowclose_2"></div>
					</div>
				</div>
				<div class="empinfo_body">
					<form action="/cart/add" method="post"
						onsubmit="return jsSubmit();" id="form">
						<div class="body_left">
							<h3>상품 상세 정보</h3>
							<table>
								<tr>
									<td>거래처선택</td>
									<td><select name="companyname" id="selectBox1">
											<option value="">::업체를 선택하세요::</option>
											<c:forEach items="${productlist}" var="prolist">
												<option><c:out value="${prolist.companyname}" /></option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td>상품선택</td>
									<td><select name="productname" id="productname">
											<option value="">::상품을 선택하세요::</option>
									</select> <input type="hidden" id="productid" name="productid"></td>
								</tr>
								<tr>
									<td>상품 설명</td>
									<td><textarea rows="12" cols="28" id="productcnt"
											name="productcnt" readonly></textarea></td>
								</tr>
								<tr>
									<td>가격</td>
									<td><input type="text" id="price" name="price"
										maxlength="10" class="box" readonly> 원</td>
								</tr>
								<tr>
									<td>구매수량</td>
									<td><input type="text" id="productcount"
										name="productcount" maxlength="10" class="box"
										onkeyup="autoCal();"> 개</td>
								</tr>
								<tr>
									<td>총 가격</td>
									<td><input type="text" id="totalprice" name="totalprice"
										class="box" value=""> 원</td>
								</tr>
								<tr>
									<td></td>
									<td><input type="hidden" id="cartid" name="cartid"
										value="1" /></td>
								</tr>
							</table>

							<div class="button_div">
								<input type="hidden" id="signup" value="등록" class="submit"
									onclick="productSubmit();"> <a href="/main/purchase"><input
									type="hidden" value="취소" class="cancle"></a>
							</div>
						</div>
					</form>

					<div class="body_right">
						<h3>승인 대기 목록</h3>
						<table>
							<tr>
								<td>신청자</td>
								<td>거래처명</td>
								<td>상품명</td>
								<td>가격</td>
								<td>구매수량</td>
								<td>총 가격</td>
								<td>관리</td>
							</tr>

							<c:forEach items="${cartInfo}" var="cartInfo">
								<tr>
									<td>${cartInfo.name}</td>
									<td>${cartInfo.companyname}</td>
									<td>${cartInfo.productname}</td>
									<td><fmt:formatNumber value="${cartInfo.price}"
											pattern="#,### 원" /></td>
									<td><fmt:formatNumber value="${cartInfo.productcount}"
											pattern="#,### 개" /></td>
									<td><fmt:formatNumber value="${cartInfo.totalprice}"
											pattern="#,### 원" /></td>
									<td>
									
									<input type="button" value="상세" class="modify" onclick="return modify('${cartInfo.cartid}');">
										
									<a href="/cart/manager_delete?cartid=${cartInfo.cartid}">
									<input type="button" value="삭제" class="delete" onclick="return delchk();"></a>
										
									<a href="/cart/confirm?cartid=${cartInfo.cartid}">
									<input type="button" value="승인" class="confirm" onclick="return confirm();"></a>
											
									</td>
								</tr>
							</c:forEach>
						</table>
						<div class="page">
							<!-- prev(이전)이 true이면 이전버튼 활성화 -->
							<c:if test="${paging.prev}">
								<a class="pagemove"
									href="/main/purchase_wait?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${paging.startPage-1}&amount=${paging.cri.amount}"><</a>
							</c:if>
							<!-- begin(1)이 end(10) 될 동안 반복(1이 10 될 동안 반복) -->
							<c:forEach begin="${paging.startPage}" end="${paging.endPage}"
								var="num">

								<c:choose>
									<c:when test="${paging.cri.pageNum==num}">
										<a class="selectpage"
											href="/main/purchase_wait?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${num}&amount=${paging.cri.amount}">${num}</a>
									</c:when>
									<c:otherwise>
										<a class="pagenum"
											href="/main/purchase_wait?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${num}&amount=${paging.cri.amount}">${num}</a>
									</c:otherwise>
								</c:choose>

							</c:forEach>

							<!-- next(다음)이 true이면 다음버튼 활성화 -->
							<c:if test="${paging.next}">
								<a class="pagemove"
									href="/main/purchase_wait?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${paging.endPage+1}&amount=${paging.cri.amount}">></a>
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