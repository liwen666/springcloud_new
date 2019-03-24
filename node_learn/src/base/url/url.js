const http = require('http');
var mime = require ('mime');
var path = require ('path');
var fs = require ('fs');
var url = require ('url');
http.createServer((req, res) => {
       req.url= req.url.toLocaleLowerCase();
       req.method=req.method.toLocaleLowerCase();
       var urlObj=url.parse(req.url);
       console.log(urlObj)

        res.render = function (fileName){
            fs.readFile(fileName,(err,data)=>{
                if(err){
                    res.writeHead(404,'not find',{'Content-Type':'text/plain;charset=utf-8'})
                    res.end("找不到资源-->"+fileName)
                }else{
                    res.setHeader('Content-Type', mime.getType(fileName)+";charset=utf-8");
                    res.end(data);
                }

            })
        }
    var staticDir=path.join(__dirname,'view')
    var fileName = path.join(staticDir,req.url);
        res.render(fileName);

}).listen('8082', () => {
    console.log('服务启动在 http://localhost:8082');

})
