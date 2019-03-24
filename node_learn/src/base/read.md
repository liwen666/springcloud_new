#全局模块不需要requare加载
       
            如process  不需要加载可以直接使用
            const fs = require('fs');
            全局模块有
        Global Objects
            Class: Buffer
            __dirname
            __filename
            clearImmediate(immediateObject)
            clearInterval(intervalObject)
            clearTimeout(timeoutObject)
            console
            exports
            global
            module
            process
            require()
            setImmediate(callback[, ...args])
            setInterval(callback, delay[, ...args])
            setTimeout(callback, delay[, ...args])
            URL
            URLSearchParams
            WebAssembly
 #浏览器的单线程异步调用  使用的是队列的方式 最后调用回调函数
    这种情况后台返回的数据不会阻塞浏览器加载数据
    setTimeout(function(){},0)
    动画演示
    
 ##try  {}catch(e){}  不适用与异步操作捕获异常  
   如文件  fs的操作就不能使用try catch   只能使用  对异常处理
   fs.readFile('./hello.txt','utf8', (err, data) => {
       // 此方法是异步执行
       if (err) throw err;
       console.log(data);
   });
##  写文件需要追加新内容   方法是读取以前的内容转换成数组，加入新内容  再将新数组写入文件
    var list = JSON.parse(jsonData || '[]') 意思是如果jsonData有值  就直接转换成数组 没有值就  转换一个空的数组