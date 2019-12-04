package com.nacos;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nacos.dao.ConfigInfo;
import com.nacos.dao.ConfigInfoMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * config_info 服务实现类
 * </p>
 *
 * @author schedule
 * @since 2019-12-04
 */
@Service
public class ConfigInfoServiceImpl extends ServiceImpl<ConfigInfoMapper, ConfigInfo> implements IConfigInfoService {

}
