package jrx.data.hub.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 描述数据转换
 * </p>
 *
 * @author LW
 * @since 2020/11/9  11:06
 */
@Slf4j
public class DataTransferUtils {
    public static <V> V modelToVo(Object source, V target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public static <V> List<V> modelListToVoList(List source, Class<V> target) {
        List<V> targetList = new ArrayList<>();
        for (int i = 0; i < source.size(); i++) {
            V v = null;
            try {
                v = target.newInstance();
            } catch (Exception e) {
                log.error("---model to list 数据转换异常---");
            }
            BeanUtils.copyProperties(source.get(i), v);
            targetList.add(v);
        }
        return targetList;
    }

    public static <K, V> IPage<V> pageModelToVo(IPage<K> source, Class<V> target) {
        List<K> records = source.getRecords();
        IPage<V> targetPage = new Page<>();
        BeanUtils.copyProperties(source, targetPage);
        List<V> recordsTarget = new ArrayList<V>();
        for (int i = 0; i < records.size(); i++) {
            try {
                V v = target.newInstance();
                BeanUtils.copyProperties(records.get(i), v);
                recordsTarget.add(v);
            } catch (Exception e) {
                log.error("---model to vo page 数据转换异常---");
            }
        }
        targetPage.setRecords(recordsTarget);
        return targetPage;
    }
}
