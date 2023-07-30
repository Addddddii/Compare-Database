package com.example.compare_db.entity.structure;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 索引
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Data
public class Index {
	
	/**
	 * 索引所在的表名
	 */
	private String tableName;

	/***
	 * 索引名称
	 */
	private  String name;

	/***
	 * 参与索引的列
	 */
	private List<String> columnList;

	/**
	 * 索引类型   Normal | Unique | Full Text 
	 */
	private String indexType;

	/**
	 * 索引方法 btree hash
	 */
	private String indexMethod;

	/**
	 * 唯一索引 0 非唯一索引 1
	 */
	private String nonUnique;

	/**
	 * 索引顺序
	 */
	private String sort;

	/**
	 * 列名
	 */
	private String columnName;

	/**
	 * 注释
	 */
	private String comment;

	/***
	 * 参与索引的列 列名,排序
	 */
	private Map<String,String> columnMap;

}
