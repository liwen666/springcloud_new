//实现服务器端页面跳转

const http = require('http');
var mime = require('mime');
var path = require('path');
var fs = require('fs');
var url = require('url');
http.createServer((req, res) => {
    req.url = req.url.toLocaleLowerCase();
    req.method = req.method.toLocaleLowerCase();
    // var urlObj=url.parse(req.url);
    var urlObj = url.parse(req.url, true);
    var staticDir = path.join(__dirname, 'view');
    var fileName = path.join(staticDir, urlObj.pathname);
    if (urlObj.pathname === '/target.html') {
        // console.log('解析url获取到的值--->'+urlObj.query)
        // console.log('解析url获取到的值--->'+urlObj.query.user)
        var obj = urlObj.query;
        for (let i in obj) {
            console.log(i + "----" + obj[i] + '==========================')

        }
        let list = [];
        list.push(obj)

        let dir = path.join(__dirname, '/data');
        fs.writeFile(path.join(dir, 'data.json'), JSON.stringify(list), 'utf8', (err) => {
            if (err) {
                console.log(err)
            }
        })
    }else if(urlObj.pathname ==='/url.html') {
        res.statusCode = 302;
        res.statusMessage = 'Found';
        res.setHeader('Location', '/target.html');
    }
    res.render = function (fileName) {
        fs.readFile(fileName, (err, data) => {
            console.log(fileName)
            if (err) {
                res.writeHead(404, 'not find', {'Content-Type': 'text/plain;charset=utf-8'});
                res.end("找不到资源-->" + fileName)
            }else {
                res.setHeader('Content-Type', mime.getType(fileName) + ";charset=utf-8");
                res.end(data);
            }

        })
    }
    res.render(fileName);
    // var fileName = path.join(staticDir,req.url);



}).listen('8082', () => {
    console.log('服务启动在 http://localhost:8082');

});
