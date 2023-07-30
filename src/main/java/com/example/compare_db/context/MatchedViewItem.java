package com.example.compare_db.context;

import com.example.compare_db.constant.CompareResultEnum;
import com.example.compare_db.entity.structure.View;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Data
public class MatchedViewItem extends AbstractMatchedItem {


	/**
	 * 左边表
	 */
	private View left;

	/**
	 * 右边表
	 */
	private View right;

	private CompareResultEnum result;

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
		result = CompareResultEnum.NOT_EQUAL;
		if (StringUtils.equals(left.getDefinition(), right.getDefinition())) {
			result = CompareResultEnum.EQUAL;
		}
		return result;
	}
	
	/**
	 * @return the result
	 */
	public CompareResultEnum getResult() {
		return result;
	}


}
