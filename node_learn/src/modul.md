#  模块的引用
##文件模块
    const fs = require('fs');
    
## path模块  文件的路径拼接
    const path = require('path');
    var filename = path.join(__dirname,'hello.txt')
## http模块  文件的路径拼接
        const http = require('http');
        服务器加上响应报文头  告诉浏览器编码 即可解决乱码问题
   ###  解决乱码问题 res.setHeader('Content-Type','text/plain;charset=utf-8')//纯文本    text/html  
   ###  拿到客户端请求的路径
          console.log(req.url)  //拿到用户请求的路径    请求的路径可以传参数
   ###  响应资源  根据请求的url读取服务器的文件  如果没有返回错误消息   
      request   request.headers   返回一个对象  其中包含了所有的请求报文头   request.rawHeaders  返回的是一个数组 数组包含请求报文头的字符串
                request.method  如（get post  ） request.url  request.httpVersion   http版本
      responst   res.write('hello 我'，'utf8')  //表示将数据已什么编码响应
                 res.end()  里面都是可选参数   表示告诉浏览器响应结束了   end([data][,encoding][,function])
                 res.statusCode  res.statusMessage="not find"; 设置状态码需要提前设置    和setHeader一样
                  res.setHeader('Content-Type', 'text/plain;charset=utf-8');//纯文本    text/html
      res.writeHead()   要在res.write()  and res.end()  之前    res.writeHead(404,'not find',{'Content-Type':'text/plain;charset=utf-8'})
      res实现页面跳转的原理
       res.statusCode = 302;
       res.statusMessage = 'Found';
        res.setHeader('Location', '/target.html');
        数组的转换
         var list = JSON.parse(jsonData || '[]')
## mime模块  作用就是根据请求的路径确定返回的  请求头
        const mime = require('mime');
          res.setHeader('Content-Type', mime.getType(fileName)+";charset=utf-8");
         在cmd  窗口下可以使用 mime  a.png  查看HTTP协议的类型 
         
## url 模块  
        处理req.url  后面的参数
         var url = require('url');
          req.url= req.url.toLocaleLowerCase();
             req.method=req.method.toLocaleLowerCase();
          var urlObj=url.parse(req.url,true);  第二个参数表示将参数解析出对象
         为js对象扩展属性  和方法
         Object.prototype.protoPer1 = function(){console.log("name is tom");};//通过原型链增加属性，为一个函数
         Object.prototype.protoPer2 = 2;////通过原型链增加属性，为一个整型值2
         *******************遍历对象的属性和值用以下方法***********************************
          for(let i in obj){
                 console.log(i +"----"+ obj[i]+'==========================')
         
             }
             
          服务器实现页面跳转
         
#package.json
  ##创建 package.json
        使用npm  init -y  自动创建 package.json
        1.  package  必须有name  version  两个属性 
  ##package-lock.json  用来记录  依赖的包的地址  
    下次使用npm install   下载依赖包速度回很快  不用一个个搜索。
#给对象挂载方法
  例如http  的response 对象   res.render  使方法看起来更加简洁
 
  
        
   