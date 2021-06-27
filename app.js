const express = require('express');
const app = express();

var fs = require('fs');
var path = require('path');

// 中间件
const middlewares = require("./server/middlewares");
middlewares(app);

// 端口
const port = normalizePort(process.env.PORT || '3000');
function normalizePort(val) {
    var port = parseInt(val, 10);

    if (isNaN(port)) {
        // named pipe
        return val;
    }

    if (port >= 0) {
        // port number
        return port;
    }

    return false;
}

// server监听和app监听二选一
// app监听
// app.listen(port, () => console.log(`NodeJS Web Server starts at http://127.0.0.1:${port} ...`));

// 使用证书
let httpsServer;
try{
    var privateCrt = fs.readFileSync(path.join(process.cwd(), 'https/1_www.kakayang.cn_bundle.crt'), 'utf8');
    var privateKey = fs.readFileSync(path.join(process.cwd(), 'https/2_www.kakayang.cn.key'), 'utf8');
    const HTTPS_OPTOIN = {
    key: privateKey,
    cert: privateCrt
    };
    const https = require('https');
    httpsServer = https.createServer(HTTPS_OPTOIN, app);
}catch(err){
    const http = require('http');
    httpsServer = http.createServer(app);
}

httpsServer.listen(port);

// server监听
httpsServer.on('error', onError);
httpsServer.on('listening', onListening);

function onError(error) {
    if (error.syscall !== 'listen') {
        throw error;
    }
    var bind = typeof port === 'string' ? 'Pipe ' + port : 'Port ' + port;

    // handle specific listen errors with friendly messages
    switch (error.code) {
        case 'EACCES':
            console.error(bind + ' requires elevated privileges');
            process.exit(1);
            break;
        case 'EADDRINUSE':
            console.error(bind + ' is already in use');
            process.exit(1);
            break;
        default:
            throw error;
    }
}
function onListening() {
    var addr = httpsServer.address();
    var bind = typeof addr === 'string' ? 'pipe ' + addr : `NodeJS Web Server starts at http://127.0.0.1:${addr.port} ...`;
    console.log(bind);
}