//package com.temp.springboot.common.core;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
//
//import java.math.BigDecimal;
//import java.util.Properties;
//
//public class PropertiesConfigurer extends PropertyPlaceholderConfigurer {
//
//    private Properties props;       // 存取properties配置文件key-value结果
//
//    @Override
//    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
//                            throws BeansException {
//        super.processProperties(beanFactoryToProcess, props);
//        this.props = props;
//    }
//
//    public String getProperty(String key){
//        return this.props.getProperty(key);
//    }
//
//    public Integer getInteger(String key){
//    	return getInteger(key, null);
//    }
//
//    public Long getLong(String key){
//    	return getLong(key,null);
//    }
//
//    public Double getDouble(String key){
//    	return getDouble(key, null);
//    }
//
//    public BigDecimal getBigDecimal(String key){
//    	return getBigDecimal(key, null);
//    }
//
//    public Boolean getBoolean(String key){
//    	return getBoolean(key, null);
//    }
//
//
//
//    public String getProperty(String key, String defaultValue) {
//        return this.props.getProperty(key, defaultValue);
//    }
//
//    public Integer getInteger(String key,Integer defaultValue){
//    	String value = getProperty(key);
//    	return value == null ? defaultValue : Integer.parseInt(value);
//    }
//
//    public Long getLong(String key,Long defaultValue){
//    	String value = getProperty(key);
//    	return value == null ? defaultValue : Long.parseLong(value);
//    }
//
//    public Double getDouble(String key,Double defaultValue){
//    	String value = getProperty(key);
//    	return value == null ? defaultValue : Double.parseDouble(value);
//    }
//
//    public BigDecimal getBigDecimal(String key,BigDecimal defaultValue){
//    	String value = getProperty(key);
//    	return value == null ? defaultValue : new BigDecimal(value);
//    }
//
//    public Boolean getBoolean(String key,Boolean defaultValue){
//    	String value = getProperty(key);
//    	return value == null ? defaultValue : Boolean.parseBoolean(value);
//    }
//
//
//
//}
