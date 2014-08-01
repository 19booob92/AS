$(function() {
	$('#btn').click(function() {
		$.ajax({
			url : '127.0.0.1:8080/server/carWashers',
			type : 'get',
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				alert("success " + data + textStatus + jqXHR);
				console.log(data.error);
			}
		});
	});
});
