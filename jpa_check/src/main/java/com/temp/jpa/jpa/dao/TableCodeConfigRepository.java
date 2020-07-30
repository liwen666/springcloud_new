package com.temp.jpa.jpa.dao;

import com.temp.jpa.jpa.entity.TableCodeConfig;
import com.temp.jpa.jpa.entity.TableCodeRelation;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xinre
 * @date 2019/5/25 14:20
 */
@Repository
public interface TableCodeConfigRepository extends BaseDao<TableCodeConfig> {


    TableCodeConfig findTableCodeConfigByTableCodeName(String codeName);
}
