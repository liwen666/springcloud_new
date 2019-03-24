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
    if (urlObj.pathname === '/url.html') {
        // console.log('解析url获取到的值--->'+urlObj.query)
        // console.log('解析url获取到的值--->'+urlObj.query.user)
        console.log(urlObj);
        console.log(urlObj.query.user);
        var obj = urlObj.query;
        console.log(JSON.stringify(obj))
        console.log(Object.keys(obj))
        console.log(Object.values(obj))

        for (let element in obj) {
            console.log(element + "----" + obj.element + '==========================')

        }
        for (let i in obj) {
            console.log(i + "----" + obj[i] + '==========================')

        }
        let list = [];
        list.push(obj)
        console.log(JSON.stringify(list))
        let dir = path.join(__dirname, '/data');
        // fs.mkdir(dir)
        fs.writeFile(path.join(dir, 'data.json'), JSON.stringify(list), 'utf8', (err) => {
            if (err) {
                console.log(err)
            }

        })
    }
    res.render = function (fileName) {
        fs.readFile(fileName, (err, data) => {
            if (err) {
                res.writeHead(404, 'not find', {'Content-Type': 'text/plain;charset=utf-8'});
                res.end("找不到资源-->" + fileName)
            } else {
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
