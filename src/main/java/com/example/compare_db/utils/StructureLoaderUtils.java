package com.example.compare_db.utils;

import com.example.compare_db.entity.structure.Column;
import com.example.compare_db.entity.structure.Table;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据库结构构建工具类
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
public class StructureLoaderUtils {

    public static void processTableMap(Map<String, Table> tableMap, List<Column> columnList){
        Map<String, List<Column>> columnListMap = getColumnListMapByColumnList(columnList);
        for (Table value : tableMap.values()) {
            List<Column> columnListNew = columnListMap.get(value.getName());
            if (!CollectionUtils.isEmpty(columnListNew)){
                value.setColumnList(columnListNew);
            }
        }
    }

    private static Map<String, List<Column>> getColumnListMapByColumnList(List<Column> columnList){
        return columnList.stream().collect(Collectors.groupingBy(Column::getTableName));
    }
}
