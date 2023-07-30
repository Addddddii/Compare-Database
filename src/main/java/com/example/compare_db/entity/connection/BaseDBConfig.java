package com.example.compare_db.entity.connection;

import com.example.compare_db.constant.DataBaseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据库连接父类
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Data
@NoArgsConstructor
public abstract class BaseDBConfig {

    /**
     * 数据库枚举类型 由具体实现类赋值
     */
    private DataBaseEnum dataBaseEnum;

    /**
     * url 由具体实现类赋值
     */
    private String url;

    /**
     * host
     */
    private String host;

    /**
     * 端口
     */
    private String port;

    /**
     *用户
     */
    private String user;

    /**
     * 密码
     */
    private String password;

    public BaseDBConfig(DataBaseEnum dataBaseEnum, String host, String port, String user, String password) {
        this.dataBaseEnum = dataBaseEnum;
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
    }
}
