package com.nacos;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import org.yaml.snakeyaml.DumperOptions;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @program: config
 * @description: 转换工具类
 * @author: xujingyang
 * @create: 2019-03-27 12:16
 **/
public class TransferUtils {

    private static final String ENCODING = "utf-8";


    public static Properties yml2Properties(String path) {
        final String DOT = ".";
        Properties properties = new Properties();
        try {
            YAMLFactory yamlFactory = new YAMLFactory();
            YAMLParser parser = yamlFactory.createParser(
                    new InputStreamReader(new FileInputStream(path), Charset.forName(ENCODING)));

            String key = "";
            String value = null;
            JsonToken token = parser.nextToken();
            while (token != null) {
                if (JsonToken.START_OBJECT.equals(token)) {
                    // do nothing
                } else if (JsonToken.FIELD_NAME.equals(token)) {
                    if (key.length() > 0) {
                        key = key + DOT;
                    }
                    key = key + parser.getCurrentName();
                    token = parser.nextToken();
                    if (JsonToken.START_OBJECT.equals(token)) {
                        continue;
                    }
                    value = parser.getText();
                    properties.setProperty(key, value);
                    int dotOffset = key.lastIndexOf(DOT);
                    if (dotOffset > 0) {
                        key = key.substring(0, dotOffset);
                    }
                } else if (JsonToken.END_OBJECT.equals(token)) {
                    int dotOffset = key.lastIndexOf(DOT);
                    if (dotOffset > 0) {
                        key = key.substring(0, dotOffset);
                    } else {
                        key = "";
                    }
                }
                token = parser.nextToken();
                System.out.println(token);
            }
            parser.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    public static List<String> listPros(String path) {
        Properties properties = yml2Properties(path);
        List<String> listPros = new ArrayList<>();
        for(Object key:properties.keySet()){
                listPros.add(key+"="+properties.get(key));
        }
        return listPros;
    }



//    public static void properties2Yaml(String path) {
//        JsonParser parser = null;
//        JavaPropsFactory factory = new JavaPropsFactory();
//        try {
//            parser = factory.createParser(
//                    new InputStreamReader(new FileInputStream(path), Charset.forName(ENCODING)));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            YAMLFactory yamlFactory = new YAMLFactory();
//            YAMLGenerator generator = yamlFactory.createGenerator(
//                    new OutputStreamWriter(new FileOutputStream(path), Charset.forName(ENCODING)));
//
//            JsonToken token = parser.nextToken();
//
//            while (token != null) {
//                if (JsonToken.START_OBJECT.equals(token)) {
//                    generator.writeStartObject();
//                } else if (JsonToken.FIELD_NAME.equals(token)) {
//                    generator.writeFieldName(parser.getCurrentName());
//                } else if (JsonToken.VALUE_STRING.equals(token)) {
//                    generator.writeString(parser.getText());
//                } else if (JsonToken.END_OBJECT.equals(token)) {
//                    generator.writeEndObject();
//                }
//                token = parser.nextToken();
//            }
//            parser.close();
//            generator.flush();
//            generator.close();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void properties2Yaml(Properties properties) {
        JsonParser parser = null;
        JavaPropsFactory factory = new JavaPropsFactory();
        parser = factory.createParser(properties);
        try {
            YAMLFactory yamlFactory = new YAMLFactory();
            YAMLGenerator generator = yamlFactory.createGenerator(
                    new OutputStreamWriter(new FileOutputStream("D:\\idea2018workspace\\springcloud_new\\nacos_base\\src\\main\\resources\\test.yaml"), Charset.forName(ENCODING)));
            JsonToken token = parser.nextToken();
            while (token != null) {
                if (JsonToken.START_OBJECT.equals(token)) {
                    generator.writeStartObject();
                } else if (JsonToken.FIELD_NAME.equals(token)) {
                    generator.writeFieldName(parser.getCurrentName());
                } else if (JsonToken.VALUE_STRING.equals(token)) {
                    generator.writeObject(parser.getText());
                } else if (JsonToken.VALUE_NUMBER_INT.equals(token)) {
                    System.out.println("******************************************************");
                } else if (JsonToken.END_OBJECT.equals(token)) {
                    generator.writeEndObject();
                }
                token = parser.nextToken();
            }
            parser.close();
            generator.flush();
            generator.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}