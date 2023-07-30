package com.example.compare_db.loader;

import com.example.compare_db.entity.DataBase;
import java.util.List;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
public interface StructureLoader  {

	/**
	 * 获取数据库类型
	 * @param type
	 * @return
	 */
	Boolean isCurrentType(String type);

	/**
	 * 通过连接获取数据库名称
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	List<String> loadDatabaseNames(String key) throws Exception ;

	/**
	 * 通过连接获取数据库中的Schemas
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	List<String> loadSchemas(String key, String dataBaseName) throws Exception;

	/**
	 * 获取数据库结构
	 * @param connection
	 * @param databaseName
	 * @return
	 * @throws Exception
	 */
	DataBase loadDatabaseStructure(String key, String dataBaseName, String schemaName) throws Exception;
}
