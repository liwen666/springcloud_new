package it.pcan.test.feign.client;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import it.pcan.test.feign.bean.UploadInfo;
import it.pcan.test.feign.bean.UploadMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 * @author Pierantonio Cangianiello
 */
@SpringBootApplication
//@EnableFeignClients
public class ClientMain {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ClientMain.class, args);




    }

    @RestController
    public static class MyController {
        @Autowired
        private FeignClientTest feignClientTest;
        @RequestMapping("/")
        public String handler (Model model) {


            UploadInfo test = feignClientTest.test(new UploadMetadata("pippo"));
            System.out.println("test: " + test);


            return "myPage";
        }
    }
    @Bean
    public FeignClientTest initFeignClient(){
        Feign.Builder encoder = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new FeignSpringFormEncoder());

        FeignClientTest service = encoder.target(FeignClientTest.class, "http://localhost:8080");
    return service;
    }

}
