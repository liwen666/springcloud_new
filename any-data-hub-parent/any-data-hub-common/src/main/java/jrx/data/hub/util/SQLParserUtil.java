package jrx.data.hub.util;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLIntegerExpr;
import com.alibaba.druid.sql.ast.statement.*;
import com.google.common.collect.Lists;
import jrx.data.hub.domain.enums.DbType;
import jrx.data.hub.infrastructure.entity.MetaObjectField;
import lombok.experimental.UtilityClass;

import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class SQLParserUtil {

    public List<MetaObjectField> parserColumnForDDL(String sql, String dbType) throws SQLSyntaxErrorException {
        List<String> columns = Lists.newArrayList();
        DbType dbTyp = DbType.valueOf(dbType);
        List<SQLStatement> list = SQLUtils.parseStatements(sql, dbTyp.name().toLowerCase());
        if (list.size() > 1) {
            throw new SQLSyntaxErrorException("MultiQueries is not supported,use single query instead ");
        }
        SQLCreateStatement statement = (SQLCreateStatement) list.get(0);
        SQLCreateTableStatement createTableStatement = (SQLCreateTableStatement) statement;
        List<SQLTableElement> sqlTableElements = createTableStatement.getTableElementList();

        List<MetaObjectField> fields = Lists.newArrayList();
        for (SQLTableElement sqlTableElement : sqlTableElements) {
            if (sqlTableElement instanceof SQLColumnDefinition) {
                MetaObjectField metaObjectField = new MetaObjectField();
                String code = ((SQLColumnDefinition) sqlTableElement).getName().getSimpleName().replace(dbTyp.getPrefix(), "").replace(dbTyp.getSuffix(), "");
                metaObjectField.setFieldCode(((SQLColumnDefinition) sqlTableElement).getName().getSimpleName().replace(dbTyp.getPrefix(), "").replace(dbTyp.getSuffix(), ""));
                metaObjectField.setFieldName(code);
                metaObjectField.setDescription(((SQLColumnDefinition) sqlTableElement).getComment().toString());
                metaObjectField.setFieldType(((SQLColumnDefinition) sqlTableElement).getDataType().getName());
                metaObjectField.setFieldLength(((SQLIntegerExpr) (((SQLColumnDefinition) sqlTableElement).getDataType().getArguments().get(0))).getNumber().intValue());
                if (((SQLColumnDefinition) sqlTableElement).getDefaultExpr() != null) {
                    metaObjectField.setDefaultValue(((SQLColumnDefinition) sqlTableElement).getDefaultExpr().toString());
                }
                columns.add(((SQLColumnDefinition) sqlTableElement).getName().getSimpleName());
                fields.add(metaObjectField);
            }
        }
        Optional<SQLTableElement> primaryOp = sqlTableElements.stream().filter(x -> x instanceof SQLPrimaryKey).findFirst();
        primaryOp.ifPresent(x -> {
            fields.stream().filter(y -> y.getFieldCode().equals(((SQLPrimaryKey) primaryOp.get()).getColumns().get(0).getExpr().toString().replace(dbTyp.getPrefix(), "").replace(dbTyp.getSuffix(), "")))
                    .findFirst().ifPresent(y -> {
                y.setIsKey(true);
            });
        });

        return fields;
    }
}
