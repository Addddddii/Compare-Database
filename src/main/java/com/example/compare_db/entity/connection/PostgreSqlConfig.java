package com.example.compare_db.entity.connection;

import com.example.compare_db.constant.constant.Constant;
import com.example.compare_db.constant.DataBaseEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SqlServer连接配置
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@NoArgsConstructor
public class PostgreSqlConfig extends BaseDBConfig {

    /**
     * 初始数据库
     */
    @Getter
    @Setter
    private String initDataBase;

    public PostgreSqlConfig(String host, String port, String user, String password, String initDataBase) {
        super(DataBaseEnum.SqlServer, host, port, user, password);
        this.initDataBase = initDataBase;
    }

    @Override
    public String getUrl() {
        return String.format(Constant.POSTGRE_SQL_URL,this.getHost(),this.getPort(),this.getInitDataBase());
    }
}
