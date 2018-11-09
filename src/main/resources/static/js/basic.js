function right()
{
    var input = $("#inputText").val();
    var additional = $("#additionalText").val();

    $.ajax({
        url: "http://localhost:8080/api",
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            data: "right "+ input+" "+additional
        })
    }).then(function (data, status, jqxhr) {
        $("#outputText").val('Response: ' + data);
    });
}

function address()
{
    var input = $("#inputText").val();

    $.ajax({
        url: "http://localhost:8080/api",
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            data: "address "+ input
        })
    }).then(function (data, status, jqxhr) {
        $("#outputText").val('Response: ' + data);
    });
}

function left()
{
    var input = $("#inputText").val();
    var additional = $("#additionalText").val();

    $.ajax({
        url: "http://localhost:8080/api",
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            data: "left "+ input +" "+additional
        })
    }).then(function (data, status, jqxhr) {
        $("#outputText").val('Response: ' + data);
    });
}

function up()
{
    var input = $("#inputText").val();

    $.ajax({
        url: "http://localhost:8080/api",
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            data: "up "+ input
        })
    }).then(function (data, status, jqxhr) {
        $("#outputText").val('Response: ' + data);
    });
}

function down()
{
    var input = $("#inputText").val();

    $.ajax({
        url: "http://localhost:8080/api",
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            data: "down "+ input
        })
    }).then(function (data, status, jqxhr) {
        $("#outputText").val('Response: ' + data);
    });
}

function zoomin()
{
    var input = $("#inputText").val();

    $.ajax({
        url: "http://localhost:8080/api",
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            data: "zoomin "+ input
        })
    }).then(function (data, status, jqxhr) {
        $("#outputText").val('Response: ' + data);
    });
}

function zoomout()
{
    var input = $("#inputText").val();

    $.ajax({
        url: "http://localhost:8080/api",
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            data: "zoomout "+ input
        })
    }).then(function (data, status, jqxhr) {
        $("#outputText").val('Response: ' + data);
    });
}

function home()
{
    var input = $("#inputText").val();

    $.ajax({
        url: "http://localhost:8080/api",
        datatype: 'json',
        type: "post",
        contentType: "application/json",
        data: JSON.stringify({
            data: "home "+ input
        })
    }).then(function (data, status, jqxhr) {
        $("#outputText").val('Response: ' + data);
    });
}