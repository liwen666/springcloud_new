package jrx.batch.dataflow.controller;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * 捕获异常返回对象.
 *
 * @author
 */
@ControllerAdvice
public class BatchExceptionHandler {


    private static final Logger logger = LoggerFactory.getLogger(BatchExceptionHandler.class);



    @ExceptionHandler(value = {
            Exception.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public JsonResult exception(HttpServletRequest request, HttpServletResponse response, Exception e) {
        return JsonResult.error("失败");
    }


    @ExceptionHandler(value = {
            RuntimeException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse runtime(HttpServletRequest request, HttpServletResponse response, Exception e) {

        return ErrorResponse.builder().message(e.getMessage())
                .path(request.getRequestURI())
                .timestamp(System.currentTimeMillis())
                .exception(e.getClass().getName()).build();

    }

//    @ExceptionHandler(value = {
//            PlanExecutionException.class})
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ErrorResponse defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
//        ErrorResponse error = new ErrorResponse();
//        error.setError(e.getLocalizedMessage());
//        logger.error(e.getLocalizedMessage(), e);
//        StackTraceElement[] stackTrace = e.getStackTrace();
//        return error;
//    }
}