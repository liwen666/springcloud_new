//node support file controller


const fs = require('fs');

// fs.unlink('/tmp/hello', (err) => {
//     if (err) throw err;
//     console.log('successfully deleted /tmp/hello');
// });
var msg = 'hello world, 你好 世界！'

//调用fs.writeFile()write file  fa hase async  and  sync  method
// function writeFile(path: PathLike | number, data: any, callback: (err: NodeJS.ErrnoException) => void): void;
// 需要使用__dirname 来定位文件 否则有可能找不到
fs.writeFile('./hello.txt', msg, 'utf8', (err) => {
    // 此方法是异步执行
    if (err) throw err;
    console.log('The file has been saved!');
})
// function readFile(path: PathLike | number, callback: (err: NodeJS.ErrnoException, data: Buffer) => void): void;
fs.readFile('./hello.txt','utf8', (err, data) => {
    // 此方法是异步执行
    if (err) throw err;
    console.log(data);
});
fs.readFile('./hello.txt', (err, data) => {
    // 此方法是异步执行
    if (err) throw err;
    console.log(data);
    console.log(data.toString('utf8'));//it can conversion byte to chinese
});
//创建目录   fs.mkdir