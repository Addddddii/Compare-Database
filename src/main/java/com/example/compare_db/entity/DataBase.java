package com.example.compare_db.entity;

import com.example.compare_db.entity.structure.Table;
import com.example.compare_db.entity.structure.View;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 数据库结构
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Data
@NoArgsConstructor
public class DataBase {
	
	/**
	 * 数据库名称
	 */
	private String name;
	
	/**
	 * 数据库编码
	 */
	private String charset;	

	/**
	 * 数据引擎类型
	 */
	private String dataEngine;

	/**
	 * 表结构 
	 */
	private List<Table> tableList;
	
	/**
	 * 视图
	 */
	private List<View> viewList;

	/**
	 * 名称构造方法
	 */
	public DataBase(String name) {
		super();
		this.name = name;
	}
}
