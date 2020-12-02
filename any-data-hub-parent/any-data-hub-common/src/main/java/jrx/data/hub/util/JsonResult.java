//package jrx.data.hub.util;
//
//import jrx.data.hub.domain.enums.CodeEnums;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.io.Serializable;
//
///**
// * <p>
// * 描述
// * </p>
// *
// * @author lw
// * @since 2020/10/22 17:03
// */
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class JsonResult<T> implements Serializable {
//
//    private int code;
//    private String message;
//    private T data;
//    private String sign;
//
//    public static <T> JsonResult<T> error(int code, String message) {
//        return new JsonResult(code, message, null, null);
//    }
//
//    public static <T> JsonResult<T> error(String message) {
//        return new JsonResult(CodeEnums.EROOR.code(), message, null, null);
//    }
//
//    public static <T> JsonResult<T> success() {
//        return new JsonResult(CodeEnums.SUCCESS.code(), CodeEnums.SUCCESS.getDesc(), null, null);
//    }
//
//    public static <T> JsonResult<T> success(T data) {
//        return new JsonResult(CodeEnums.SUCCESS.code(), CodeEnums.SUCCESS.getDesc(), data, null);
//    }
//}
