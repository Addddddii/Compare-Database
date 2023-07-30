package com.example.compare_db.context;

import com.example.compare_db.constant.CompareResultEnum;
import com.example.compare_db.entity.structure.Table;
import lombok.Data;

import java.util.List;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Data
public class MatchedTableItem extends AbstractMatchedItem {
	/**
	 * 左边表
	 */
	private Table left;

	/**
	 * 右边表
	 */
	private Table right;

	/***
	 * 索引
	 */
	private List<MatchedIndexItem> matchedIndexItemList;

	private CompareResultEnum result;

	private List<MatchedColumnItem> matchedColumnItemList;

	@Override
	public CompareResultEnum compare() {
		if (left == null) {
			result = CompareResultEnum.LEFT_NOT_EXIST;
			return result;
		}
		if (right == null) {
			result = CompareResultEnum.RIGHT_NOT_EXIST;
			return result;
		}
		result = CompareResultEnum.EQUAL;
		
		for (MatchedColumnItem matchedColumnItem: matchedColumnItemList){
			if(CompareResultEnum.EQUAL != matchedColumnItem.compare()){
				result = CompareResultEnum.NOT_EQUAL;
			}
		}
		
		for (MatchedIndexItem matchedIndexItem : matchedIndexItemList) {
			if (CompareResultEnum.EQUAL != matchedIndexItem.compare()) {
				result = CompareResultEnum.NOT_EQUAL;
			}
		}
		return result;
	}

}
