package jrx.data.hub.domain.exception;

/**
 * @author Songyc5
 * @date: 2020/11/25
 */
public class JobExecuteException extends RuntimeException {

    public JobExecuteException(String message) {
        super(message);
    }

    public JobExecuteException(String message, Throwable cause) {
        super(message, cause);
    }
}
