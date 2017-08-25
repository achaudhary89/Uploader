var min, sec;

function Minutes(data) {
	for (var i = 0; i < data.length; i++)
		if (data.substring(i, i + 1) == ":")
			break;
	return (data.substring(0, i));
}

function Seconds(data) {
	for (var i = 0; i < data.length; i++)
		if (data.substring(i, i + 1) == ":")
			break;
	return (data.substring(i + 1, data.length));
}

function Down() {
	sec--;
	if (sec == -1) {
		sec = 59;
		min--;
	}
	/*
	 * if(min==3 && sec == 0){ var d = new Date(); var hours = d.getHours(); var
	 * minutes = d.getMinutes(); if(minutes<10){ br=""> minutes = "0" +
	 * minutes; } alert('Message from Application at '+hours+':'+minutes+' -
	 * nnYou'); }
	 */
	if (min == 0 && sec == 0) {
		alert("Your session has timed out");
		var host = document.URL;
		var res = host.split("/");
		var newhost = "";
		for (i = 0; i < res.length - 1; i++) {
			newhost = newhost + res[i] + "/";
		}
		newhost = newhost + "displayForm.htm";
		document.location.href = newhost;
	} else {
		down = setTimeout("Down()", 1000);
	}
}

function timeIt() {
	var time = "15:00";
	min = 1 * Minutes(time);
	sec = 0 + Seconds(time);
	Down();
}

timeIt();