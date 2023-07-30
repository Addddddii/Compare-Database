package com.example.compare_db.constant.constant;

/**
 * 基础常量
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
public class Constant {

    public static final String LEFT = "left";

    public static final String RIGHT = "right";

    public static final String YES = "YES";

    public static final String NO = "NO";

    public static final String IP_REG = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";

    public static final String LOCALHOST = "localhost";

    /**
     * 左视图拼接
     */
    public static final String LEFT_VIEW = "LeftView";

    /**
     * 右视图拼接
     */
    public static final String RIGHT_VIEW = "RightView";

    /**
     * MySql 连接URL
     */
    public static final String MYSQL_URL = "jdbc:mysql://%s:%s?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";

    /**
     * MySql 左视图
     */
    public static final String MYSQL_CONFIG_LEFT_VIEW = "MySqlConfigLeftView";

    /**
     * MySql 右视图
     */
    public static final String MYSQL_CONFIG_RIGHT_VIEW = "MySqlConfigRightView";

    /**
     * SqlServer 连接URL
     */
    public static final String SQL_SERVER_URL =  "jdbc:sqlserver://%s:%s;DatabaseName=%s;characterEncoding=UTF-8;encrypt=false";

    /**
     * SqlServer 左视图
     */
    public static final String SQL_SERVER_CONFIG_LEFT_VIEW = "SqlServerConfigLeftView";

    /**
     * SqlServer 右视图
     */
    public static final String SQL_SERVER_CONFIG_RIGHT_VIEW = "SqlServerConfigRightView";

    /**
     * SqlServer 初始化数据库
     */
    public static final String SQL_SERVER_INIT_DATABASE = "master";

    /**
     * Oracle 连接URL
     */
    public static final String ORACLE_URL =  "jdbc:oracle:thin:@%s:%s:%s";

    /**
     * Oracle 左视图
     */
    public static final String ORACLE_CONFIG_LEFT_VIEW = "OracleConfigLeftView";

    /**
     * Oracle 右视图
     */
    public static final String ORACLE_CONFIG_RIGHT_VIEW = "OracleConfigRightView";

    /**
     * Oracle 初始化数据库
     */
    public static final String ORACLE_INIT_SERVER_NAME = "ORCL";


    /**
     * PostgreSql 连接URL
     */
    public static final String POSTGRE_SQL_URL =  "jdbc:postgresql://%s:%s/%s";

    /**
     * PostgreSql 左视图
     */
    public static final String POSTGRE_SQL_CONFIG_LEFT_VIEW = "PostgreSqlConfigLeftView";

    /**
     * PostgreSql 右视图
     */
    public static final String POSTGRE_SQL_CONFIG_RIGHT_VIEW = "PostgreSqlConfigRightView";

    /**
     * PostgreSql 初始化数据库
     */
    public static final String POSTGRE_SQL_INIT_DATABASE = "postgres";

}
