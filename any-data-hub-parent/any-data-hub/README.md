#Swagger
http://127.0.0.1:9018/any-data-hub/swagger-ui.html#/

#开发环境
http://10.0.8.16:9018/any-data-hub/swagger-ui.html#/   

#系统主页
http://localhost:9018/any-data-hub/index.html#/

#java-doc
-encoding UTF-8 -charset UTF-8

# 数据管理平台

## 提供功能点列表

#基础数据模型分类，基础实体类之间没有关系，子需要区分各自的用途
##vo 此包下的所有模型对象命名都以Vo结尾
- vo定义为返回页面的视图结构，其数据字段可以来源于任何数据模型

##entity 此包下的所有模型对象命名和表名保持一致
- entity 包下的类只是作为表数据的载体，属性和表字段一一对应

##dto 此包下的所有模型对象只作为数据转换和传输的中间媒介
- entity 包下的类只是作为表数据的载体，属性和表字段一一对应



