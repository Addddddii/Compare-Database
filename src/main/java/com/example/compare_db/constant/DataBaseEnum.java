package com.example.compare_db.constant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 支持的数据库类型
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Getter
@AllArgsConstructor
public enum DataBaseEnum {

    /**
     * MySql
     */
    MySql("1","Mysql","com.mysql.cj.jdbc.Driver","MySqlConfig"),

    /**
     * SqlServer
     */
    SqlServer("2","SqlServer","com.microsoft.sqlserver.jdbc.SQLServerDriver","SqlServerConfig"),

    /**
     * Oracle
     */
    Oracle("3","Oracle(开发中)","oracle.jdbc.driver.OracleDriver","OracleConfig"),

    /**
     * PostgreSql
     */
    PostgreSql("4","PostgreSql(开发中)","org.postgresql.Driver","PostgreSqlConfig");

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String describe;

    /**
     * 数据库驱动
     */
    private String driver;

    /**
     * 视图
     */
    private String view;

    /**
     * 获取所有支持的数据库类型描述
     */
    public static ObservableList<String> getAllDescribe(){
        List<String> describeList = Arrays.stream(values()).map(DataBaseEnum::getDescribe).collect(Collectors.toList());
        return FXCollections.observableArrayList(describeList);
    }

}
