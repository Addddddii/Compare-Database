package com.example.compare_db.entity.structure;

import lombok.Data;

/**
 * 列结构信息
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Data
public class Column {


	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * 列名
	 */
	private String name;

	/**
	 * 排序
	 */
	private String ordinalPosition;

	/**
	 * 字段类型
	 */
	private String type;

	/**
	 * 长度
	 */
	private String length;
	
	/**
	 * 小数点精度
	 */
	private String scale;

	/**
	 * 是否要求不为空
	 */
	private String isNullable;

	/**
	 * 默认值
	 */
	private String defaultValue;

	/**
	 * 注释
	 */
	private String comment;
	
	/**
	 * 是否递增
	 */
	private String autoIncrement;

	/**
	 * 主健
	 */
	private String primaryKey;

}
