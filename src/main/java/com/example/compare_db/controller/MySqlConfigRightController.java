package com.example.compare_db.controller;

import com.example.compare_db.constant.constant.Constant;
import com.example.compare_db.constant.DataBaseEnum;
import com.example.compare_db.constant.constant.MessageConstant;
import com.example.compare_db.entity.connection.MySqlConfig;
import com.example.compare_db.loader.DatabaseStructureLoader;
import com.example.compare_db.utils.ConnectionUtils;
import com.example.compare_db.utils.InfoUtils;
import com.example.compare_db.utils.NodeClearUtils;
import com.example.compare_db.utils.VerifyUtils;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

/**
 * MySqlConfigView控制器
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Slf4j
@FXMLController
public class MySqlConfigRightController {

    @Autowired
    private DatabaseStructureLoader databaseStructureLoader;

    @FXML
    private TextField host;

    @FXML
    private TextField port;

    @FXML
    private TextField user;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<String> database;

    @FXML
    public void doConnection() {
        try {
            //校验host是否合法
            Assert.isTrue(VerifyUtils.verifyHost(host.getText()), MessageConstant.HOST_NOT_VALID);
            MySqlConfig config = new MySqlConfig(host.getText(), port.getText(), user.getText(), password.getText());
            ConnectionUtils.init(config,Constant.RIGHT);
            List<String> databaseNameList = databaseStructureLoader.loadAllDatabaseNames(Constant.RIGHT, DataBaseEnum.MySql.getType());
            database.getItems().setAll(databaseNameList);
            InfoUtils.info(MessageConstant.CONNECT_SUCCESS);
        } catch (Exception e) {
            log.error(e.toString());
            InfoUtils.error(e.getMessage());
        }
    }

    @FXML
    private void onChange(){
        MainController.rightDataBaseName = this.database.getValue();
    }

    public void init(){
        NodeClearUtils.clear(this.host, this.port, this.user, this.password);
    }

}
