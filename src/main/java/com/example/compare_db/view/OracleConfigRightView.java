package com.example.compare_db.view;

import com.example.compare_db.constant.constant.Constant;
import com.example.compare_db.controller.OracleConfigRightController;
import de.felixroske.jfxsupport.FXMLView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@FXMLView("/view/OracleConfigRightView.fxml")
public class OracleConfigRightView extends BaseConfigView {

    @Autowired
    private OracleConfigRightController controller;

    @Override
    public boolean isCurrentView(String viewName) {
        return Constant.ORACLE_CONFIG_RIGHT_VIEW.equals(viewName);
    }

    public void init(){
        controller.init();
    }
}
