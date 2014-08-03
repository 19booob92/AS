$(function() {
	$('#btn').click(function() {
		$(document).ready(function () {
			$("table").find("tr:gt(0)").remove();
		    $.getJSON("/Auto/carWashers",
		    function (data) {
		        var tr;
		        for (var i = 0; i < data.length; i++) {
		            tr = $('<tr/>');
		            tr.append("<td>" + data[i].id+ "</td>");
		            tr.append("<td>" + data[i].name + "</td>");
		            if (data[i].avaliable === true) {
		            	tr.append("<td>Aktywna</td>");
		            	tr.css('background-color','#0F0');
		            } else {
		            	tr.append("<td>Nieaktywna</td>");
		            	tr.css('background-color','#F00');
		            }
		            $('table').append(tr);
		        }
		    });
		});
		
	});
});
