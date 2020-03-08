package jrx.batch.dataflow.domain.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/3/6 21:09
 */

public class ErrorSkipLoggerListener<T,S> implements SkipListener<T,S> {
    private static final Logger logger = LoggerFactory.getLogger(ErrorSkipLoggerListener.class);

    @Override
    public void onSkipInRead(Throwable t) {
        logger.error("read skip: "+t.getMessage(),t);
    }

    @Override
    public void onSkipInWrite(S item, Throwable t) {
        logger.error("write skip: "+t.getMessage(),t);
    }

    @Override
    public void onSkipInProcess(T item, Throwable t) {
        logger.error("process skip: "+t.getMessage(),t);
    }
}
