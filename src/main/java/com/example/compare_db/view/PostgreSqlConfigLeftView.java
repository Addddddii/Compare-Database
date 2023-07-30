package com.example.compare_db.view;

import com.example.compare_db.constant.constant.Constant;
import com.example.compare_db.controller.PostgreSqlConfigLeftController;
import de.felixroske.jfxsupport.FXMLView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@FXMLView("/view/PostgreSqlConfigLeftView.fxml")
public class PostgreSqlConfigLeftView extends BaseConfigView {

    @Autowired
    private PostgreSqlConfigLeftController controller;

    @Override
    public boolean isCurrentView(String viewName) {
        return Constant.POSTGRE_SQL_CONFIG_LEFT_VIEW.equals(viewName);
    }

    public void init(){
        controller.init();
    }
}
