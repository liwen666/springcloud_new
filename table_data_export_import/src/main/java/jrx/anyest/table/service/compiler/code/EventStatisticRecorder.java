package jrx.anyest.table.service.compiler.code;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;

/**
 * 事件统计记录器
 * 每一个统计模型一个记录器
 * 当前统计记录器使用时间窗口存储小时，分钟，秒的数据
 * 使用分片存储天，周，月数据
 * 统计模型的关联表计算关联表类型时，需要判断关联表的连接类型
 * 如果连接类型为左连接时，如果查询主表数据不存在时，关联表的数据不计算，不存储
 * 因此，左连接的表一定是在主表之后执行
 * 统计模型计算不存在右连接情况，右连接时则必须为定义为左边的表为主表
 * 全连接则是主表，关联表数据全部都记录，默认为空就是全连接
 *  添加支持实体模型 计算和存储
 * @author
 * @date 2019/2/8
 */
@Slf4j
public class EventStatisticRecorder {


    public Map<String, Object> findRelationData(String objectInfoCode, String keyValue, Object statTime, Set<Integer> columnPositions, boolean b) {
        return null;
    }

    public Map<String, Object> findCacheDataUseCode(String objectInfoCode, String key) {
        return null;
    }
}
