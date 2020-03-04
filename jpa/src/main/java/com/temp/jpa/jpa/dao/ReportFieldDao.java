package com.temp.jpa.jpa.dao;

import com.temp.jpa.jpa.entity.DataObjectEntity;
import com.temp.jpa.jpa.entity.ReportFieldEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xinre
 * @date 2019/5/25 14:20
 */
@Repository
public interface ReportFieldDao extends BaseDao<ReportFieldEntity> {


    List<ReportFieldEntity> findByResourceId(int resourceId);

    List<ReportFieldEntity> findByResourceIdAndUsed(int resourceId,boolean used);

    List<ReportFieldEntity> findByUsed(boolean b);
//    List<ReportFieldEntity> findByResourceIdAndFieldName(int resourceId,String fieldName);
}
