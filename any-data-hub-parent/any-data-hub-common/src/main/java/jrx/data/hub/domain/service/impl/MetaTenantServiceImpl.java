package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.common.ModelUpdateAssistant;
import jrx.data.hub.domain.exception.DataException;
import jrx.data.hub.domain.service.IMetaTenantService;
import jrx.data.hub.infrastructure.dao.MetaTenantMapper;
import jrx.data.hub.infrastructure.entity.MetaTenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-11-16
 */
@Transactional(rollbackFor = DataException.class)
@Service
public class MetaTenantServiceImpl extends ServiceImpl<MetaTenantMapper, MetaTenant> implements IMetaTenantService {

    @Autowired
    private MetaTenantMapper metaTenantMapper;

    public boolean update(MetaTenant metaTenant) {
        LocalDateTime localDateTime = LocalDateTime.now();
        metaTenant.setUpdateTime(localDateTime);
        int count = metaTenantMapper.updateById(metaTenant);
        if (count <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean create(MetaTenant metaTenant) {
        QueryWrapper<MetaTenant> infoQueryWrapper = new QueryWrapper<>();
        infoQueryWrapper.eq("tenant_name", metaTenant.getTenantName()).or().eq("tenant_code", metaTenant.getTenantCode());
        MetaTenant tenant = metaTenantMapper.selectOne(infoQueryWrapper);
        if (tenant != null) {
            throw new DataException("租户信息中name或者code已经存在");
        }
        ModelUpdateAssistant.setCreate(metaTenant);
        metaTenant.setTenantId(null);
        int count = metaTenantMapper.insert(metaTenant);
        if (count <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteBYId(String tenantId) {
        int flag = metaTenantMapper.deleteById(tenantId);
        return flag == 0 ? true : false;
    }

}
