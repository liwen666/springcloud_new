package jrx.data.hub.domain.exception;

/**
 * @author Songyc5
 * @date: 2020/11/25
 */
public class InitializationException extends RuntimeException{

    public InitializationException(String msg){
        super(msg);
    }

    public InitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
