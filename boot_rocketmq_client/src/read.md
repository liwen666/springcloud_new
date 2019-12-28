http://localhost:8081/th-demo/html/a 请求主页

http://localhost:8081/ueditor/uconfig

获取到ue的配置信息

UE的8297行如何获取到 jsonp
localhost:8081/ueditor/controller?action=config&callback=bd__editor__fbxh1h


http://localhost:8081/newpay/test/callback

2477v852j2.zicp.vip:27765




H2



一、H2数据库常用数据类型

INT类型：对应java.lang.Integer

REAL类型：对应java.lang.Float

DOUBLE类型：对应java.lang.Double

DECIMAL类型：对应java.math.BigDecimal，比如DECIMAL(20,2)

CHAR类型：对应java.lang.String，比如CHAR(10)

VARCHAR类型：对应java.lang.String

VARCHAR_IGNORECASE类型：对应java.lang.String，忽略大小写

BOOLEAN类型：对应java.lang.Boolean

TIME类型：对应java.sql.Time，当转换成java.sql.Date时，日期会设置成1970-01-01

DATE类型：对应java.sql.Date，格式为yyyy-MM-dd，其时间默认为00:00:00

TIMESTAMP类型：对应java.sql.Timestamp，格式为yyyy-MM-dd hh:mm:ss[.nnnnnnnnn]，也支持java.util.Date

TINYINT类型：对应java.lang.Byte，-128 to 127

SMARTINT类型：对应java.lang.Short，-32768 to 32767

BIGINT类型：对应java.lang.Long

IDENTITY类型：自增类型，对应java.lang.Long，值范围-9223372036854775808 to 9223372036854775807

BINARY类型：二进制字节存储，最大不超过2GB，且完全保存在内存，比如BINARY(1000)

BLOB类型：对应java.sql.Blob，与BINARY相似，但针对很大的数据（如文件或图像），且不完全保存在内存，使用PreparedStatement.setBinaryStream来保存数据。

CLOB类型：与VARCHAR相似，但适用于保存很大的数据，且数据不完全保存在内存。用于任意尺寸的XML或HTML文档、文本文件等。使用PreparedStatement.setCharacterStream保存数据。

OTHER类型：对应java.lang.Object，用于存储序列化的Java对象，使用的是字节数组，客户端只能做序列化或反序列化，使用getObject反序列化，使用PreparedStatement.setObject存储数据。

UUID类型：对应java.util.UUID，128位的值，可以使用PreparedStatement.setBytes或setString或setObject(uuid)保存数据，使用ResultSet.getObject取回数据。
ARRAY类型：对应java.lang.Object[]

作者：chszs，转载需注明。博客主页：http://blog.csdn.net/chszs

二、H2常用函数

CURRENT_DATE：取当前日期

CURRENT_TIME：取当前时间

CURRENT_TIMESTAMP：取当前日期时间

LOWER：字符串小写

UPPER：字符串大写

CONCAT：字符串连结

CHAR：ASCII值转字符

ASCII：字符转ASCII值

ENCRYPT：加密函数，支持AES算法，Block尺寸为16字节，

比如CALL ENCRYPT('AES', '00', STRINGTOUTF8('Test'))

DECRYPT：解密函数，支持AES算法，Block尺寸为16字节，

比如CALL TRIM(CHAR(0) FROM UTF8TOSTRING(

DECRYPT('AES', '00', '3fabb4de8f1ee2e97d7793bab2db1116')))

HASH：哈希函数，只支持SHA256算法，比如CALL HASH('SHA256', STRINGTOUTF8('Password'), 1000)

MAX：求最大

MIN：求最小

SUM：求和

CURRENT_USER：返回当前用户

H2VERSION：返回H2数据库的版本

DISK_SPACE_USED：返回表使用的磁盘空间尺寸，比如CALL DISK_SPACE_USED('my_table');

DATABASE_PATH：返回数据库文件的路径和数据库名，比如CALL DATABASE_PATH();
