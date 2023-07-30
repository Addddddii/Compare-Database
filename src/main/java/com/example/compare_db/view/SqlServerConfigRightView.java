package com.example.compare_db.view;

import com.example.compare_db.constant.constant.Constant;
import com.example.compare_db.controller.SqlServerConfigRightController;
import de.felixroske.jfxsupport.FXMLView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@FXMLView("/view/SqlServerConfigRightView.fxml")
public class SqlServerConfigRightView extends BaseConfigView {

    @Autowired
    private SqlServerConfigRightController controller;

    @Override
    public boolean isCurrentView(String viewName) {
        return Constant.SQL_SERVER_CONFIG_RIGHT_VIEW.equals(viewName);
    }

    public void init(){
        controller.init();
    }
}
