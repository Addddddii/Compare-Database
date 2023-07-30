package com.example.compare_db.utils;

import com.example.compare_db.constant.constant.Constant;
import com.example.compare_db.constant.constant.MessageConstant;
import com.example.compare_db.entity.connection.BaseDBConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import java.util.regex.Pattern;

/**
 * 参数校验工具类
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
public class VerifyUtils {

    /**
     * 校验数据库地址是否合法localhost或ip地址
     * @param  host
     * @return Boolean
     */
    public static Boolean verifyHost(String host){
        return StringUtils.equals(Constant.LOCALHOST,host) ? true : Pattern.compile(Constant.IP_REG).matcher(host).matches();
    }

    /**
     * 校验数据库config相关属性不能为空
     * @param  t
     * @return Boolean
     */
    public static <T extends BaseDBConfig> void verifyConfig(T t) throws Exception{
        Assert.isTrue(StringUtils.isNotBlank(t.getPort()), MessageConstant.PORT_NOT_NULL);
        Assert.isTrue(StringUtils.isNotBlank(t.getUser()), MessageConstant.USERNAME_NOT_NULL);
        Assert.isTrue(StringUtils.isNotBlank(t.getPassword()), MessageConstant.PASSWORD_NOT_NULL);
    }


}
