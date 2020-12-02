package jrx.data.hub.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jrx.data.hub.infrastructure.entity.MetaRelationInfo;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 对象之间的关联关系  Mapper 接口
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
@Repository
public interface MetaRelationInfoMapper extends BaseMapper<MetaRelationInfo> {

}
