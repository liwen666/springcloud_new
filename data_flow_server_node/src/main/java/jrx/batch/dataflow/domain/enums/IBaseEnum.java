package jrx.batch.dataflow.domain.enums;
/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/3/6 21:07
 */
public interface IBaseEnum<T> {
    int code();

    String getDesc();

    String getDesc(Integer code);
}
