package com.example.compare_db.view;

import com.example.compare_db.constant.constant.Constant;
import com.example.compare_db.controller.SqlServerConfigLeftController;
import de.felixroske.jfxsupport.FXMLView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@FXMLView("/view/SqlServerConfigLeftView.fxml")
public class SqlServerConfigLeftView extends BaseConfigView {

    @Autowired
    private SqlServerConfigLeftController controller;

    @Override
    public boolean isCurrentView(String viewName) {
        return Constant.SQL_SERVER_CONFIG_LEFT_VIEW.equals(viewName);
    }

    public void init(){
        controller.init();
    }
}
