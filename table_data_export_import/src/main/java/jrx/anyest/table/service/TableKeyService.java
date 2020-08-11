package jrx.anyest.table.service;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.jpa.entity.TableParamConfig;
import jrx.anyest.table.jpa.sql.PackageScanUtil;
import jrx.anyest.table.utils.TableSqlBulider;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;


/**
 * author lw
 * date 2019/8/30  19:54
 * discribe
 */
@Service
public class TableKeyService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Integer getNewKey(JdbcTemplate jdbcTemplate) {
        TableParamConfig tableParamConfig = TableDataCodeCacheManager.tableParamConfigs.get(TableConstants.KEY_SEQUENCE);
        Integer key;
        while (true){
            Integer integer = jdbcTemplate.queryForObject("select  " + tableParamConfig.getKeyColumn() + " from " + tableParamConfig.getTableDescribe() + " limit 1", Integer.class);
            Integer old = integer;
            int update = jdbcTemplate.update("update " + tableParamConfig.getTableDescribe() + " set " + tableParamConfig.getKeyColumn() + "=" + ++integer + "   where " + tableParamConfig.getKeyColumn() + "=" + old);
            if(update>0){
                key=integer;
                break;
            }
        }
        return key;
    }



}
