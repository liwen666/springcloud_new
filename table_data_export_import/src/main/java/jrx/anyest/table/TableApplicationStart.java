package jrx.anyest.table;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableFeignClients
@EnableTransactionManagement
public class TableApplicationStart {
	public static void main(String[] args) {
		SpringApplication.run(TableApplicationStart.class, args);
	}

}
