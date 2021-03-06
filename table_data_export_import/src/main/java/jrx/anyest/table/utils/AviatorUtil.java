package jrx.anyest.table.utils;

import com.googlecode.aviator.Expression;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author peidong.meng
 * @date 2019/9/4
 */
@Slf4j
public class AviatorUtil {

    /**
     * aviator计算统一方法，统一进行数据处理
     *
     * @return
     */
    public static Object compute(Expression expression, Map<String, Object> data) {

        if (expression == null) {
            return null;
        }
        // 表达式使用到的字段名称
        List<String> dataName = expression.getVariableNames();
        // 表达式使用到的字段值（重新构建到新的map中）
        Map<String, Object> params = new HashMap<>(dataName.size());
        if (CollectionUtils.isNotEmpty(dataName)) {
            for (String s : dataName) {
                // 忽略nil情况
                if (!s.equals("nil")) {
                    // 如果参数缺失，返回空值
                    if (!data.containsKey(s)) {
                        log.warn("compute aviator param【{}】 can not find", s);
                        // 金额类型处理，转换成仅double
                    } else if (!Objects.isNull(data.get(s)) && data.get(s).getClass() == BigDecimal.class) {
                        params.put(s, ((BigDecimal) data.get(s)).doubleValue());
                    } else {
                        params.put(s, data.get(s));
                    }
                }
            }
        } else {
            log.warn("compute aviator no param");
        }

        if (log.isDebugEnabled()) {
            log.debug("aviator params: {}", params.values());
        }

        Object result = expression.execute(params);

        if (log.isDebugEnabled()) {
            log.debug("aviator result: {}", result);
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
