package jrx.batch.dataflow;


import jrx.batch.dataflow.quartz.QuartzManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ServletComponentScan
public class SpringbootDataflowServerApplication {
    @Autowired
    QuartzManager quartzManager;

    public static void main(String[] args) {

        SpringApplication.run(SpringbootDataflowServerApplication.class, args);


    }
}