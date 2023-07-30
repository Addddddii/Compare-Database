package com.example.compare_db.loader;

import com.example.compare_db.constant.DataBaseEnum;
import com.example.compare_db.constant.constant.MessageConstant;
import com.example.compare_db.constant.constant.SqlConstant;
import com.example.compare_db.entity.DataBase;
import com.example.compare_db.entity.structure.*;
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
public class SqlServerStructureLoader implements StructureLoader {

	@Override
	public Boolean isCurrentType(String type) {
		return DataBaseEnum.SqlServer.getType().equals(type);
	}

	@Override
	public List<String> loadDatabaseNames(String key) throws Exception {
		List<DataBase> dataBaseList = JdbcUtils.queryToList(key, SqlConstant.SQL_SERVER_QUERY_ALL_DATABASE, DataBase.class);
		return dataBaseList.stream().map(DataBase::getName).collect(Collectors.toList());
	}

	@Override
	public List<String> loadSchemas(String key, String dataBaseName) throws Exception {
		String sql = String.format(SqlConstant.SQL_SERVER_QUERY_ALL_SCHEMA, dataBaseName);
		List<Schema> schemaList = JdbcUtils.queryToList(key, sql, Schema.class);
		return schemaList.stream().map(Schema::getName).collect(Collectors.toList());
	}

	@Override
	public DataBase loadDatabaseStructure(String key, String databaseName, String schemaName) throws Exception {
		Assert.isTrue(StringUtils.isNotBlank(databaseName), key + MessageConstant.DATABASE_NOT_NULL);
		Assert.isTrue(StringUtils.isNotBlank(schemaName), key + MessageConstant.SCHEMA_NOT_NULL);
		DataBase database = new DataBase(databaseName);
		List<View> viewList = this.loadViewList(key, databaseName, schemaName);
		database.setViewList(viewList);
		List<Table> tableList = this.loadTableList(key, databaseName, schemaName);
		database.setTableList(tableList);
		return database;
	}

	private List<View> loadViewList(String key, String databaseName, String schemaName) throws Exception {
		String sql = String.format(SqlConstant.SQL_SERVER_QUERY_ALL_VIEW, databaseName, schemaName);
		return JdbcUtils.queryToList(key, sql, View.class);
	}

	private List<Table> loadTableList(String key, String databaseName, String schemaName) throws Exception {
		Map<String, Table> tableMap;
		String tableSql = String.format(SqlConstant.SQL_SERVER_QUERY_ALL_TABLE, databaseName,schemaName);
		List<Table> tableList = JdbcUtils.queryToList(key, tableSql, Table.class);
		// 加载表结构
		tableList.forEach(table -> {
			table.setDataBaseEnum(DataBaseEnum.SqlServer);
			table.setColumnList(new ArrayList<>());
			table.setIndexList(new ArrayList<>());
		});
		tableMap = tableList.stream().collect(Collectors.toMap(Table::getName, Function.identity(), (key1, key2) -> key2));
		// 获取列
		String columnSql = String.format(SqlConstant.SQL_SERVER_QUERY_ALL_COLUMN, databaseName,schemaName);
		List<Column> columnList = JdbcUtils.queryToList(key, columnSql, Column.class);
		StructureLoaderUtils.processTableMap(tableMap,columnList);
		// 获取索引
		Map<String, Index> indexTreeMap = new TreeMap<>();
		String indexSql = String.format(SqlConstant.SQL_SERVER_QUERY_ALL_INDEX.replaceAll(SqlConstant.PREFIX, databaseName), schemaName);
		List<Index> indexList = JdbcUtils.queryToList(key, indexSql, Index.class);
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
