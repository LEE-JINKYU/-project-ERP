window.addEventListener('load', function() {
	var signup = $("#signup");
	signup.attr("type", "button");
	signup[0].addEventListener('click', function() {
		if (signup.attr('type') == 'button') {
			alert("아이디 중복확인 해주세요.");
		}
	});
});

function idCheck() {
	var idreg = /^[a-zA-Z][0-9a-z-_]{4,19}$/g;
	var empno = $("#idchk").val();
	if (!idreg.test(empno)) {
		alert("형식에 맞게 아이디를 입력해주세요.")
		return false;
	}
	$.ajax({
		type : 'post',
		url : "/main/idcheck",
		data : {
			"empno" : empno
		},
		dataType : "json",
		success : function(data) {
			if (data == 1) {
				alert("중복된 아이디 입니다.");
			} else {
				alert("사용가능한 아이디 입니다.");
				$("#signup").attr("type", "submit");
			}
		}
	});
}

function modify(empno) {

	$.ajax({
		type : 'get',
		url : "/main/modify",
		data : {
			"empno" : empno
		},
		dataType : "json",
		success : function(data) {
			$("#idchk").val(data.empno);
			$("#pwchk").val(data.password);
			$("#name").val(data.name);
			$("#bdchk").val(data.birth);
			$("#ranking").val(data.ranking);
			$("#hdchk").val(data.hiredate);

			// 주소 쪼개서 각각 넣기
			var Addr = data.addr;
			var afterAddr = Addr.split("/");
			$('#addr1').val(afterAddr[0]);
			$('#addr2').val(afterAddr[1]);
			$('#addrDetail').val(afterAddr[2]);
			$('#addrExtra').val(afterAddr[3]);

			// 휴대폰 번호 쪼개서 각각 넣기
			var Phone = data.phone;
			var afterPhone = Phone.split("-");
			$('#numchk1').val(afterPhone[1]);
			$('#numchk2').val(afterPhone[2]);

			// 등록 버튼을 수정버튼으로
			$("#signup").attr("value", "수정");
			// 수정 버튼 누르면 form 주소 변경
			$("#form").attr("action", "/main/modifypost");
			$("#signup").attr("type", "submit");

			if (data.gender == '남자') {
				$('input:radio[id="women"]').attr("checked", false);
				$('input:radio[id="men"]').attr("checked", true);

			}
			if (data.gender == '여자') {
				$('input:radio[id="men"]').attr("checked", false);
				$('input:radio[id="women"]').attr("checked", true);
			}

			if (data.ranking == '사장') {
				$("select[name=ranking]").val("사장").prop("selected", true);
			}
			if (data.ranking == '부장') {
				$("select[name=ranking]").val("부장").prop("selected", true);
			}
			if (data.ranking == '차장') {
				$("select[name=ranking]").val("차장").prop("selected", true);
			}
			if (data.ranking == '과장') {
				$("select[name=ranking]").val("과장").prop("selected", true);
			}
			if (data.ranking == '대리') {
				$("select[name=ranking]").val("대리").prop("selected", true);
			}
			if (data.ranking == '사원') {
				$("select[name=ranking]").val("사원").prop("selected", true);
			}
		} // success
	});
}

function jsSubmit() {
	var idreg = /^[a-z][0-9a-z-_]{4,19}$/g;
	var pwreg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
	var numreg = /^(?=.*[0-9]).{7,8}$/;
	var emreg = /^[A-Za-z0-9_\.\-]/;

	var idValue = $("#idchk").val();
	var pwValue = $("#pwchk").val();
	var numV1 = $("#numchk1").val();
	var numV2 = $("#numchk2").val();
	var numValue = numV1.concat(numV2);
	var a = $("#bdchk").val();
	var b = a.replace(/-/g, '');
	var bdValue = b.replace(/-/g, '');
	var nickValue = $("#nickname").val();
	var addrValue1 = $("#addr1").val();
	var addrValue2 = $("#addrDetail").val();
	var nameValue = $("#name").val();
	var emailValue = $("#email").val();

	if (nameValue == '') {
		alert("이름를 입력해주세요.");
		return false;
	}
	;
	if (!idreg.test(idValue)) {
		alert("아이디를 중복확인 해주세요.");
		return false;
	}
	;
	if (!pwreg.test(pwValue)) {
		alert("비밀번호를 알맞게 입력해주세요.");
		return false;
	}
	;
	if (!emreg.test(emailValue)) {
		alert("이메일을 알맞게 입력해주세요.");
		return false;
	}
	;
	if (!numreg.test(numValue)) {
		alert("전화번호를 알맞게 입력해주세요.");
		return false;
	}
	;
	if (bdValue == '') {
		alert("생년월일를 알맞게 입력해주세요.");
		return false;
	}
	;
	if (nickValue == '') {
		alert("닉네임를 알맞게 입력해주세요.");
		return false;
	}
	;
	if (addrValue1 == '') {
		alert("주소를 입력해주세요.");
		return false;
	}
	;
	if (addrValue2 == '') {
		alert("자세한 주소를 입력해주세요.");
		return false;
	}
	;
	alert("가입이 완료 되었습니다.");
	return true;
	;
};

