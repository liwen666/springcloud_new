package com.temp.jpa.jpa.biz.impl;

import com.temp.jpa.jpa.dao.NoticDao;
import com.temp.jpa.jpa.entity.Notic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xinre
 * @date 2019/5/25 14:22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NoticBiz extends BaseBizImpl<Notic,NoticDao>{
}
