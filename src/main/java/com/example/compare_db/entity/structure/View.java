package com.example.compare_db.entity.structure;

import com.example.compare_db.constant.DataBaseEnum;
import lombok.Data;

/**
 * 视图
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Data
public class View {
	
	/**
	 * 视图名称
	 */
	private String name;

	/**
	 * 创建视图的SQL
	 */
	private String definition;

	/**
	 * 视图可更新性标志
	 */
	private String isUpdatable;

	/**
	 * Schema
	 */
	private String schema;

	/**
	 * 数据库类型
	 */
	private DataBaseEnum dataBaseEnum;
}
