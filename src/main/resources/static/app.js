var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({"userId": $("#id").val()}, function (frame) { //客户端ID
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/subscription/'+$("#id").val(), function (greeting) { //表明客户端地址
            alert(greeting.body);
            showGreeting(greeting.body);
        }, {"id": "Host_" + $("#id").val()});
    });
}

function disconnect() {
    alert(stompClient!==null);
    if (stompClient !== null) {
        console.log("yep")
        stompClient.send("/socket/disconnection", {"userId": $("#id").val()},JSON.stringify({"userId": $("#id").val()}));
        stompClient.disconnect();
    }
    setConnected(true);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/socket/chat", {}, JSON.stringify({
        'content': $("#content").val(),
        'senderId': $("#id").val(),
        'recipientId': $("#pid").val()
    }));
    //showGreeting("my:" + $("#content").val())
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
});