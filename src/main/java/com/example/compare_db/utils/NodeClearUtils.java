package com.example.compare_db.utils;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML节点清空工具类
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
public class NodeClearUtils {

    public static <T extends TextField> void clear(T ... t){
        for (T textField : t) {
            if (textField != null){
                textField.clear();
            }
        }
    }

    public static void clear(ComboBox ... comboBoxes){
        for (ComboBox comboBox : comboBoxes) {
            if (comboBox != null){
                ObservableList items = comboBox.getItems();
                if (items != null) {
                    items.clear();
                }
            }
        }
    }
}
