package jrx.anyest.table.config;

import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import java.util.Set;

@Component
public class SystemConfig {


//	@Bean
//	@ConditionalOnMissingBean(TransactionManager.class)
//	public TransactionManager transactionManager(DataSource datasource) {
//
//		return JpaTransactionManager(datasource);
//	}
}