package com.temp.jpa.jpa.biz.impl;

import com.temp.jpa.jpa.dao.OrderAccountDao;
import com.temp.jpa.jpa.entity.OrderAccount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xinre
 * @date 2019/5/25 14:22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderAccountBiz extends BaseBizImpl<OrderAccount, OrderAccountDao>{
}
