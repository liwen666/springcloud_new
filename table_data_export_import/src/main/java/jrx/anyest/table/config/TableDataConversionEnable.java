package jrx.anyest.table.config;

import jrx.anyest.table.service.TableDataExpOrImpService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
@Import({
        TableDataExpOrImpService.class
})
@EnableConfigurationProperties({TablePropertiesConfig.class})
public @interface TableDataConversionEnable {
}
