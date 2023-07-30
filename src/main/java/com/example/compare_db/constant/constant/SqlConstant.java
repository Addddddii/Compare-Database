package com.example.compare_db.constant.constant;

/**
 * sql语句
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
public class SqlConstant {
    /**
     * MySql 查询所有库
     */
    public static final String MYSQL_QUERY_ALL_DATABASE = "SELECT `table_schema` AS `name` FROM information_schema.`TABLES` GROUP BY `table_schema`";

    /**
     * MySql 查询该数据库下所有视图
     */
    public static final String MYSQL_QUERY_ALL_VIEW = "SELECT `TABLE_NAME` AS `name`,`VIEW_DEFINITION` AS `definition`,`IS_UPDATABLE` AS `isUpdatable` FROM  information_schema.`VIEWS` WHERE `TABLE_SCHEMA` = ? ";

    /**
     * MySql 查询该数据库下所有表
     */
    public static final String MYSQL_QUERY_ALL_TABLE = "SELECT `TABLE_NAME` AS `name`,`ENGINE` AS `engine`,`TABLE_COLLATION`  AS `charset` FROM information_schema.`TABLES` WHERE `TABLE_TYPE` = 'BASE TABLE' AND `TABLE_SCHEMA` = ? ";

    /**
     * MySql 查询该数据库下所有表的所有字段
     */
    public static final String MYSQL_QUERY_ALL_COLUMN = "SELECT `TABLE_NAME` AS `tableName`,`COLUMN_NAME` AS `name`,`DATA_TYPE` AS `type`,IFNULL(`CHARACTER_MAXIMUM_LENGTH`, `NUMERIC_PRECISION`) AS `length`,`NUMERIC_SCALE` AS `scale`,`COLUMN_COMMENT` AS `comment`,`COLUMN_DEFAULT` AS `defaultValue`,`IS_NULLABLE` AS `isNullable`,`EXTRA` AS 'extra' FROM information_schema.`COLUMNS` WHERE `TABLE_SCHEMA` = ? ";

    /**
     * MySql 查询该数据库下所有表的所有索引
     */
    public static final String MYSQL_QUERY_ALL_INDEX = "SELECT `TABLE_NAME` AS `tableName`,`NON_UNIQUE` AS `nonUnique`,`INDEX_NAME` AS `name`,`SEQ_IN_INDEX` AS `sort`,`COLUMN_NAME` AS `columnName`,`INDEX_TYPE` AS `indexType`,CONCAT(`COMMENT`, `INDEX_COMMENT`) AS `comment` FROM information_schema.`STATISTICS` WHERE `TABLE_SCHEMA` = ? ORDER BY `TABLE_NAME`,`INDEX_NAME`,`SEQ_IN_INDEX`";

    /**
     * SqlServer 因为数据库不能确定 所以直接通过拼接传递参数
     */
    public static final String PREFIX = ":#";

    /**
     * SqlServer 查询所有非系统库
     */
    public static final String SQL_SERVER_QUERY_ALL_DATABASE = "SELECT [NAME] AS [name] FROM [MASTER].[SYS].[DATABASES] WHERE [NAME] NOT IN ('master','model','msdb','tempdb') ORDER BY [NAME]";

    /**
     * SqlServer 查询库下所有Schema
     */
    public static final String SQL_SERVER_QUERY_ALL_SCHEMA = "SELECT [name] FROM [%s].[sys].[schemas] WHERE [name] NOT IN ('db_owner','db_accessadmin','db_securityadmin','db_ddladmin','db_backupoperator','db_datareader','db_datawriter','db_denydatareader','db_denydatawriter','INFORMATION_SCHEMA','sys') ORDER BY [name]";

    /**
     * SqlServer 查询该数据库下所有视图
     */
    public static final String SQL_SERVER_QUERY_ALL_VIEW = "SELECT [TABLE_SCHEMA] AS [schema],[TABLE_NAME] AS [name],[VIEW_DEFINITION] AS [definition],[IS_UPDATABLE] AS [isUpdatable] FROM [%s].[INFORMATION_SCHEMA].[VIEWS] WHERE [TABLE_SCHEMA] = '%s' ";

    /**
     * SqlServer 查询该数据库下所有表
     */
    public static final String SQL_SERVER_QUERY_ALL_TABLE = "SELECT [TABLE_SCHEMA] AS [schema],[TABLE_NAME] AS [name] FROM [%s].[INFORMATION_SCHEMA].[TABLES] WHERE [TABLE_TYPE] = 'BASE TABLE' AND [TABLE_SCHEMA] = '%s' ORDER BY [TABLE_NAME]";

    /**
     * SqlServer 查询该数据库下所有字段
     */
    public static final String SQL_SERVER_QUERY_ALL_COLUMN = "SELECT [TABLE_NAME] AS [tableName],[COLUMN_NAME] AS [name],[ORDINAL_POSITION] AS [ordinalPosition],[COLUMN_DEFAULT] AS [defaultValue],[IS_NULLABLE] AS [isNullable],[DATA_TYPE] AS [type],ISNULL([CHARACTER_MAXIMUM_LENGTH],[NUMERIC_PRECISION]) AS [length],[NUMERIC_SCALE] AS [scale] FROM [%s].[INFORMATION_SCHEMA].[COLUMNS] WHERE [TABLE_SCHEMA] = '%s'";

    /**
     * SqlServer 查询该数据库下所有索引
     */
    public static final String SQL_SERVER_QUERY_ALL_INDEX = "SELECT [S].[name] AS [schemaName], [O].[name] AS [tableName],[I].[name],[I].[type_desc] AS [indexType],[I].[is_unique] AS [nonUnique],[IC].[index_column_id] AS [sort],[C].[name] AS [columnName] " +
            "FROM [:#].[sys].[index_columns] [IC] " +
            "LEFT JOIN [:#].[sys].[sysobjects] [O] ON [IC].[object_id] = [O].[id] " +
            "LEFT JOIN [:#].[sys].[indexes] [I] ON [IC].[object_id] = [I].[object_id] AND [IC].[index_id] = [I].[index_id] " +
            "LEFT JOIN [:#].[sys].[schemas] [S] ON [O].[uid] = [S].[schema_id] " +
            "LEFT JOIN [:#].[sys].[columns] [C] ON [IC].[column_id] = [C].[column_id] AND [IC].[object_id] = [C].[object_id] " +
            "WHERE [O].[xtype] = 'U' AND [S].[name] = '%s' order by [I].[name]";

    /**
     * Oracle 查询所有库
     */
    public static final String ORACLE_QUERY_ALL_DATABASE = "SELECT name FROM v$database";

    /**
     * PostgreSql 查询所有库
     */
    public static final String POSTGRE_SQL_QUERY_ALL_DATABASE = "SELECT datname FROM pg_database";
}
