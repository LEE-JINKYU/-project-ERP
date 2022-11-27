<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<!-- Jquery 사용 -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- main CSS 적용 -->
<link rel="stylesheet"
	href="../../../resources/css/mypage.css?version=1.0">
<!-- main JS 적용 -->
<script type="text/javascript"
	src="../../../resources/js/mypage.js?version=1.0"></script>
</head>
<body>
	<div class="container">
		<div class="main">
			<div class="main_line">

				<div class="empinfo_body">
					<form action="/main/mypagemodify" method="post"
						onsubmit="return jsSubmit();" id="form">
						<div class="body_left">
							<h3>내 정보</h3>
							<table>
								<tr>
									<td>사원번호</td>
									<td><input type="text" id="idchk" name="empno"
										maxlength="20" class="box" readonly value="${list.empno}">
										<span id="idmsg" class="msg"></span></td>
								</tr>
								<tr>
									<td>비밀번호</td>
									<td><input type="password" id="pwchk" name="password"
										maxlength="20" class="box" value=""> <span
										id="pwmsg" class="msg"></span></td>
								</tr>
								<tr>
									<td>이름</td>
									<td><input type="text" id="name" name="name"
										maxlength="10" class="box" value="${list.name}" readonly></td>
								</tr>
								<tr>
									<td>성별</td>
									<td><input type="text" name="gender" id="men" class="box"
										value="${list.gender}" readonly></td>
								</tr>
								<tr>
									<td>생년월일</td>
									<td><input type="date" name="birth" id="bdchk" class="box"
										readonly value="${list.birth}"></td>
								</tr>
								<tr>
									<td>직급</td>
									<td><input type="text" name="ranking" class="box" readonly
										value="${list.ranking}"></td>
								</tr>
								<tr>
									<td>전화번호</td>
									<td><input type="text" class="box" name="phone" readonly value="${list.phone}"></td>
								</tr>
								<tr>
									<td>주소</td>
									<td><input type="text" name="addr" id="addr1"
										placeholder="우편번호" class="box">
										<input type="button"
										id="addrButton" onclick="sample6_execDaumPostcode()"
										value="우편번호" class="check_box"><br>
										 <input
										type="text" name="addr" id="addr2" placeholder="주소"
										class="box"><br> 
										<input type="text" name="addr"
										id="addrDetail" placeholder="상세주소" class="box"> 
										<input
										type="text" name="addr" id="addrExtra" placeholder="참고항목"
										class="box"></td>
								</tr>
								<tr>
									<td>입사일자</td>
									<td><input type="text" name="hiredate" id="hdchk"
										class="box" readonly value="${list.hiredate}"></td>
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
								<input type="button" id="signup" value="수정" class="submit" onclick="return modify('${list.empno}');">
								<input type="button" value="닫기"
									class="cancle" onClick='window.close()'>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- main_line -->
		</div>
		<!-- main -->
	</div>
	<!-- container -->
</body>
</html>