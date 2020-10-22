package jrx.data.hub.domain.config.annotation;

import java.lang.annotation.*;
/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/10/22 16:24
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface BeanOveride {
    /**
     * 表名
     * @return
     */
    String tableName() default "";
}
