package it.pcan.test.feign.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public static <T> JsonResult<T> error( String message) {
        return new JsonResult(CodeEnums.EROOR.getCode(), message,null,null);
    }


    public static <T> JsonResult<T> success( ) { return new JsonResult(CodeEnums.SUCCESS.getCode(),CodeEnums.SUCCESS.getDesc(), null,null);
    }
    public static <T> JsonResult<T> success( T data) { return new JsonResult(CodeEnums.SUCCESS.getCode(),CodeEnums.SUCCESS.getDesc(), data,null);
    }
}
