package com.architect.all.geneator.tx.service.impl;

import com.architect.all.geneator.tx.domain.Menu;
import com.architect.all.geneator.tx.dao.MenuMapper;
import com.architect.all.geneator.tx.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tx
 * @since 2019-03-25
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
