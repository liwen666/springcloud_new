package jrx.anyest.table.jpa.jpautil;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author shengyong.huang
 * @date 2019/5/20
 */
public class TimeConverter implements AttributeConverter<Date, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(Date date) {
        if (date == null){
            return null;
        }
        return new Timestamp(date.getTime());
    }

    @Override
    public Date convertToEntityAttribute(Timestamp timestamp) {
        return timestamp;
    }

}
