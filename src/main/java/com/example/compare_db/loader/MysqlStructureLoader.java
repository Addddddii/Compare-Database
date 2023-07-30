package com.example.compare_db.loader;

import com.example.compare_db.constant.DataBaseEnum;
import com.example.compare_db.constant.constant.MessageConstant;
import com.example.compare_db.constant.constant.SqlConstant;
import com.example.compare_db.entity.DataBase;
import com.example.compare_db.entity.structure.Column;
import com.example.compare_db.entity.structure.Index;
import com.example.compare_db.entity.structure.Table;
import com.example.compare_db.entity.structure.View;
import com.example.compare_db.utils.JdbcUtils;
import com.example.compare_db.utils.StructureLoaderUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Service
public class MysqlStructureLoader implements StructureLoader {

	@Override
	public Boolean isCurrentType(String type) {
		return DataBaseEnum.MySql.getType().equals(type);
	}

	@Override
	public List<String> loadDatabaseNames(String key) throws Exception {
		List<DataBase> dataBaseList = JdbcUtils.queryToList(key, SqlConstant.MYSQL_QUERY_ALL_DATABASE, DataBase.class);
		return dataBaseList.stream().map(DataBase::getName).collect(Collectors.toList());
	}

	/**
	 * MySQL暂时未实现该方法
	 */
	@Override
	public List<String> loadSchemas(String key, String dataBaseName) throws Exception{
		return null;
	}

	@Override
	public DataBase loadDatabaseStructure(String key, String databaseName, String schemaName) throws Exception {
		Assert.isTrue(StringUtils.isNotBlank(databaseName), key + MessageConstant.DATABASE_NOT_NULL);
		DataBase database = new DataBase(databaseName);
		List<View> viewList = this.loadViewList(key, databaseName);
		database.setViewList(viewList);
		List<Table> tableList = this.loadTableList(key, databaseName);
		database.setTableList(tableList);
		return database;
	}

	private List<View> loadViewList(String key, String databaseName) throws Exception {
		return JdbcUtils.queryToListByArgs(key, SqlConstant.MYSQL_QUERY_ALL_VIEW, View.class, databaseName);
	}

	private List<Table> loadTableList(String key, String databaseName) throws Exception {
		Map<String, Table> tableMap;
		// 加载表结构
		List<Table> tableList = JdbcUtils.queryToListByArgs(key, SqlConstant.MYSQL_QUERY_ALL_TABLE, Table.class, databaseName);

		tableList.forEach(table -> {
			table.setDataBaseEnum(DataBaseEnum.MySql);
			table.setColumnList(new ArrayList<>());
			table.setIndexList(new ArrayList<>());
		});
		tableMap = tableList.stream().collect(Collectors.toMap(Table::getName, Function.identity(), (key1, key2) -> key2));
		// 获取列
		List<Column> columnList = JdbcUtils.queryToListByArgs(key, SqlConstant.MYSQL_QUERY_ALL_COLUMN, Column.class, databaseName);
		StructureLoaderUtils.processTableMap(tableMap,columnList);
//		Map<String, List<Column>> columnListMap = StructureLoaderUtils.getColumnListMapByColumnList(columnList);
//		for (Table value : tableMap.values()) {
//			List<Column> columnListNew = columnListMap.get(value.getName());
//			if (!CollectionUtils.isEmpty(columnListNew)){
//				value.setColumnList(columnListNew);
//			}
//		}
		// 获取索引
		Map<String, Index> indexTreeMap = new TreeMap<>();
		List<Index> indexList = JdbcUtils.queryToListByArgs(key, SqlConstant.MYSQL_QUERY_ALL_INDEX, Index.class, databaseName);
		Map<String, List<Index>> indexListMap = indexList.stream().collect(Collectors.groupingBy(Index::getName));
		for (List<Index> indexListNew : indexListMap.values()) {
			List<String> columnListNew = indexListNew.stream().map(Index::getColumnName).collect(Collectors.toList());
			Index newIndex = indexList.get(0);
			newIndex.setColumnList(columnListNew);
			if (indexTreeMap.get(newIndex.getName()) == null){
				indexTreeMap.put(newIndex.getName(),newIndex);
			}
		}
		for (Index index : indexTreeMap.values()) {
			Table table = tableMap.get(index.getTableName());
			if (table != null) {
				table.getIndexList().add(index);
			}
		}
		return tableList;
	}

}
