package com.example.compare_db.utils;

import com.example.compare_db.config.ConnectPool;
import com.example.compare_db.constant.constant.MessageConstant;
import com.example.compare_db.entity.connection.BaseDBConfig;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库连接工具类
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
public class ConnectionUtils {

    /**
     * 通过不同的数据库属性获取jdbc连接 并添加到连接池
     * @param <T> extends BaseDBConnection
     */
    public static <T extends BaseDBConfig> void init(T t,String key) throws Exception{
        //校验参数
        VerifyUtils.verifyConfig(t);
        ConnectPool instance = ConnectPool.getInstance();
        try {
            Class.forName(t.getDataBaseEnum().getDriver());
            Connection connection = DriverManager.getConnection(t.getUrl(), t.getUser(), t.getPassword());
            instance.addOrReturn(key,connection);
        } catch (Exception e){
            throw new Exception(MessageConstant.CONNECT_FAIL + e.getMessage());
        }

    }

    /**
     * 把JDBC的ResultSet转换为对象
     */
    public static <T> T resultToObj(ResultSet resultSet, Class<T> clazz) throws Exception{
        T bean = clazz.newInstance();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int count = metaData.getColumnCount();
        Map<String,Object> sourceMap = new HashMap<>();
        while (resultSet.next()){
            for (int i = 0; i < count; i++) {
                sourceMap.put(metaData.getColumnLabel(i),String.valueOf(resultSet.getObject(i)));
            }
            bean = mapToObj(sourceMap, clazz);
        }
        return bean;
    }

    /**
     * 把JDBC的ResultSet转换为对象集合
     */
    public static <T> List<T> resultToList(ResultSet resultSet, Class<T> clazz) throws Exception{
        List<T> beanList = new ArrayList<T>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int count = metaData.getColumnCount();
        Map<String,String> sourceMap = new HashMap<>();
        while (resultSet.next()){
            for (int i = 1; i <= count; i++) {
                //适配多个数据库通过ResultSet获取Object后使用Json工具类结果封装为String类型
                sourceMap.put(metaData.getColumnLabel(i), String.valueOf(resultSet.getObject(i)));
            }
            T bean = mapToObj(sourceMap, clazz);
            beanList.add(bean);
        }
        return beanList;
    }

    /**
     * 把sourceMap转为targetBean
     * @param source source
     * @param clazz target
     * @param <T> 返回值类型
     * @return 返回值
     * @throws Exception newInstance可能会抛出的异常
     */
    public static <T> T mapToObj(Map source, Class<T> clazz) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        T obj = clazz.newInstance();
        for (Field field : fields) {
            Object val = source.get(field.getName());
            if (val != null) {
                field.setAccessible(true);
                field.set(obj, val);
            }
        }
        return obj;
    }
}
