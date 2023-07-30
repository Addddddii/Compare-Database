package com.example.compare_db.context;

import com.example.compare_db.constant.CompareResultEnum;
import com.example.compare_db.entity.structure.Column;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 匹配好的列
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 *
 */
@Data
public class MatchedColumnItem extends AbstractMatchedItem {

	private Column left;

	private Column right;

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
		if (StringUtils.equals(left.getType(), right.getType())
				&& StringUtils.equals(left.getComment(), right.getComment())
				&& StringUtils.equals(left.getDefaultValue(), right.getDefaultValue())
				&& StringUtils.equals(left.getLength() ,right.getLength())
				&& StringUtils.equals(left.getScale(), right.getScale())) {
			result = CompareResultEnum.EQUAL;
		}else{
			System.out.println(left);
			System.out.println(right); 
		}
		return result;
	}

	private boolean equals(String left, String right) {
		if(left == null && left == null){
			return true;
		}
		
		return left != null && left.equals(right);
	}

}
