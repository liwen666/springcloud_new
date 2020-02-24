package com.temp.jpa.jpa.entity;

import com.temp.jpa.jpa.jpautil.LargeTextConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 数据对象版本
 * @author peidong.meng
 * @date 2018/9/27
 */
@Entity
@Getter
@Setter
@Table(name = "meta_data_object", indexes ={@Index(name = "idx_resource_id", columnList = "resourceId"), @Index(name = "idx_version_state", columnList = "versionState")})
public class DataObjectEntity  extends  BaseVersionEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer dataObjectId;

    /**
     * 是否被占用
     */
    @Column
    private Boolean used=false;

    /**
     * 参数(基础字段的json格式，仅需保存名称/标识/类型)
     */
    @Column
    @Convert(converter = LargeTextConverter.class)
    private String paramsJson;

    /**
     * 包含字段id集合，如1，2，3
     */
    @Column(name = "field_ids",length = 8000)
    private String fieldIds;

    /**数据对象额外的配置信息，如果是sql_view的视图类型，包含key值有，sql和variable声明变量2项*/
    @Column
    @Convert(converter = LargeTextConverter.class)
    private String configs;

    public Boolean getUsed(){
        if(null == used){
            return false;
        }

        return used;
    }
}
