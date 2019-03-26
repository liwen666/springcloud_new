package com.architect.all.spring.security.config.java;

import lombok.Data;
import org.apache.ibatis.transaction.TransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

/**
 */

@Data
public class ArchitectSqlSessionFactoryBean extends SqlSessionFactoryBean {

    private static final Logger logger = LoggerFactory.getLogger(ArchitectSqlSessionFactoryBean.class);

    private static final String WF_MAPPER_XML_END_IDENTIFIER = "WfSqlMapper.xml";

    @Autowired
//	@Qualifier("dataSource")
	private DataSource dataSource;

	@SuppressWarnings("unused")
	private Resource configLocation;

	private Resource[] mapperLocations;

	@SuppressWarnings("unused")
	private TransactionFactory transactionFactory;

	@Override
	public void setTransactionFactory(TransactionFactory transactionFactory) {
		super.setTransactionFactory(transactionFactory);
	}

	@Override
	public void setConfigLocation(Resource configLocation) {
		super.setConfigLocation(configLocation);
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		if (dataSource instanceof TransactionAwareDataSourceProxy) {
			this.dataSource = ((TransactionAwareDataSourceProxy) dataSource)
					.getTargetDataSource();
		} else {
			this.dataSource = dataSource;
		}
		super.setDataSource(this.dataSource);
	}

	@Override
	public void setMapperLocations(Resource[] mapperLocations) {
		this.mapperLocations = mapperLocations;
		super.setMapperLocations(this.getCurrentDatabaseResources());
	}

	private Resource[] getCurrentDatabaseResources() {

		Resource[] currentDatabaseResources = new Resource[this.mapperLocations.length];
		String databaseLogo = "mysql";
		try {
			databaseLogo = this.getDatabaseIdProvider().getDatabaseId(dataSource);
		} catch (SQLException e) {
			logger.error("获取数据库类型错误！");
			e.printStackTrace();
		}
		this.setBpmnDatabaseType(databaseLogo);
		for (int i = 0; i < mapperLocations.length; i++) {
			URL url = null;
			String path;
			try {
				url = mapperLocations[i].getURL();
			} catch (IOException e) {
				logger.error("获取mapper.xml文件url信息错误！");
				e.printStackTrace();
			}
			if (url != null) {
				path = url.getPath();
				if (this.wfMapperXmlPath(path)) {
					if (path.contains(databaseLogo.toLowerCase())) {
						logger.info(" *Mapper.xml path is <===> {}", path);
						currentDatabaseResources[i] = mapperLocations[i];
					}
				}else{
					currentDatabaseResources[i] = mapperLocations[i];
				}
			}
		}
		return currentDatabaseResources;
	}

	private void printDatabaseLogo(String databaseLogo) {
		System.out.println("##" + "database " + "type " + "is " + databaseLogo);
	}

	private void setBpmnDatabaseType(String databaseLogo) {
        this.printDatabaseLogo(databaseLogo);
    }

	private boolean wfMapperXmlPath(String mapperXmlPath) {
        if (mapperXmlPath.endsWith("Mapper.xml")) {
			return true;
        } else {
            return false;
        }
	}


}
