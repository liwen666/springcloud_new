//package it.pcan.test.feign.fegin;
//
//import it.pcan.test.feign.bean.UploadInfo;
//import it.pcan.test.feign.bean.UploadMetadata;
//import it.pcan.test.feign.client.FeignClientTest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.feign.EnableFeignClients;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
///**
// *
// * @author Pierantonio Cangianiello
// */
//@SpringBootApplication
//@EnableFeignClients
//public class ClientMain {
//
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(ClientMain.class, args);
//
//
//
//
//    }
//
//    @RestController
//    public static class MyController {
//        @Autowired
//        private FaspService faspService;
//        @RequestMapping("/")
//        public String handler (Model model) {
//
//            Map<String, Object> userByTokenId = faspService.getUserByTokenId("1");
//
//
//            return "myPage";
//        }
//    }
////    @Bean
////    public FeignClientTest initFeignClient(){
////        Feign.Builder encoder = Feign.builder()
////                .decoder(new JacksonDecoder())
////                .encoder(new FeignSpringFormEncoder());
////
////        FeignClientTest service = encoder.target(FeignClientTest.class, "http://localhost:8080");
////    return service;
////    }
//
//}
