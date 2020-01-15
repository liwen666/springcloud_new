package it.pcan.test.feign.client;

import feign.Feign;
import feign.jackson.JacksonDecoder;

import java.util.Map;

/**
 *
 * @author Pierantonio Cangianiello
 */

public class FeignClientMainNode {

    public static void main(String args[]) {

        Feign.Builder encoder = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new FeignSpringFormEncoder());

        FeignClientNode service = encoder.target(FeignClientNode.class, "http://localhost:8080");
//        Map file = service.file("111","fdae");
//        System.out.println(file);

//
//        Map file1 = service.finddataflow("task","simplejob","1");
//        System.out.println(file1
//        );
        JsonResult result = service.finResult("task","simplejob","1");
        System.out.println(result
        );
//        Map map = service.appList("task", "simplejob", "1", false);
//        System.out.println(map);


    }

}
