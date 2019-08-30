# 更新缓存列表的web接口
http://localhost:10100/merchant/cache/updateList

# 根据商户uid更新商户缓存信息
http://localhost:10100/merchant/cache/updateById/1


#每日商家维度信息需要做定时清理任务
每日零点定时刷新缓存

#配置多数据源
2019.8.29

DBTypeEnum.SCHEDULE  
 PLATFORM,
    MERCHANT,
    ORDER,
    BASE,
    COMMON,
    SCHEDULE,
    SLAVE_BASE;