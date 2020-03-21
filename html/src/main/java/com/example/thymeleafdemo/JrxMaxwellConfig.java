package com.example.thymeleafdemo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/5/26 23:40
 */
@Data
@Slf4j
public class JrxMaxwellConfig {
	public static Properties globleConfig=new Properties();


	/**
	 * 表的所有timestamp字段集合
	 */
	public static Map<String, Set<String>> tempstampColums;
	;

}
