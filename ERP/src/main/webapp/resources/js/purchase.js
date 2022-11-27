$(document).on("change","#selectBox1",function() {

			$.ajax({
				type : 'get',
				url : "/main/company_select",
				data : {
					"companyname" : $("#selectBox1 option:selected").val()
				},
				dataType : "json",
				success : function(data) {

					$('#productname').empty();

					$('#productname').append(
							$("<option>" + '::상품을 선택하세요::' + "</option>"));

					for (var count = 0; count < data.length; count++) {
						var option = $("<option>" + data[count].productname
								+ "</option>");
						$('#productname').append(option);
					}
				} // success

			});

		});

$(document).on("change", "#productname", function() {

	$.ajax({
		type : 'get',
		url : "/main/product_select",
		data : {
			"companyname" : $("#selectBox1 option:selected").val(),
			"productname" : $("#productname option:selected").val()
		},
		dataType : "json",
		success : function(data) {

			$('#productcnt').empty();
			$('#price').empty();
			$('#productcount').empty();
			$('#totalprice').empty();

			$("#productcnt").val(data.productcnt);
			$("#price").val(data.price);
			$("#productcount").val(data.productcount);
			$("#totalprice").val(data.totalprice);
			$("#productid").val(data.productid);

		} // success

	});
});

$(".submit").on("click", function() {

	$.ajax({
		type : 'POST',
		url : '/cart/add',
		data : "json",
		success : function(result) {
			cartAlert(result);
		}
	})

	$.ajax({
		type : 'get',
		url : '/cart/add',
		data : "json",
		success : function(result) {
			cartAlert(result);
		}
	})
});

function cartAlert(result) {
	if (result == '0') {
		alert("장바구니에 추가를 하지 못하였습니다.");
	} else if (result == '1') {
		alert("장바구니에 추가되었습니다.");
	} else if (result == '2') {
		alert("장바구니에 이미 추가되어져 있습니다.");
	} else if (result == '5') {
		alert("로그인이 필요합니다.");
	}
}

function modify(cartid) {
	
	$.ajax({
		type : 'get',
		url : "/cart/modify",
		data : {
			"cartid" : cartid
		},
		dataType : "json",
		success : function(data) {
			$("#productcount").prop("readonly",false);
			
			$("#cartid").val(data.cartid);
			$("#productcnt").val(data.productcnt);
			$("#price").val(data.price);
			$("#productcount").val(data.productcount);
			$("#totalprice").val(data.productcount*data.price);
			$("#productid").val(data.productid);

			// 등록 버튼을 수정버튼으로
			$("#signup").attr("value", "수정");
			// 수정 버튼 누르면 form 주소 변경
			$("#form").attr("action", "/cart/modifypost");
			$("#signup").attr("type", "submit");

			$("#selectBox1").val(data.companyname).prop("selected", true);
			
		} // success
	});
}

function autoCal(){
    var total = 0;
    total = $('#productcount').val()*$('#price').val();
    $('#totalprice').val(total);
}

function jsSubmit(){
	var companay = $('#selectBox1').val();
	var product = $('#productname').val();
	var count =$('#productcount').val();
	
	if (companay == '') {
		alert("업체를 선택해주세요.");
		return false;
	}
	;
	
	if (product == '::상품을 선택하세요::') {
		alert("상품을 선택해주세요.");
		return false;
	}
	;
	
	if (count == '') {
		alert("구매 수량을 입력해주세요.");
		return false;
	}
	;
	
	alert("등록이 완료 되었습니다.");
	return true;
	;

}

function confirm(){
	return confirm("승인하시겠습니까?");
}