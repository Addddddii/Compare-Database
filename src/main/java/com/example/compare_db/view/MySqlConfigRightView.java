package com.example.compare_db.view;

import com.example.compare_db.constant.constant.Constant;
import com.example.compare_db.controller.MySqlConfigRightController;
import de.felixroske.jfxsupport.FXMLView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@FXMLView("/view/MySqlConfigRightView.fxml")
public class MySqlConfigRightView extends BaseConfigView {

    @Autowired
    private MySqlConfigRightController controller;

    @Override
    public boolean isCurrentView(String viewName) {
        return Constant.MYSQL_CONFIG_RIGHT_VIEW.equals(viewName);
    }

    public void init(){
        controller.init();
    }
}
