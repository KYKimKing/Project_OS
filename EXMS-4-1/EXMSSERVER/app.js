var http = require('http');
var express = require('express');
var app = express();
var port = 3000;
var httpServer = http.createServer(app).listen(port, function (req, res) {

});

var io = require('socket.io').listen(httpServer);

var mysql = require("mysql");
var connection = mysql.createConnection({
    host: "192.168.0.15",
    port: 3306,
    user: "root",
    password: "",
    database: "exms"
});

connection.connect();

io.on('connection', function (socket) {

    socket.on('GetDataList', function (data) {
        var sqlQuery = "SELECT * FROM data;";
        connection.query(sqlQuery, function (err, result) {
            if (err == null) {
                console.log(result);
                socket.emit('GetDataListRes', result);
            } else {

            }
        });
    });

    socket.on('InsertData', function (data){
    		 var json = data.JSON.parse(data);
    		 var sqlQuery = "Insert * FROM data (json.code,json.name,json.exp);";
    		 connection.query(sqlQuery, function(err,result){

    		 });
    });
});