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

function get_json(url, callback) {
    http.get(url, function(res) {
        var body = '';
        res.on('data', function(chunk) {
            body += chunk;
        });
        res.on('end', function() {
            var response = JSON.parse(body);
            // call function ----v
            callback(response);
        });
    });
}

function q(addr, cb) {
    $.ajax({
        url: rq,
        type: 'GET',
        dataType: 'json',
    }).done(function(data) {
        console.log("success");
        console.log(data);
        $('#backend').removeClass("fa fa-spinner fa-spin grey").addClass("fa fa-check green");
        cb(data);
    }).fail(function(data) {
        console.log("error");
        $('#online').removeClass("fa fa-question fa-spin").addClass("fa fa-times red");
        $('#backend').removeClass("fa fa-spinner fa-spin grey").addClass("fa fa-times red");
    }).always(function() {});
}

function setclass(o, c) {
    o.removeClass(allclasses);
    o.addClass(classes[c]);
    o.html('');
}

function settext(o, t) {
    o.removeClass(allclasses);
    o.html(t);
}

function setimg(o, t) {
    o.attr("src", "https://minotar.net/helm/" + t + "/25.png");
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

function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds) {
            break;
        }
    }
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
    }).fail(function(data) {
        console.log("error");
        console.log(data);
    }).always(function() {});
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
            document.getElementById("list").innerHTML += '<p class="lead" style="font-style: normal;" id="player-text"><img class="avatar" src="https://minotar.net/helm/' + obj.name + '/25.png">' + obj.name + '</p>';
            $("#list").removeClass("fa fa-spinner grey");
        }
    } else {
       $("#list").removeClass("fa fa-spinner fa-spin grey").addClass("fa fa-times red"); 
    }

}

$(document).ready(function() {
    q('play.128keaton.com', display);
});

function drawTable(data) {
    for (var i = 0; i < data.length; i++) {
        drawRow(data[i]);
    }
}

function drawRow(rowData) {
    var row = $("<tr/>")
    $("#personDataTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
    row.append($("<td>" + rowData.list + "</td>"));
}