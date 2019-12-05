package jrx.batch.dataflow.controller;

/**
 * @Auther: liq
 * @Date: 2019/5/18 17:14
 * @Description:
 */
public interface IBaseEnum<T> {
    int code();

    String getDesc();

    String getDesc(Integer code);

//    T getEnum(int code);
//
//    boolean isValid(int code);
}
