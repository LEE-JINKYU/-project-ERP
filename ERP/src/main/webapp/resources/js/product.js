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
	var productid = $("#idchk").val();
	if (!idreg.test(productid)) {
		alert("형식에 맞게 아이디를 입력해주세요.")
		return false;
	}
	$.ajax({
		type : 'post',
		url : "/main/productidcheck",
		data : {
			"productid" : productid
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

	var productname = $("#productname").val();
	var price = $("#price").val();
	var productcnt = $("#productcnt").val();

	if (productname == '') {
		alert("상품명을 입력해주세요.");
		return false;
	}
	;
	if (price == '') {
		alert("상품 가격을 입력해주세요.");
		return false;
	}
	;
	if (productcnt == '') {
		alert("상품 설명을 입력해주세요.");
		return false;
	}
	;

	alert("등록이 완료 되었습니다.");
	return true;
	;
}

function modify(productid) {
	
	$.ajax({
		type : 'get',
		url : "/main/product_modify",
		data : {
			"productid" : productid
		},
		dataType : "json",
		success : function(data) {
			$("#idchk").val(data.productid);
			$("#productname").val(data.productname);
			$("#price").val(data.price);
			$("#productcnt").val(data.productcnt);


			// 등록 버튼을 수정버튼으로
			$("#signup").attr("value", "수정");
			// 수정 버튼 누르면 form 주소 변경
			$("#form").attr("action", "/main/product_modifypost");
			$("#signup").attr("type", "submit");

			$("#selectBox").val(data.companyname).prop("selected", true);

		} // success
	});
}

$(document).on("change", "#selectBox", function() {

	$.ajax({
		type : 'get',
		url : "/main/companyid_select",
		data : {
			"companyname" : $("#selectBox option:selected").val(),
		},
		dataType : "json",
		success : function(data) {

			$("#companyid").val(data.companyid);


		} // success

	});
});