package com.temp.jpa.jpa.dao;

import com.temp.jpa.jpa.entity.ObjectFieldEntity;
import com.temp.jpa.jpa.entity.TableEntityConversionRule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xinre
 * @date 2019/5/25 14:20
 */
//@Repository
public interface ObjectFieldEntityDao extends BaseDao<ObjectFieldEntity> {
    @Query(value = "select t from ObjectFieldEntity t ")
    List<ObjectFieldEntity> findAll();
    ObjectFieldEntity findByObjectFieldId(Integer id);

}
