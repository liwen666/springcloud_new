package vip.dcpay.filesystem.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vip.dcpay.filesystem.domain.constant.CodeEnum;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult<T> implements Serializable {

    private int code;
    private String message;
    private T data;
    private String sign;

    public static <T> JsonResult<T> error(int code, String message) {
        return new JsonResult(code, message,null,null);
    }

    public static <T> JsonResult<T> success( ) { return new JsonResult(CodeEnum.SUCCESS.getCode(),CodeEnum.SUCCESS.getDesc(), null,null);
    }
    public static <T> JsonResult<T> success( T data) { return new JsonResult(CodeEnum.SUCCESS.getCode(),CodeEnum.SUCCESS.getDesc(), data,null);
    }
}
