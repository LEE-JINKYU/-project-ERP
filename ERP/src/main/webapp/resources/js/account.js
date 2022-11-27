window.addEventListener('load', function() {
	var signup = $("#signup");
	signup.attr("type", "button");
	signup[0].addEventListener('click', function() {
		if (signup.attr('type') == 'button') {
			alert("거래처코드 중복확인을 해주세요.");
		}
	});
});

function idCheck() {
	var idreg = /^[a-zA-Z][0-9a-z-_]{4,19}$/g;
	var companyid = $("#idchk").val();
	if (!idreg.test(companyid)) {
		alert("형식에 맞게 거래처코드를 입력해주세요.")
		return false;
	}
	$.ajax({
		type : 'post',
		url : "/main/companyidcheck",
		data : {
			"companyid" : companyid
		},
		dataType : "json",
		success : function(data) {
			if (data == 1) {
				alert("중복된 코드 입니다.");
			} else {
				alert("사용가능한 코드 입니다.");
				$("#signup").attr("type", "submit");
			}
		}
	});
}

function jsSubmit() {

	var companyname = $("#companyname").val();
	var companymanager = $("#companymanager").val();
	var companytype = $("#companytype").val();
	var numchk1 = $("#numchk1").val();
	var numchk2 = $("#numchk2").val();
	var addrDetail = $("#addrDetail").val();
	var hdchk = $("#hdchk").val();

	if (companyname == '') {
		alert("업체명을 입력해주세요.");
		return false;
	}
	;
	if (companymanager == '') {
		alert("대표자명을 입력해주세요.");
		return false;
	}
	;
	if (companytype == '') {
		alert("업태를 입력해주세요.");
		return false;
	}
	;
	if (numchk1 == '') {
		alert("전화번호 입력해주세요.");
		return false;
	}
	;
	if (numchk2 == '') {
		alert("전화번호 입력해주세요.");
		return false;
	}
	;
	if (addrDetail == '') {
		alert("상세주소를 입력해주세요.");
		return false;
	}
	;
	if (hdchk == '') {
		alert("거래시작일을 입력해주세요.");
		return false;
	}
	;
	alert("등록이 완료 되었습니다.");
	return true;
	;
}

function modify(companyid) {

	$.ajax({
		type : 'get',
		url : "/main/account_modify",
		data : {
			"companyid" : companyid
		},
		dataType : "json",
		success : function(data) {
			$("#idchk").val(data.companyid);
			$("#companyname").val(data.companyname);
			$("#companymanager").val(data.companymanager);
			$("#companytype").val(data.companytype);
			$("#hdchk").val(data.startdate);

			// 주소 쪼개서 각각 넣기
			var Addr = data.companyaddr;
			var afterAddr = Addr.split("/");
			$('#addr1').val(afterAddr[0]);
			$('#addr2').val(afterAddr[1]);
			$('#addrDetail').val(afterAddr[2]);
			$('#addrExtra').val(afterAddr[3]);

			// 휴대폰 번호 쪼개서 각각 넣기
			var Phone = data.companyphone;
			var afterPhone = Phone.split("-");
			$('#numchk1').val(afterPhone[1]);
			$('#numchk2').val(afterPhone[2]);

			// 등록 버튼을 수정버튼으로
			$("#signup").attr("value", "수정");
			// 수정 버튼 누르면 form 주소 변경
			$("#form").attr("action", "/main/account_modifypost");
			$("#signup").attr("type", "submit");

		} // success
	});
}