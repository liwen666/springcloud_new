package jrx.data.hub.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jrx.data.hub.infrastructure.entity.MetaTenant;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lw
 * @since 2020-11-16
 */
public interface IMetaTenantService extends IService<MetaTenant> {

    boolean update(MetaTenant metaTenant);

    boolean create(MetaTenant metaTenant);

    Boolean deleteBYId(String tenantId);
}
