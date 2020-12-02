package jrx.data.hub.domain.enums;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020/10/22 16:26
 */

public interface IBaseEnum<T> {
    int code();

    String getDesc();

    String getDesc(Integer code);
}
