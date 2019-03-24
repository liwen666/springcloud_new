const fs = require('fs');
var msg = 'hello world, 你好 世界！'
console.log(__dirname)
console.log(__filename)
fs.writeFile(__dirname+'/hello.txt', msg, 'utf8', (err) => {
    // 此方法是异步执行
    if (err) throw err;
    console.log('The file has been saved!');
})
// importScripts(__dirname+'../../base/file/fa.js')
