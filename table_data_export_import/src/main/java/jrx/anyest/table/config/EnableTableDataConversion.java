package jrx.anyest.table.config;

import jrx.anyest.table.TableApplicationStart;
import jrx.anyest.table.service.TableDataExpOrImpService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.*;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
//@Import({
//        TableDataExpOrImpService.class,
//        TableEnvironmentConfig.class
//})
//@EnableConfigurationProperties({TablePropertiesConfig.class})
//@Target(ElementType.TYPE)
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(TableApplicationStart.class)
public @interface EnableTableDataConversion {
}
