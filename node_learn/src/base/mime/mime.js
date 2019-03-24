const http = require('http');
var mime = require ('mime');
var path = require ('path');
var fs = require ('fs');
http.createServer((req, res) => {

    // (req,res){
    //     res.render = function(fileName){
    //         fs.readFile(fileName,(err,data)=>{
    //             res.writeHead(404,'not find',{'Content-Type':'text/plain;charset=utf-8'})
    //             res.end("找不到资源-->"+fileName)
    //         })
    //     }
        //以后调用res  的时候可以直接调用这个方法
    // }(req,res)


    console.log(req.url)  //拿到用户请求的路径
    var staticDir=path.join(__dirname,'static')

var fileName = path.join(staticDir,req.url);
    console.log('文件名字是----->'+fileName)
    fs.readFile(fileName, (err, data) => {
        console.log(data)
        if (err){
            // res.setHeader('Content-Type', 'text/plain;charset=utf-8');
            console.log('文件Type-->'+   mime.getType(fileName))
            res.statusCode=404;
            res.statusMessage="not find";
            if(mime.getType(fileName)==undefined){
                res.setHeader('Content-Type', 'text/plain;charset=utf-8');//纯文本    text/html
            }else {
                res.setHeader('Content-Type', mime.getType(fileName)+";charset=utf-8");
            }

          console.log('文件路径的扩展名称是--->'+  mime.getExtension( mime.getType(fileName)))
            res.end("找不到资源-->"+fileName)

            // throw err;
        }else{
            res.setHeader('Content-Type', mime.getType(fileName)+";charset=utf-8");
            res.end(data);
        }


    })

}).listen('8082', () => {
    console.log('服务启动在 http://localhost:8082');

})
