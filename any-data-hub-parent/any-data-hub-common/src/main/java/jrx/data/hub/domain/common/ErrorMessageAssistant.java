package jrx.data.hub.domain.common;

import jrx.data.hub.util.DataResponse;
import org.springframework.validation.BindingResult;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2020/11/16  11:11
 */
public class ErrorMessageAssistant {
    /**
     * 通用错误消息处理
     * @param bindingResult
     * @return
     */
    public static  DataResponse getDataResponse(BindingResult bindingResult) {
        DataResponse dataResponse;
        dataResponse = DataResponse.error();
        dataResponse.setReason(bindingResult.getAllErrors().get(0).getDefaultMessage());
        return dataResponse;
    }
}
