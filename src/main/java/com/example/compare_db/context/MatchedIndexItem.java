package com.example.compare_db.context;

import com.example.compare_db.constant.CompareResultEnum;
import com.example.compare_db.entity.structure.Index;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Data
public class MatchedIndexItem extends AbstractMatchedItem {
	/**
	 * 左边
	 */
	private Index left;

	/**
	 * 右边
	 */
	private Index right;

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

		if (StringUtils.equals(left.getIndexType(), right.getIndexType())
				&& StringUtils.equals(left.getIndexMethod(), left.getIndexMethod())
				&& left.getColumnList().equals(right.getColumnList())) {
			result = CompareResultEnum.EQUAL;
		}
		return result;
	}

}
