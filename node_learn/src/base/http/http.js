const http = require('http');
const fs = require('fs');
const path = require('path');
http.createServer((req, res) => {
    console.log(req.url)  //拿到用户请求的路径
    // /favicon.ico
    if (req.url === '/login') {
        console.log('进入登陆')  //拿到用户请求的路径
        // res.setHeader('Content-Type','text/plain;charset=utf-8');//纯文本    text/html
        var filePath = path.join(__dirname, 'login.html');
        console.log(filePath)  //拿到用户请求的路径
        fs.readFile(filePath, (err, data) => {
            console.log(data)
            if (err) throw err;
            res.end(data);
        })
    } else if (req.url.includes('.css')) {
        console.log(req.url);
        fs.readFile(path.join(__dirname, req.url), (err, data) => {
            console.log(data)
            if (err) throw err;
            res.end(data);
        })

    } else {
        res.setHeader('Content-Type', 'text/plain;charset=utf-8');//纯文本    text/html
        res.write('helo world! 你好 世界  ');
        //有可能乱码
        res.end();
    }


}).listen('8081', () => {
    console.log('服务启动在 http://localhost:8081');

})
var server = http.createServer();
server.on('request', (req, res) => {
    // console.log(req.get('name'))
    //拿到客户端请求的路径 和请求参数


    res.setHeader('Content-Type', 'text/plain;charset=utf-8')//纯文本    text/html
    res.write('helo world! 你好 世界  ');
    //有可能乱码
    res.end();
});
// listen(port?: number, hostname?: string, backlog?: number, listeningListener?: () => void): this;
server.listen(8080, 'localhost', f => {
    console.log('服务器启动了！')
})



