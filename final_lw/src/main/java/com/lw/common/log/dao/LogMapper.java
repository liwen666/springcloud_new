package com.lw.common.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lw.common.log.domain.Log;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tx
 * @since 2019-05-26
 */
@Repository
public interface LogMapper extends BaseMapper<Log> {

    Log selectMy(String s);
}
