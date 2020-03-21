package com.temp.jpa.jpa.jpautil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.DataException;

import javax.persistence.AttributeConverter;
import javax.sql.rowset.serial.SerialClob;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

/**
 * @author shengyong.huang
 * @date 2019/5/20
 */
@Slf4j
public class LargeTextConverter implements AttributeConverter<String, Clob> {

    @Override
    public Clob convertToDatabaseColumn(String str) {
        if (StringUtils.isBlank(str)){
            return null;
        }
        Clob clob = null;
        try {
            clob = new SerialClob(str.toCharArray());
        } catch (SQLException e) {
            log.error("字符串 转换 大文本失败",e);
        }
        return clob;
    }

    @Override
    public String convertToEntityAttribute(Clob clob) {
        if (clob == null) {
            return null;
        }
        Reader inStreamDoc = null;
        try {
            inStreamDoc = clob.getCharacterStream();
            char[] tempDoc = new char[(int) clob.length()];
            inStreamDoc.read(tempDoc);
            inStreamDoc.close();
            return new String(tempDoc);
        } catch (SQLException e) {
            log.error("大文本 转换 字符串失败 SQL",e);
        } catch (IOException e) {
            log.error("大文本 转换 字符串失败 IO",e);
        }
        return null;
    }

}
