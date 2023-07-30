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
public class OracleConfig extends BaseDBConfig {

    /**
     * 初始数据库
     */
    @Getter
    @Setter
    private String serverName;

    public OracleConfig(String host, String port, String user, String password, String serverName) {
        super(DataBaseEnum.Oracle, host, port, user, password);
        this.serverName = serverName;
    }

    @Override
    public String getUrl() {
        return String.format(Constant.ORACLE_URL,this.getHost(),this.getPort(),this.getServerName());
    }
}
