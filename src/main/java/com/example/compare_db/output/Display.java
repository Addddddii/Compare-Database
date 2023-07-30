package com.example.compare_db.output;

import com.example.compare_db.context.MatchedTableItem;
import com.example.compare_db.context.MatchedViewItem;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
public interface Display {
	
	/**
	 * 展示对比两个表格
	 * @param tableItme
	 * @param ignoreEqual
	 */
	public void show(MatchedTableItem tableItem, boolean ignoreEqual);

	
	/**
	 * 展示对比两个视图
	 * @param indexItme
	 * @param ignoreEqual
	 */
	public void show(MatchedViewItem viewItem, boolean ignoreEqual);


	public Object getContent();
}
