package jrx.anyest.table;

import jrx.anyest.table.config.TableDataConversionEnable;
import jrx.anyest.table.config.TablePropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
@EnableTransactionManagement
@TableDataConversionEnable
public class ApplicationStart {
	public static void main(String[] args) {
		List<String> strings = new ArrayList<>();
		strings.add("--spring.config.location=D:\\workspace\\springcloud_new\\table_data_export_import\\src\\main\\resources\\application.properties");
		args= strings.toArray(new String[0]);
		SpringApplication.run(ApplicationStart.class, args);

	}

}
