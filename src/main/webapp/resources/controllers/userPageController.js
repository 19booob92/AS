selectedTab = 0;

$(function() {
	$('#addUserFormDiv').hide();
	fetchUserInfo();
	$('#fetchUsers').click(function() {
		fetchUserInfo();
	});

	$('#fetchFVs').click(function() {
		fetchFVs();
	});

	$('#fetchCards').click(function() {
		fetchCards();
	});

	$('#submitUser').click(function() {

		$.post('/Auto/user/add', $("#addUserForm").serializeArray(), function(data,
                textStatus, jqXHR) {
			$('#tableDiv').show("fast");
			$('#tableDiv').hide("fast");
        });
		
    });
	
	$('#newUser').click(function(){
		$('#addUserFormDiv').show("fast");
		$('#tableDiv').hide("fast");
	});
});

var fetchUserInfo = function() {
	selectedTab = 0;

	headerTr = $('<tr/>');
	$("table").find("tr").remove();
	headerTr.append("<td>ID</td>");
	headerTr.append("<td>Imie</td>");
	headerTr.append("<td>Nazwisko</td>");
	headerTr.append("<td>Karty</td>");
	$('table').append(headerTr);

	$.getJSON("/Auto/user/list", function(data) {
		var tr;
		for (var i = 0; i < data.length; i++) {
			tr = $('<tr/>');
			tr.append("<td>" + data[i].id + "</td>");
			tr.append("<td>" + data[i].imie + "</td>");
			tr.append("<td>" + data[i].nazwisko + "</td>");
			for (j = 0; j < data[i].cards.length; j++) {
				tr.append("<td>" + data[i].cards[j].id + "  "
						+ data[i].cards[j].aktywna + "</td>");
			}
			$('table').append(tr);
		}
	});
};

var fetchFVs = function() {
	selectedTab = 1;

	headerTr = $('<tr/>');
	$("table").find("tr").remove();
	headerTr.append("<td>ID</td>");
	headerTr.append("<td>numer sekwencyjny</td>");
	headerTr.append("<td>id klienta</td>");
	$('table').append(headerTr);

	$.getJSON("/Auto/fv/list", function(data) {
		var tr;
		for (var i = 0; i < data.length; i++) {
			tr = $('<tr/>');
			tr.append("<td>" + data[i].id + "</td>");
			tr.append("<td>" + data[i].seqNo + "</td>");
			tr.append("<td>" + data[i].client + "</td>");
			for (j = 0; j < data[i].slots.length; j++) {
				tr.append("<td>" + data[i].slots[j].id + " usługa"
						+ data[i].slots[j].serviceId + "</td>");
			}
			$('table').append(tr);
		}
	});
};

var fetchCards = function() {
	selectedTab = 2;

	headerTr = $('<tr/>');
	$("table").find("tr").remove();
	headerTr.append("<td>ID</td>");
	headerTr.append("<td>Imie</td>");
	headerTr.append("<td>Nazwisko</td>");
	headerTr.append("<td>Karty</td>");
	$('table').append(headerTr);

	$.getJSON("/Auto/user/list", function(data) {
		var tr;
		for (var i = 0; i < data.length; i++) {
			tr = $('<tr/>');
			tr.append("<td>" + data[i].id + "</td>");
			tr.append("<td>" + data[i].imie + "</td>");
			tr.append("<td>" + data[i].nazwisko + "</td>");
			for (j = 0; j < data[i].cards.length; j++) {
				tr.append("<td>" + data[i].cards[j].id + "  "
						+ data[i].cards[j].aktywna + "</td>");
			}
			$('table').append(tr);
		}
	});
};