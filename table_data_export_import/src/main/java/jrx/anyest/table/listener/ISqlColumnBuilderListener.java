package jrx.anyest.table.listener;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
public interface ISqlColumnBuilderListener {

    /**
     * 表字段数据的特殊处理
     * @param s
     * @param v
     * @return
     */
    Object listener(String s, Object v);
}
