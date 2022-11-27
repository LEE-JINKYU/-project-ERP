<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>거래처 관리</title>
<!-- Jquery 사용 -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- empinfo CSS 적용 -->
<link rel="stylesheet"
	href="../../../resources/css/account.css?version=1.6">
<!-- empinfo js -->
<script type="text/javascript"
	src="../../../resources/js/account.js?version=1.3"></script>
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
					<h1>거래처 정보 시스템</h1>
					<div>
						<a href="/main/account" class="account_move">거래처 등록</a> 
						<a href="/main/product" class="product_move">상품 등록</a>
					</div>
					<div class="windowclose" onClick='window.close()'>
					<div class="windowclose_1"></div>
					<div class="windowclose_2"></div>
					</div>
				</div>
				<div class="empinfo_body">
					<form action="/main/account_signup" method="post"
						onsubmit="return jsSubmit();" id="form">
						<div class="body_left">
							<h3>거래처 정보 입력</h3>
							<table>
								<tr>
									<td>거래처코드</td>
									<td><input type="text" id="idchk" name="companyid"
										maxlength="20" class="box"><span><input
											type="button" value="중복확인" onclick="idCheck()"
											class="check_box"></span> <br> <span id="idmsg"
										class="msg"></span></td>
								</tr>
								<tr>
									<td>거래처명</td>
									<td><input type="text" id="companyname" name="companyname"
										maxlength="10" class="box"></td>
								</tr>
								<tr>
									<td>대표자</td>
									<td><input type="text" id="companymanager" name="companymanager"
										maxlength="10" class="box"></td>
								</tr>
								<tr>
									<td>업태</td>
									<td><input type="text" id="companytype" name="companytype"
										maxlength="10" class="box"></td>
								</tr>
								<tr>
									<td>전화번호</td>
									<td><select name="companyphone" class="emailselect">
											<option value="02">02</option>
											<option value="051">051</option>
											<option value="052">052</option>
											<option value="053">053</option>
											<option value="054">054</option>
									</select> - <input type="text" name="companyphone" id="numchk1" maxlength='4'
										class="shortbox"
										onKeyup="this.value=this.value.replace(/[^-0-9]/g,'');"
										class="box"> - <input type="text" name="companyphone"
										id="numchk2" maxlength='4' class="shortbox"
										onKeyup="this.value=this.value.replace(/[^-0-9]/g,'');"
										class="box"> <span id="nummsg" class="msg"></span></td>
								</tr>
								<tr>
									<td>주소</td>
									<td><input type="text" name="companyaddr" id="addr1"
										placeholder="우편번호" class="box">
										<input type="button"
										id="addrButton" onclick="sample6_execDaumPostcode()"
										value="우편번호 찾기" class="check_box"><br>
										 <input
										type="text" name="companyaddr" id="addr2" placeholder="주소"
										class="box"><br> 
										<input type="text" name="companyaddr"
										id="addrDetail" placeholder="상세주소" class="box"> 
										<input
										type="text" name="companyaddr" id="addrExtra" placeholder="참고항목"
										class="box"></td>
								</tr>
								<tr>
									<td>거래시작일</td>
									<td><input type="date" name="startdate" id="hdchk"
										class="box"></td>
								</tr>
							</table>
							<script
								src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
							<script>
								function sample6_execDaumPostcode() {
									new daum.Postcode(
											{
												oncomplete : function(data) {
													// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

													// 각 주소의 노출 규칙에 따라 주소를 조합한다.
													// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
													var addr = ''; // 주소 변수
													var extraAddr = ''; // 참고항목 변수

													//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
													if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
														addr = data.roadAddress;
													} else { // 사용자가 지번 주소를 선택했을 경우(J)
														addr = data.jibunAddress;
													}

													// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
													if (data.userSelectedType === 'R') {
														// 법정동명이 있을 경우 추가한다. (법정리는 제외)
														// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
														if (data.bname !== ''
																&& /[동|로|가]$/g
																		.test(data.bname)) {
															extraAddr += data.bname;
														}
														// 건물명이 있고, 공동주택일 경우 추가한다.
														if (data.buildingName !== ''
																&& data.apartment === 'Y') {
															extraAddr += (extraAddr !== '' ? ', '
																	+ data.buildingName
																	: data.buildingName);
														}
														// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
														if (extraAddr !== '') {
															extraAddr = ' ('
																	+ extraAddr
																	+ ')';
														}
														// 조합된 참고항목을 해당 필드에 넣는다.
														document
																.getElementById("addrExtra").value = extraAddr;

													} else {
														document
																.getElementById("addrExtra").value = '';
													}

													// 우편번호와 주소 정보를 해당 필드에 넣는다.
													document
															.getElementById('addr1').value = data.zonecode;
													document
															.getElementById("addr2").value = addr;
													// 커서를 상세주소 필드로 이동한다.
													document.getElementById(
															"addrDetail")
															.focus();
												}
											}).open();
								}
							</script>
							<div class="button_div">
								<input type="submit" id="signup" value="등록" class="submit">
								<a href="/main/account"><input type="button" value="취소" class="cancle"></a>
							</div>
						</div>
					</form>

					<div class="body_right">
						<h3>거래처 리스트</h3>
						<table>
							<tr>
								<td>거래처코드</td>
								<td>거래처명</td>
								<td>대표자</td>
								<td>업태</td>
								<td>거래시작일</td>
								<td>관리</td>
							</tr>
							
							<c:forEach items="${companylist}" var="comlist">
								<tr>
									<td>${comlist.companyid}</td>
									<td>${comlist.companyname}</td>
									<td>${comlist.companymanager}</td>
									<td>${comlist.companytype}</td>
									<td>${comlist.startdate}</td>
									<td>
									<input type="button" value="상세" class="modify" onclick="return modify('${comlist.companyid}');">
									<a href="/main/account_delete?companyid=${comlist.companyid}"><input type="button" value="삭제" class="delete" onclick="return delchk();"></a>
									</td>
								</tr>
							</c:forEach>
						</table>
						<div class="page">
							<!-- prev(이전)이 true이면 이전버튼 활성화 -->
							<c:if test="${paging.prev}">
								<a class="pagemove"
									href="/main/account?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${paging.startPage-1}&amount=${paging.cri.amount}"><</a>
							</c:if>
							<!-- begin(1)이 end(10) 될 동안 반복(1이 10 될 동안 반복) -->
							<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="num">

								<c:choose>
									<c:when test="${paging.cri.pageNum==num}">
										<a class="selectpage"
											href="/main/account?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${num}&amount=${paging.cri.amount}">${num}</a>
									</c:when>
									<c:otherwise>
										<a class="pagenum"
											href="/main/account?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${num}&amount=${paging.cri.amount}">${num}</a>
									</c:otherwise>
								</c:choose>

							</c:forEach>

							<!-- next(다음)이 true이면 다음버튼 활성화 -->
							<c:if test="${paging.next}">
								<a class="pagemove"
									href="/main/account?type=${paging.cri.type}&keyword=${paging.cri.keyword}&pageNum=${paging.endPage+1}&amount=${paging.cri.amount}">></a>
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