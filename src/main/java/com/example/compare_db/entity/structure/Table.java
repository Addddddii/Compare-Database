package com.example.compare_db.entity.structure;

import com.example.compare_db.constant.DataBaseEnum;
import lombok.Data;

import java.util.List;

/**
 * 表
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Data
public class Table {
	
	/**
	 * 表名
	 */
	private String name;

	/**
	 * 字段
	 */
	private List<Column> columnList;
	
	/**
	 * 索引
	 */
	private List<Index> indexList;
	
	/**
	 * 表引擎
	 */
	private String  engine;

	/**
	 * 表编码
	 */
	private String charset;

	/**
	 * Schema
	 */
	private String schema;

	/**
	 * 数据库类型
	 */
	private DataBaseEnum dataBaseEnum;

}
