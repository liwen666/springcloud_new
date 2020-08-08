
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
