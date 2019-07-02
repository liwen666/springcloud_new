package com.temp.springboot.common.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.Base64Utils;

import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DataConversion {
//	public static H2DataToOracle
	public static Object XmlObjectToObject(Object xmlObj , Object obj){
	     Field[] fxml = xmlObj.getClass().getDeclaredFields();
	     Field[] fobj = obj.getClass().getDeclaredFields();
	     for(int i= 0;i<fxml.length;i++){
	    	 fxml[i].setAccessible(true);
	    	 try {
	    	 for(Field f : fobj){
	    		 f.setAccessible(true);
	    		 if(f.getName().equals(fxml[i].getName())){
	    			 if(fxml[i].getType().getName().equals("javax.xml.datatype.XMLGregorianCalendar")){
	    				 if(null !=fxml[i].get(xmlObj)){
	    					 f.set(obj,DateUtil.xmlDate2Date((XMLGregorianCalendar) fxml[i].get(xmlObj)));
		    	    		 continue;
	    				 }

	    	    	 }
	    			 f.set(obj,fxml[i].get(xmlObj));
	    		 }
	    		 f.setAccessible(false);
	    	 }
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	    	 fxml[i].setAccessible(false);
	     }
		return obj;
	}
	public static Object ObjectToXmlObject(Object obj , Object xmlObj) {
		 Field[] fxml = xmlObj.getClass().getDeclaredFields();
	     Field[] fobj = obj.getClass().getDeclaredFields();
	     for(int i= 0;i<fxml.length;i++){
	    	 fxml[i].setAccessible(true);
	    	 try {
	    	 for(Field f : fobj){
	    		 f.setAccessible(true);
	    		 if(f.getName().equals(fxml[i].getName())){
	    			 if(fxml[i].getType().getName().equals("javax.xml.datatype.XMLGregorianCalendar")){
	    				 if(null !=f.get(obj)){
	    					 fxml[i].set(xmlObj,DateUtil.dateToXmlDate((Date)f.get(obj)));
		    	    		 continue;
	    				 }
	    	    	 }
	    			 fxml[i].set(xmlObj,f.get(obj));
	    		 }
	    		 f.setAccessible(false);
	    	 }
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	    	 fxml[i].setAccessible(false);
	     }
		return xmlObj;
	}

	public static <T> T mapToObject(Map<String, Object> map, T t)  {
		Class<?> aClass = t.getClass();
		Field[] declaredFields = aClass.getDeclaredFields();
			Field var1 = null;
		for(Field field :declaredFields){
			var1=field;
			field.setAccessible(true);
			try {
				if(map.get(field.getName())!=null){
                    if(field.getType().getName().equalsIgnoreCase("[B")){
                        field.set(t, Base64Utils.decodeFromString((String)map.get(field.getName())));
                        continue;
                    }
					if(field.getType().getName().equalsIgnoreCase("java.util.Date")){
//						@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
//						private Date startTime;  实体类上面加上这个注解，rest传输过程中解析才不会出错
						field.set(t, DateUtils.parseDate((String)map.get(field.getName()),"yyyy-MM-dd HH:mm:ss"));
						continue;
					}
                    field.set(t,map.get(field.getName()));

                }
			} catch (Exception e) {
				System.err.println("字段赋值失败  字段类型是-->"+var1.getType().getName() +"--字段名称是 ："+var1.getName());
			}
		}
		return t;
	}

	public static <T> List<T> listMapToListObj(List list, Class<T> tClass)  {
		List<T> list1 = new ArrayList<>();
		try {
			for (Object b :list){
				T object = DataConversion.mapToObject((Map<String, Object>) b, tClass.newInstance());
				list1.add(object);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return list1;
	}

}
