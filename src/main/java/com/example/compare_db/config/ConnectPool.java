package com.example.compare_db.config;

import com.example.compare_db.utils.CloseUtils;

import java.sql.Connection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据库连接池
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
public class ConnectPool {

    private static volatile ConnectPool INSTANCE;

    private static final Map<String, Connection> CONNECTION_MAP = new ConcurrentHashMap<>(2);

    private ConnectPool(){

    }

    public static ConnectPool getInstance(){
        if (INSTANCE == null){
            synchronized (ConnectPool.class){
                if (INSTANCE == null){
                    INSTANCE = new ConnectPool();
                }
            }
        }
        return INSTANCE;
    }

    public static void clean(){
        Set<String> keySet = CONNECTION_MAP.keySet();
        for (String key : keySet) {
            destroy(key);
        }
    }

    public void addOrReturn(String key, Connection connection){
        if (!CONNECTION_MAP.containsKey(key)){
            CONNECTION_MAP.put(key,connection);
        }
    }

    public Connection use(String key){
        Connection connection = null;
        if (CONNECTION_MAP.containsKey(key)){
            connection = CONNECTION_MAP.get(key);
        }
        CONNECTION_MAP.remove(key);
        return connection;
    }

    public void destroy(String ... keys) {
        for (String key : keys) {
            if (CONNECTION_MAP.containsKey(key)){
                Connection connection = CONNECTION_MAP.get(key);
                if (connection != null){
                    CloseUtils.close(connection);
                }
                CONNECTION_MAP.remove(key);
            }
        }
    }

    private static void destroy(String key) {
        if (CONNECTION_MAP.containsKey(key)){
            Connection connection = CONNECTION_MAP.get(key);
            if (connection != null){
                CloseUtils.close(connection);
            }
            CONNECTION_MAP.remove(key);
        }
    }
}
