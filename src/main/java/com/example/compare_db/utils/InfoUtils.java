package com.example.compare_db.utils;

import javafx.scene.control.Alert;

/**
 * 弹窗工具类
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
public class InfoUtils {

    private static Alert alert;

    public static void info(String info){
        alert = new Alert(Alert.AlertType.INFORMATION, info);
        alert.setHeaderText(null);
        alert.setTitle("提示");
        alert.showAndWait();
    }

    public static void warning(String info){
        alert = new Alert(Alert.AlertType.WARNING, info);
        alert.setTitle("警告");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void confirmation(String info){
        alert = new Alert(Alert.AlertType.CONFIRMATION, info);
        alert.setTitle("确认");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void error(String info){
        alert = new Alert(Alert.AlertType.ERROR, info);
        alert.setTitle("错误");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
