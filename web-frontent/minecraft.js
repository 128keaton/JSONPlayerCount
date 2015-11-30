var rq = 'http://128keaton.com/applecider/decode.php';
var error = 'unknown';
var banana = 'http://128keaton.com/applecider/players.php';
var classes = {
	error: "fa-spinner",
	false: "fa-spinner",
	true: "fa-check green",
};
var allclasses = "";
var isOnline = "";
for (i in classes) {
	allclasses += ' ' + classes[i];
};

function setclass(o, c) {
	o.removeClass(allclasses);
	o.addClass(classes[c]);
	o.html('');
}

function settext(o, t) {
	o.removeClass(allclasses);
	o.html(t);
}

function display(data) {
	var np = $('#numplayers'),
		version = $('#version'),
		online = $('#online'),
		motd = $('#motd'),
		updated = $('#updated'),
		players = $('#players');
	data.online = data.status === 'success';
	qbert('play.128keaton.com');
}

function qbert(addr) {
	$.ajax({
		url: banana,
		type: 'GET',
		dataType: 'json',
	}).done(function(data) {
		console.log("success");
		console.log(data);
		display_players(data);
		$('#backend').removeClass("fa fa-spinner fa-spin grey").addClass("fa fa-check green");
	}).fail(function(data) {
		console.log("error");
		console.log(data);
		$('#online').removeClass("fa fa-question fa-spin").addClass("fa fa-times red");
		$('#backend').removeClass("fa fa-spinner fa-spin grey").addClass("fa fa-times red");
	}).always(function() {});
}

function newHealth(health) {
	var healthNew = 0;
	if (health == 1) {
		healthNew = .5;
	} else if (health == 2) {
		healthNew = 1;
	} else if (health == 3) {
		healthNew = 1.5;
	} else if (health == 4) {
		healthNew = 2;
	} else if (health == 5) {
		healthNew = 2.5;
	} else if (health == 6) {
		healthNew = 3;
	} else if (health == 7) {
		healthNew = 3.5;
	} else if (health == 8) {
		healthNew = 4;
	} else if (health == 9) {
		healthNew = 4.5;
	} else if (health == 10) {
		healthNew = 5;
	} else if (health == 11) {
		healthNew = 5.5;
	} else if (health == 12) {
		healthNew = 6;
	} else if (health == 13) {
		healthNew = 6.5;
	} else if (health == 14) {
		healthNew = 7;
	} else if (health == 15) {
		healthNew = 7.5;
	} else if (health == 16) {
		healthNew = 8;
	} else if (health == 17) {
		healthNew = 8.5;
	} else if (health == 18) {
		healthNew = 9;
	} else if (health == 19) {
		healthNew = 9.5;
	} else if (health == 20) {
		healthNew = 10;
	} else console.log("Player health: " + healthNew);
	return healthNew;
}

function display_players(data) {
	var np = $('#numplayers'),
		version = $('#version'),
		online = $('#online'),
		motd = $('#motd'),
		updated = $('#updated'),
		players = $('#players');
	data.online = data.status === 'success';
	settext(updated, data.last_update);
	setclass(online, data.online);
	online.removeClass("fa fa-question grey fa-spin").addClass("fa fa-check green");
	online.removeClass("fa fa-spinner green fa-spin").addClass("fa fa-check green");
	players.removeClass("fa-spin");
	settext(updated, data.last_updated);
	settext(players, data.players.toString());
	if (data.players > 0) {
		for (var i = 0; i < data.list.length; i++) {
			var obj = data.list[i];
			console.log(obj.name);
			$("#list").removeClass("fa fa-spinner grey");
			document.getElementById("list").innerHTML += '<p class="lead" style="font-style: normal;" id="player-text"><img class="avatar" src="https://minotar.net/helm/' + obj.name + '/25.png">' + obj.name + '</p><p class="health">Health: ' + newHealth(obj.health) + '/10</p>';
			$("#list").removeClass("fa fa-spinner grey");
		}
	} else {
		$("#list").removeClass("fa fa-spinner fa-spin grey").addClass("fa fa-times red");
	}
}
$(document).ready(function() {
	qbert('play.128keaton.com', display_players);
});