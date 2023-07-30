package com.example.compare_db.context;

import lombok.Data;

import java.util.List;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Data
public class MatchedDatabaseContext {
	
	private MatchedDatabaseItem database;
	
	private List<MatchedTableItem> matchedTableItemList;
	
	private List<MatchedViewItem> matchedViewItemList;

}
