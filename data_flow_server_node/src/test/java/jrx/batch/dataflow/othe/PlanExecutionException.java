package jrx.batch.dataflow.othe;

/**
 * 调度计划执行异常
 *
 * @author
 * @date 2018/4/12
 */
public class PlanExecutionException extends RuntimeException {


    public PlanExecutionException(String msg) {
        super(msg);
    }

    public PlanExecutionException() {
        super();
    }

}
