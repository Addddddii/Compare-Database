package com.example.compare_db.service;


import com.example.compare_db.context.*;
import com.example.compare_db.entity.*;
import com.example.compare_db.entity.structure.Column;
import com.example.compare_db.entity.structure.Index;
import com.example.compare_db.entity.structure.Table;
import com.example.compare_db.entity.structure.View;
import com.example.compare_db.output.Display;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Data
@Service
public class DatabaseComparator {

	@Autowired
	private Display Display;


	public void compare(DataBase left, DataBase right) {
		MatchedDatabaseContext context = matchedDatabase(left, right);

		for (MatchedTableItem matchedTableItem : context.getMatchedTableItemList()) {
			Display.show(matchedTableItem, true);
		}

		for (MatchedViewItem matchedViewItem : context.getMatchedViewItemList()) {
			Display.show(matchedViewItem, true);
		}
	}

	/**
	 * 进行匹配 库索引和表
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	private MatchedDatabaseContext matchedDatabase(DataBase left, DataBase right) {
		MatchedDatabaseContext context = new MatchedDatabaseContext();

		MatchedDatabaseItem matchedDatabaseItem = new MatchedDatabaseItem();
		matchedDatabaseItem.setLeft(left);
		matchedDatabaseItem.setRight(right);
		context.setDatabase(matchedDatabaseItem);
		//已经匹配成功的表
		List<MatchedTableItem> matchedTableItemList = new ArrayList<>();

		// 匹配表
		Set<Table> rightMatchedTables = new HashSet<>();
		for (Table leftTable : left.getTableList()) {
			MatchedTableItem matchedTableItem = null;
			for (Table rightTable : right.getTableList()) {
				if (StringUtils.equalsIgnoreCase(leftTable.getName(), rightTable.getName())) {
					matchedTableItem = new MatchedTableItem();
					matchedTableItem.setLeft(leftTable);
					matchedTableItem.setRight(rightTable);
					rightMatchedTables.add(rightTable);
					break;
				}
			}
			if (matchedTableItem == null) {// 未匹配上
				matchedTableItem = new MatchedTableItem();
				matchedTableItem.setLeft(leftTable);
			}

			matchedTableItemList.add(matchedTableItem);
			matchedTable(matchedTableItem);// 匹配表格
		}
		for (Table rightTable : right.getTableList()) {
			if (!rightMatchedTables.contains(rightTable)) {
				MatchedTableItem matchedTableItem = new MatchedTableItem();
				matchedTableItem.setRight(rightTable);
				matchedTableItemList.add(matchedTableItem);
			}
		}
		context.setMatchedTableItemList(matchedTableItemList);

		// 匹配 view
		List<MatchedViewItem> matchedViewItemList = new ArrayList<>();
		Set<View> rightMatchedViewSet = new HashSet<>();
		for (View leftView : left.getViewList()) {
			MatchedViewItem viewItme = null;
			for (View rightView : right.getViewList()) {
				if (StringUtils.equalsIgnoreCase(leftView.getName(), rightView.getName())) {
					viewItme = new MatchedViewItem();
					viewItme.setLeft(leftView);
					viewItme.setRight(rightView);
					rightMatchedViewSet.add(rightView);
					break;
				}
			}
			if (viewItme == null) {
				viewItme = new MatchedViewItem();
				viewItme.setLeft(leftView);
			}
		}

		for (View rightView : right.getViewList()) {
			if (!rightMatchedTables.contains(rightView)) {
				MatchedViewItem matchedViewItem = new MatchedViewItem();
				matchedViewItem.setRight(rightView);
				matchedViewItemList.add(matchedViewItem);
			}
		}
		context.setMatchedViewItemList(matchedViewItemList);
		return context;
	}

	/***
	 * 匹配表格内的列和索引
	 * 
	 * @param tableItme
	 */
	private void matchedTable(MatchedTableItem tableItme) {
		if (tableItme.getLeft() == null || tableItme.getRight() == null) {
			return;
		}

		// 匹配列
		List<MatchedColumnItem> matchedColumnItemList = matchedColumns(tableItme.getLeft().getColumnList(),
				tableItme.getRight().getColumnList(), false);
		tableItme.setMatchedColumnItemList(matchedColumnItemList);

		// 匹配索引
		List<MatchedIndexItem> matchedIndexItemList = matchedIndexItemList(tableItme.getLeft().getIndexList(),
				tableItme.getRight().getIndexList());
		tableItme.setMatchedIndexItemList(matchedIndexItemList);
	}

	private List<MatchedIndexItem> matchedIndexItemList(List<Index> leftIndexList, List<Index> rightIndexList) {
		List<MatchedIndexItem> matchedIndexItemList = new ArrayList<>();
		if (leftIndexList == null) {
			leftIndexList = new ArrayList<>();
		}
		if (rightIndexList == null) {
			rightIndexList = new ArrayList<>();
		}

		List<Index> matchedRightIndex = new ArrayList<>();
		for (Index leftIndex : leftIndexList) {
			MatchedIndexItem matchedIndexItem = null;
			for (Index rightIndex : rightIndexList) {
				if (StringUtils.equals(leftIndex.getName(), rightIndex.getName())) {
					matchedIndexItem = new MatchedIndexItem();
					matchedIndexItem.setLeft(leftIndex);
					matchedIndexItem.setRight(rightIndex);
					matchedRightIndex.add(rightIndex);
				}
			}
			if (matchedIndexItem == null) {
				matchedIndexItem = new MatchedIndexItem();
				matchedIndexItem.setLeft(leftIndex);
			}
			matchedIndexItemList.add(matchedIndexItem);
		}
		for (Index rightIndex : rightIndexList) {
			if (!matchedRightIndex.contains(rightIndex)) {
				MatchedIndexItem matchedIndexItem = new MatchedIndexItem();
				matchedIndexItem.setRight(rightIndex);
				matchedIndexItemList.add(matchedIndexItem);
			}
		}

		return matchedIndexItemList;
	}

	private List<MatchedColumnItem> matchedColumns(List<Column> leftColumns, List<Column> rightColumns,
												   boolean sequence) {
		if (!sequence) {
			return matchedColumnsIgnoreSequence(leftColumns, rightColumns);
		}
		return matchedColumnsRequireSequence(leftColumns, rightColumns);
	}

	/***
	 * 匹配列 关心列顺序
	 * 
	 * @param leftColumns
	 * @param rightColumns
	 * @return
	 */
	private List<MatchedColumnItem> matchedColumnsRequireSequence(List<Column> leftColumns, List<Column> rightColumns) {
		throw new RuntimeException("此方法未实现");
	}

	/***
	 * 匹配列忽略顺序
	 * 
	 * @param leftColumns
	 * @param rightColumns
	 * @return
	 */
	private List<MatchedColumnItem> matchedColumnsIgnoreSequence(List<Column> leftColumns, List<Column> rightColumns) {
		List<MatchedColumnItem> matchedColumnItemList = new ArrayList<>();
		if (leftColumns == null) {
			leftColumns = new ArrayList<>();
		}
		if (rightColumns == null) {
			rightColumns = new ArrayList<>();
		}
		List<Column> matchedRightColumn = new ArrayList<>();
		for (Column leftColumn : leftColumns) {
			MatchedColumnItem columnItem = null;
			for (Column rightColumn : rightColumns) {
				if (StringUtils.equals(leftColumn.getName(), rightColumn.getName())) {
					columnItem = new MatchedColumnItem();
					columnItem.setLeft(leftColumn);
					columnItem.setRight(rightColumn);
					matchedRightColumn.add(rightColumn);
				}
			}
			if (columnItem == null) {
				columnItem = new MatchedColumnItem();
				columnItem.setLeft(leftColumn);
			}
			matchedColumnItemList.add(columnItem);
		}
		for (Column rightColumn : rightColumns) {
			if (!matchedRightColumn.contains(rightColumn)) {
				MatchedColumnItem matchedColumnItem = new MatchedColumnItem();
				matchedColumnItem.setRight(rightColumn);
				matchedColumnItemList.add(matchedColumnItem);
			}
		}
		return matchedColumnItemList;
	}
}
