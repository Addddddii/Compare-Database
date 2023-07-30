package com.example.compare_db.view;

import com.example.compare_db.constant.constant.Constant;
import com.example.compare_db.controller.PostgreSqlConfigRightController;
import de.felixroske.jfxsupport.FXMLView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@FXMLView("/view/PostgreSqlConfigRightView.fxml")
public class PostgreSqlConfigRightView extends BaseConfigView {

    @Autowired
    private PostgreSqlConfigRightController controller;

    @Override
    public boolean isCurrentView(String viewName) {
        return Constant.POSTGRE_SQL_CONFIG_RIGHT_VIEW.equals(viewName);
    }

    public void init(){
        controller.init();
    }
}
