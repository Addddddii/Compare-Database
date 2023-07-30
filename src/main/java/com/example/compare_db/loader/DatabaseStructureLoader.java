package com.example.compare_db.loader;

import com.example.compare_db.entity.DataBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Service
public class DatabaseStructureLoader {
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseStructureLoader.class);

	@Autowired
	private List<StructureLoader> structureLoaderList;

	public DataBase loadDatabase(String key, String dataBaseName, String schemaName, String type) throws Exception{
		StructureLoader structureLoader = structureLoaderList.stream().filter(s -> s.isCurrentType(type)).findFirst().orElse(null);
		return structureLoader.loadDatabaseStructure(key, dataBaseName, schemaName);
	}

	/**
	 * 从数据中加载表结构
	 * @param key
	 * @param type
	 * @return dataBaseNameList
	 */
	public List<String> loadAllDatabaseNames(String key, String type) throws Exception{
		StructureLoader structureLoader = structureLoaderList.stream().filter(s -> s.isCurrentType(type)).findFirst().orElse(null);
		return  structureLoader.loadDatabaseNames(key) ;
	}

	/**
	 * 从数据库中加载表结构Schema SqlServer
	 * @param key
	 * @param type
	 * @return dataBaseNameList
	 */
	public List<String> loadSchemaByDataBaseName(String key, String dataBaseName, String type){
		List<String> schemaNameList = new ArrayList<>();
		try {
			StructureLoader structureLoader = structureLoaderList.stream().filter(s -> s.isCurrentType(type)).findFirst().orElse(null);
			schemaNameList = structureLoader.loadSchemas(key, dataBaseName);
		} catch (Exception e) {
			LOGGER.error("获取数据库结构出错", e);
		}
		return schemaNameList;
	}
	
}
