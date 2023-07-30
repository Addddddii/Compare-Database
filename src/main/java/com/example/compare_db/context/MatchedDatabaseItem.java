package com.example.compare_db.context;

import com.example.compare_db.entity.DataBase;
import lombok.Data;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Data
public class MatchedDatabaseItem   {
	
	/**
	 * 左边数据库
	 */
	private DataBase left;
	
	/**
	 * 右边数据库
	 */
	private DataBase right;

}
