package jrx.anyest.table.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/10/22 16:24
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InterfaceAnalysisLog {
	String description() default "";
}
