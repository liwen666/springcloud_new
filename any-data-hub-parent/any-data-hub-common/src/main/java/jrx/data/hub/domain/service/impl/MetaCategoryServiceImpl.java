package jrx.data.hub.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.service.IMetaCategoryService;
import jrx.data.hub.infrastructure.dao.MetaCategoryMapper;
import jrx.data.hub.infrastructure.entity.MetaCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类信息 服务实现类
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
@Service
public class MetaCategoryServiceImpl extends ServiceImpl<MetaCategoryMapper, MetaCategory> implements IMetaCategoryService {
    @Autowired
    MetaCategoryMapper metaCategoryMapper;

    public MetaCategory queryByName(String name) {
        QueryWrapper<MetaCategory> queryWrapper = new QueryWrapper<MetaCategory>();
        queryWrapper.eq("name", name);
        MetaCategory info = metaCategoryMapper.selectOne(queryWrapper);
        return info;
    }
}
