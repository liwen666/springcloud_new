package com.temp.springcloud.environment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xinre
 * @date 2019/5/25 14:22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AnnounceTypeBiz extends BaseBizImpl<AnnounceType,AnnounceTypeDao>{
}
