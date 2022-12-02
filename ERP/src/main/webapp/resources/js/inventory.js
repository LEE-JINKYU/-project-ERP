$(document).on("change","#selectBox1",function() {

			$.ajax({
				type : 'get',
				url : "/main/inventory_select1",
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
					
					var html = '';
					for (key in data) {
						html += '<tr>';
						html += '<td>' + data[key].productid + '</td>';
						html += '<td>' + data[key].companyname + '</td>';
						html += '<td>' + data[key].productname + '</td>';
						html += '<td>' + data[key].price + '</td>';
						html += '<td>' + data[key].sumcount + '</td>';
						html += '</tr>';
					}

					$("#resultTbody").empty();
					$("#resultTbody").append(html);
				} // success
			});
})

$(document).on("change", "#productname", function() {

	$.ajax({
		type : 'get',
		url : "/main/inventory_select",
		data : {
			"companyname" : $("#selectBox1 option:selected").val(),
			"productname" : $("#productname option:selected").val()
		},
		dataType : "json",
		success : function(data) {

			var html = '';
			for (key in data) {
				html += '<tr>';
				html += '<td>' + data[0].productid + '</td>';
				html += '<td>' + data[0].companyname + '</td>';
				html += '<td>' + data[0].productname + '</td>';
				html += '<td>' + data[0].price + '</td>';
				html += '<td>' + data[0].sumcount + '</td>';
				html += '</tr>';
			}

			$("#resultTbody").empty();
			$("#resultTbody").append(html);
		} // success
	});
});


