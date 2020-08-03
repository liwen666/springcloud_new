package jrx.anyest.table.jpa.entity;

import jrx.anyest.table.jpa.sql.TimeConverter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Getter
@Setter
@MappedSuperclass
public class TableBaseEntity implements Serializable {

    /**
     * 创建时间
     */
    @Column
    @Convert(converter = TimeConverter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date createTime;
    /**
     * 是否启用
     */
    @Column
    protected boolean used = true;
}
