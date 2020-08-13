
#res_rule_info
SELECT count(*)num ,name FROM `res_rule_info` where project_id=4645 GROUP BY name order by num 


功能配置




#id关联版本数据
               tableConversionKey.setId(null);
               tableConversionKey.setTableCodeName("res_rule");
               tableConversionKey.setTableCodeChinaName("规则信息表");
               tableConversionKey.setConversionKey("meta_object_field@field_ids");
               tableConversionKey.setHandleBeanName("defaultTableDataHandler");
               tableConversionKey.setJsonObject(true);
               
#id 无法判断所属表问题，通过配置多个表暴力查找

                tableConversionKeyRepository.save(tableConversionKey);
                tableConversionKey.setId(null);
                tableConversionKey.setTableCodeName("meta_object_field");
                tableConversionKey.setTableCodeChinaName("字段表");
                tableConversionKey.setConversionKey("meta_data_object_info|meta_model_object_info|meta_topic_object_info@deriveContent.tableId");
                tableConversionKey.setHandleBeanName("defaultTableDataHandler");
                tableConversionKey.setJsonObject(false);
                
不支持暴力查找的对象id引用到版本对象的情况，如需处理使用listener来处理。



字段表的iD存在两种被引用的形式
 bid  id


#code的处理
id-code 的时候全部加上表前缀
code-id  id的表前缀可以不要

数据导入时方便恢复
#0 在一个事务下开启另外一个事务解决hibernate获取主键id的可见性


#1.code的数据筛选功能
##1.1 code 根据传参进行定制化处理
#2. id指向多个表
#3. 被关联的数据是个版本概念的数据需要绑定导出，并导入时恢复ID
#4. 多表入库之间存在循环依赖关系，循环依赖的数据键可以在listener的后置处理进行数据id的恢复
#5. code的二次转换达到唯一性

#6 资源管理检查 code加载有顺寻


#7 多表混合导入可以解决统计模型引用统计模型的情况

#8 页面传参，id 绑定版本可以实现不同版本的灵活选择



问题： 缺失必要code查询条件，导入会堆栈溢出




