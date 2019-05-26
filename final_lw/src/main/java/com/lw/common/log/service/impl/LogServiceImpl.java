package com.lw.common.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lw.common.log.dao.LogMapper;
import com.lw.common.log.domain.Log;
import com.lw.common.log.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tx
 * @since 2019-05-26
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {
    @Autowired
    private LogMapper logMapper;


}
