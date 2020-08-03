package jrx.anyest.table.jpa.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 表数据导入导出处理结果
 * @param <T>
 */
@Getter
@Setter
public class TableDataImportOrExpResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 表名称
	 */
	private String tableName;
	/**
	 * 表名称
	 */
	private String tableChinaName;
	/**
	 * 数据内容
	 */
	private T data;

}
