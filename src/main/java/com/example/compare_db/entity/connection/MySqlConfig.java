package com.example.compare_db.entity.connection;

import com.example.compare_db.constant.constant.Constant;
import com.example.compare_db.constant.DataBaseEnum;
import lombok.NoArgsConstructor;

/**
 * MySql连接配置
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@NoArgsConstructor
public class MySqlConfig extends BaseDBConfig {

    public MySqlConfig(String host, String port, String user, String password) {
        super(DataBaseEnum.MySql,host, port, user, password);
    }

    @Override
    public String getUrl() {
        return String.format(Constant.MYSQL_URL,this.getHost(),this.getPort());
    }
}
