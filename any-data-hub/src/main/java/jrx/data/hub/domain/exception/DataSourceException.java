package jrx.data.hub.domain.exception;

/**
 * 数据连接池初始化异常
 * @author
 * @date 2018/6/16
 */
public class DataSourceException extends RuntimeException {


    public DataSourceException(String msg){
        super(msg);
    }
}
