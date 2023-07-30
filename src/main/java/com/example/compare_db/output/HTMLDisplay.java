package com.example.compare_db.output;

import com.example.compare_db.constant.CompareResultEnum;
import com.example.compare_db.constant.constant.Constant;
import com.example.compare_db.context.MatchedColumnItem;
import com.example.compare_db.context.MatchedTableItem;
import com.example.compare_db.context.MatchedViewItem;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Component
public class HTMLDisplay implements Display {
	
	StringBuffer content = new StringBuffer("<style type=\"text/css\">  \r\n" + 
			"table.gridtable {  \r\n" + 
			"    font-family: verdana,arial,sans-serif;  \r\n" + 
			"    font-size:11px;  \r\n" + 
			"    color:#333333;  \r\n" + 
			"    border-width: 1px;  \r\n" + 
			"    border-color: #666666;  \r\n" + 
			"    border-collapse: collapse;  \r\n" + 
			"    width:100%;  \r\n "
			+ "margin:  5px 5px;                        \r\n"+	
			"}  \r\n" + 
			"table.gridtable th {  \r\n" + 
			"    border-width: 1px;  \r\n" + 
			"    padding: 8px;  \r\n" + 
			"    border-style: solid;  \r\n" + 
			"    border-color: #666666;  \r\n" + 
			"    background-color: #dedede;  \r\n" + 
			"}  \r\n" + 
			"table.gridtable td {  \r\n" + 
			"    border-width: 1px;  \r\n" + 
			"    padding: 8px;  \r\n" + 
			"    border-style: solid;  \r\n" + 
			"    border-color: #666666;  \r\n" + 
			"    background-color: #ffffff;  \r\n" + 
			"}  \r\n" + 
			"</style> <h1 style=\"text-align: center;\">不相同的表结构<h1> ");

	@Override
	public void show(MatchedTableItem tableItem, boolean ignoreEqual) {
		if(ignoreEqual && tableItem.compare() == CompareResultEnum.EQUAL){
			return ;
		}
		
		if(tableItem.getResult() == CompareResultEnum.LEFT_NOT_EXIST){
			String table = 
					  "<table class=\"gridtable\">"
					+ "		<tr>"
					+ "			<th style=\"width: 45%\"></th>"
					+ "			<th>左不存在</th>"
					+ "			<th style=\"width: 45%\">"+ tableItem.getRight().getName()+"</th>"
					+ "		</tr>"
					+ "</talbe>";
			content.append(table);
		}else if(tableItem.getResult() == CompareResultEnum.RIGHT_NOT_EXIST) {
			String table = 
					  "<table class=\"gridtable\">"
					+ "		<tr>"
					+ "			<th style=\"width: 45%\">"+ tableItem.getLeft().getName()+"</th>"
					+ "			<th>右不存在</th>"
					+ "			<th style=\"width: 45%\"></th>"
					+ "		</tr>"
					+ "</talbe>";
			content.append(table);
		}else if(tableItem.getResult() == CompareResultEnum.EQUAL || tableItem.getResult() == CompareResultEnum.NOT_EQUAL) {
			String table = 
					  "<table class=\"gridtable\">"
					+ "		<tr>"
					+ "			<th style=\"width: 45%\" >"+ tableItem.getLeft().getName()+"</th>"
					+ "			<th>"+(tableItem.getResult() == CompareResultEnum.EQUAL? "相同":"不相同")+"</th>"
					+ "			<th style=\"width: 45%\">"+ tableItem.getRight().getName()+"</th>"
					+ "		</tr>";
			content.append(table);
			for(MatchedColumnItem column: tableItem.getMatchedColumnItemList()){
				content.append(markColumn(column,ignoreEqual));
			}
			content.append("</table>");
		}
	}

	private String markColumn(MatchedColumnItem column, boolean ignoreEqual) {
		if(ignoreEqual && column.compare() == CompareResultEnum.EQUAL){
			return "";
		}
		if(column.getResult() == CompareResultEnum.LEFT_NOT_EXIST){
			String tr = "<tr>"
					+ "			<td></td>"
					+ "			<td>左不存在</td>"
					+ "			<td>"+String.format("%s %s(%s) %s",column.getRight().getName(),column.getRight().getType(),column.getRight().getLength(), Constant.YES.equals(column.getRight().getIsNullable()) ? " NOT NULL ":" NULL")+"</td>"
					+ "	</tr>";
			return (tr);
		}else if (column.getResult() == CompareResultEnum.RIGHT_NOT_EXIST){
			String tr = "<tr>"
					+ "			<td>"+String.format("%s %s(%s) %s",column.getLeft().getName(),column.getLeft().getType(),column.getLeft().getLength(),Constant.YES.equals(column.getLeft().getIsNullable()) ? " NOT NULL ":" NULL")+"</td>"
					+ "			<td>右不存在</td>"
					+ "			<td></td>"
					+ "	</tr>";
			return  (tr);
		}else if (column.getResult() == CompareResultEnum.EQUAL || column.getResult() == CompareResultEnum.NOT_EQUAL){
			String tr = "<tr>"
					+ "			<td>"+String.format("%s %s(%s,%s) %s %s",column.getLeft().getName(),column.getLeft().getType(),column.getLeft().getLength(),column.getLeft().getScale(),Constant.YES.equals(column.getLeft().getIsNullable()) ?" NOT NULL ":" NULL",column.getLeft().getComment())+"</td>"
					+ "			<td>"+(column.getResult() == CompareResultEnum.EQUAL? "相同":"不相同") +"</td>"
					+ "			<td>"+String.format("%s %s(%s,%s) %s %s",column.getRight().getName(),column.getRight().getType(),column.getRight().getLength(),column.getRight().getScale(),Constant.YES.equals(column.getRight().getIsNullable()) ?" NOT NULL ":" NULL",column.getRight().getComment())+"</td>"
					+ "	</tr>";
			return  (tr);
		}
		return "";
	}

	@Override
	public void show(MatchedViewItem viewItme, boolean ignoreEqual) {
		if(ignoreEqual && viewItme.getResult() == CompareResultEnum.EQUAL){
			return ;
		}
		
		
	}

	@Override
	public Object getContent() {
		return content;
	}
}
