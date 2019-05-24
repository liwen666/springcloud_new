//package com.example.thymeleafdemo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Configuration
//public class StrConverterconfigration implements WebMvcConfigurer {
//
//    /**
//     * str转date
//     * @return
//     */
//    @Bean
//    public Converter<String, Date> stringToDateConvert() {
//        return new Converter<String, Date>() {
//            @Override
//            public Date convert(String source) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                Date date = null;
//                try {
//                    date = sdf.parse(source);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return date;
//            }
//        };
//    }
//
//    /**
//     *str转timestamp
//     * @retun
//     */
//    @Bean
//    public Converter<String, Timestamp> stringToTimeStampConvert() {
//        return new Converter<String, Timestamp>() {
//            @Override
//            public Timestamp convert(String source) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Timestamp date = null;
//                try {
//                    date = Timestamp.valueOf(source);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return date;
//            }
//        };
//    }
//
//}