$(function() {
	fetchData();
	$('#refresh').click(function() {
		$(document).ready(function() {
			$("table").find("tr:gt(0)").remove();
				fetchData();
		});

	});
});

var fetchData = function() {
	$.getJSON("/Auto/carWashers", function(data) {
		var tr;
		for (var i = 0; i < data.length; i++) {
			tr = $('<tr/>');
			tr.append("<td>" + data[i].id + "</td>");
			if (data[i].avaliable === true) {
				tr.append("<td>Aktywna</td>");
				tr.css('background-color', '#0F0');
			} else {
				tr.append("<td>Nieaktywna</td>");
				tr.css('background-color', '#F00');
			}
			tr.append("<td>" + data[i].tokensAmount + "</td>");
			tr.append("<td>" + data[i].protocolNumber + "</td>");
			tr.append("<td>" + data[i].carWasherNo + "</td>");
			tr.append("<td>" + data[i].statesAmmount + "</td>");
			$('table').append(tr);
		}
	});
};
