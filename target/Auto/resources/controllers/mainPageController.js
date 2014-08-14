selectedTab = 0;

$(function() {
	$.ajax({
		url : "/Auto/serverStatus",
		success : function(result) {
			if (result === true) {
				$('#serwerState').text("Serwer Uruchomiony !");
				$('#btnStartServer').css( "background-color", "#585e61" );
				$('#btnStartServer').attr("disabled", true);
			}
		}
	});
	
	fetchInfo();
	$('#btnStopServer').click(function() {
		$.ajax({
			url : "/Auto/stop",
			success : function(result) {
			}
		});
		alert("Zatrzymano serwer");
	});

	$('#btnStartServer').click(function() {
		$.ajax({
			url : "/Auto/start",
			method: 'get',
			success : function(result) {
				console.log(result);
				if (result === false) {
					alert("Serwer jest już uruchomiony");
				}
			}
		});
		$('#serwerState').text("Serwer Uruchomiony !");
		$('#btnStartServer').css( "background-color", "#585e61" );
		$('#btnStartServer').attr("disabled", true);

		
	});

	$('#refresh').click(function() {
		if (selectedTab === 0) {
			fetchInfo();
		} else if (selectedTab === 1) {
			fetchFinanceData();
		} else if (selectedTab === 2) {
			fetchTechnologyData();
		}
	});
	
	$('#cwInfo').click(function() {
		fetchInfo();
	});
	
	$('#cwFinanceData').click(function() {
		fetchFinanceData();
	});

	$('#cwTechnologyData').click(function() {
		fetchTechnologyData();
	});
});

var fetchInfo = function() {
	selectedTab = 0;
	
	headerTr = $('<tr/>');
	$("table").find("tr").remove();
	headerTr.append("<td>ID</td>");
	headerTr.append("<td>Nazwa myjni</td>");
	headerTr.append("<td>Aktywność</td>");
	headerTr.append("<td>Ostatnia aktywność</td>");
	$('table').append(headerTr);
	
	$.getJSON("/Auto/carWashers", function(data) {
		var tr;
		for (var i = 0; i < data.length; i++) {
			tr = $('<tr/>');
			tr.append("<td>" + data[i].id + "</td>");
			tr.append("<td>" + data[i].name + "</td>");
			if (data[i].avaliable === true) {
				tr.append("<td>Aktywna</td>");
				tr.css('background-color', '#0F0');
			} else {
				tr.append("<td>Nieaktywna</td>");
				tr.css('background-color', '#F00');
			}
			tr.append("<td>" + data[i].lastActivity + "</td>");
			$('table').append(tr);
		}
	});
};

var fetchFinanceData = function() {
	selectedTab = 1;
	
	$("table").find("tr").remove();

	headerTr = $('<tr/>');
	headerTr.append("<td>ID</td>");
	headerTr.append("<td>Nazwa myjni</td>");
	headerTr.append("<td>Monety 1 zł</td>");
	headerTr.append("<td>Monety 2 zł</td>");
	headerTr.append("<td>Monety 5 zł</td>");
	headerTr.append("<td>Liczba żetonów</td>");
	$('table').append(headerTr);
	
	$.getJSON("/Auto/carWashers", function(data) {
		var tr;
		for (var i = 0; i < data.length; i++) {
			tr = $('<tr/>');
			tr.append("<td>" + data[i].id + "</td>");
			tr.append("<td>" + data[i].name + "</td>");
			if (data[i].avaliable === true) {
				tr.css('background-color', '#0F0');
			} else {
				tr.css('background-color', '#F00');
			}
			tr.append("<td>" + data[i].onePLNCoins + "</td>");
			tr.append("<td>" + data[i].twoPLNCoins + "</td>");
			tr.append("<td>" + data[i].fivePLNCoins + "</td>");
			tr.append("<td>" + data[i].tokensAmount + "</td>");
			
			$('table').append(tr);
		}
	});
};

var fetchTechnologyData = function() {
	selectedTab = 2;
	
	$("table").find("tr").remove();

	headerTr = $('<tr/>');
	$("table").find("tr:gt(0)").remove();
	headerTr.append("<td>ID</td>");
	headerTr.append("<td>Nazwa myjni</td>");
	$('table').append(headerTr);
	
	$.getJSON("/Auto/carWashers", function(data) {
		var tr;
		for (var i = 0; i < data.length; i++) {
			tr = $('<tr/>');
			tr.append("<td>" + data[i].id + "</td>");
			tr.append("<td>" + data[i].name + "</td>");
			if (data[i].avaliable === true) {
				tr.css('background-color', '#0F0');
			} else {
				tr.css('background-color', '#F00');
			}
			
			$('table').append(tr);
		}
	});
};