package jrx.data.hub.application.controller;

import jrx.data.hub.domain.enums.DbType;
import jrx.data.hub.util.SQLParserUtil;
import org.junit.Test;

import java.sql.SQLSyntaxErrorException;

public class SQLParseTest {
    @Test
    public void testSql() throws SQLSyntaxErrorException {
        String sql = "CREATE TABLE `any_data_hub`.`Untitled`  (\n" +
                "  `resource_id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '函数信息ID',\n" +
                "  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '函数标识',\n" +
                "  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '函数名称',\n" +
                "  `content_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '租户号',\n" +
                "  `example` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '示例',\n" +
                "  `description` varchar(1000)   CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'hai' COMMENT '描述信息',\n" +
                "  `language` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '函数语言类型',\n" +
                "  `function_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '函数类型',\n" +
                "  `used` int(4) NULL DEFAULT NULL COMMENT '函数使用与否',\n" +
                "  `create_person` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',\n" +
                "  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',\n" +
                "  `update_person` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',\n" +
                "  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',\n" +
                "  PRIMARY KEY (`resource_id`) USING BTREE,\n" +
                "  INDEX `idx_meta_function_info_name`(`name`) USING BTREE,\n" +
                "  INDEX `idx_meta_function_info_code`(`code`) USING BTREE\n" +
                ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '函数信息' ROW_FORMAT = Dynamic;";
        System.out.println(SQLParserUtil.parserColumnForDDL(sql, DbType.MYSQL.name()));

    }
}
