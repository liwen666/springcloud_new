package jrx.batch.dataflow.domain.enums;

/**
 * @Date: 2019/5/18 17:14
 * @Description:
 */
public interface IBaseEnum<T> {
    int code();

    String getDesc();

    String getDesc(Integer code);
}
