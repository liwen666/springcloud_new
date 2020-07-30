package com.temp.jpa.jpa.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 表数据导入导出处理结果
 * @param <T>
 */
public class TableDataImportOrExpResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 表名称
	 */
	private String tableName;
	/**
	 * 数据code标识
	 */
	private String strCode;
	/**
	 * 数据数量
	 */
	private Integer num;
}
