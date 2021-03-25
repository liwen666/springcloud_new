package jrx.anyest.table.service;

import jrx.anyest.table.constant.TableConstants;
import jrx.anyest.table.jpa.entity.TableParamConfig;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * author lw
 * date 2019/8/30  19:54
 * discribe
 */
@Service
public class TableKeyService {

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Long getNewKey(JdbcTemplate jdbcTemplate) {
        TableParamConfig tableParamConfig = TableDataCodeCacheManager.tableParamConfigs.get(TableConstants.KEY_SEQUENCE);
        Long key;
        while (true){
//            Integer integer = jdbcTemplate.queryForObject("select  " + tableParamConfig.getKeyColumn() + " from " + tableParamConfig.getTableDescribe() + " limit 1", Integer.class);
             jdbcTemplate.execute("lock tables  hibernate_sequence  write ");
            Long integer = jdbcTemplate.queryForObject("select  " + tableParamConfig.getKeyColumn() + " from " + tableParamConfig.getTableDescribe() + " limit 1", Long.class);
            Long old = integer;
            int update = jdbcTemplate.update("update " + tableParamConfig.getTableDescribe() + " set " + tableParamConfig.getKeyColumn() + "=" + ++integer + "   where " + tableParamConfig.getKeyColumn() + "=" + old);
            if(update>0){
                key=integer;
                break;
            }
        }
        jdbcTemplate.execute("unlock tables   ");
        return key;
    }



}
