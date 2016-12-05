var http = require('http');
var express = require('express');
var app = express();
var port = 3000;
var httpServer = http.createServer(app).listen(port, function (req, res) {

});

var io = require('socket.io').listen(httpServer);

var mysql = require("mysql");
var connection = mysql.createConnection({
    host: "opensw.iptime.org:3300",
    port: 3306,
    user: "root",
    password: "qwe505",
    database: "exms"
});

connection.connect();

io.on('connection', function (socket) {

    socket.on('GetDataList', function (data) {
        var sqlQuery = "SELECT * FROM barcode;";
        connection.query(sqlQuery, function (err, result) {
            if (err == null) {
                console.log(result);
                socket.emit('GetDataListRes', result);
            } else {

            }
        });
    });

    socket.on('InsertData', function (data) {
        console.log(data);
        var json = JSON.parse(data);
        console.log(json);

        var sqlQuery = "INSERT INTO barcode SET ?";
        var post = {
            code: json.code,
            name: json.name,
            exp: json.exp
        };

        connection.query(sqlQuery, post, function (err, result) {
            if (err == null) {
                console.log(err);
            } else {
                console.log(result);
            }

        });
    });
});