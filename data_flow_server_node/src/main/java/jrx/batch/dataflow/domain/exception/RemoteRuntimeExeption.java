package jrx.batch.dataflow.domain.exception;

/**
 * job执行状态异常
 * @author
 * @date 2018/5/23
 */
public class RemoteRuntimeExeption extends RuntimeException{

    public RemoteRuntimeExeption(String msg){
        super(msg);
    }
}
