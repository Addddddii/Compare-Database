package com.example.compare_db.utils;

import com.example.compare_db.config.ConnectPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC查询工具类
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
public class JdbcUtils {

    private static ConnectPool connectPool = ConnectPool.getInstance();

    public static <T> T query(String key, String sql, Class<T> clazz) throws Exception{
        T bean;
        Connection connection = connectPool.use(key);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            bean = ConnectionUtils.resultToObj(resultSet,clazz);
        }finally {
            CloseUtils.close(preparedStatement);
            CloseUtils.close(resultSet);
            connectPool.addOrReturn(key,connection);
        }
        return bean;
    }

    public static <T> List<T> queryToList(String key, String sql, Class<T> clazz) throws Exception{
        List<T> beanList = new ArrayList<T>();
        Connection connection = connectPool.use(key);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            beanList = ConnectionUtils.resultToList(resultSet,clazz);
        }finally {
            CloseUtils.close(preparedStatement);
            CloseUtils.close(resultSet);
            connectPool.addOrReturn(key,connection);
        }
        return beanList;
    }

    public static <T> T queryByArgs(String key, String sql, Class<T> clazz, Object... args) throws Exception{
        T bean;
        Connection connection = connectPool.use(key);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = handlePreparedStatement(connection, sql, args);
            resultSet = preparedStatement.executeQuery();
            bean = ConnectionUtils.resultToObj(resultSet,clazz);
        }finally {
            CloseUtils.close(preparedStatement);
            CloseUtils.close(resultSet);
            connectPool.addOrReturn(key,connection);
        }
        return bean;
    }

    public static <T> List<T> queryToListByArgs(String key, String sql, Class<T> clazz, Object... args) throws Exception{
        List<T> beanList;
        Connection connection = connectPool.use(key);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = handlePreparedStatement(connection, sql, args);
            resultSet = preparedStatement.executeQuery();
            beanList = ConnectionUtils.resultToList(resultSet,clazz);
        }finally {
            CloseUtils.close(preparedStatement);
            CloseUtils.close(resultSet);
            connectPool.addOrReturn(key,connection);
        }
        return beanList;
    }

    /**
     * 预处理SQL,获取PreparedStatement对象，并对SQL参数进行赋值操作
     *
     * @param connection 数据库连接对象
     * @param sql        sql语句
     * @param args 实际参数
     * @return 返回PreparedStatement对象
     * @throws Exception SQL异常
     */
    private static PreparedStatement handlePreparedStatement(Connection connection, String sql, Object... args) throws Exception {
        PreparedStatement statement = connection.prepareStatement(sql);
        int parameterCount = statement.getParameterMetaData().getParameterCount();
        if (parameterCount != 0 && args != null && args.length == parameterCount) {
            for (int i = 0; i < parameterCount; i++) {
                statement.setObject(i + 1, args[i]);
            }
        }

        return statement;
    }

}
