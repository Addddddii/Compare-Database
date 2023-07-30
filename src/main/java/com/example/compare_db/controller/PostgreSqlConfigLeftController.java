package com.example.compare_db.controller;

import com.example.compare_db.constant.constant.Constant;
import com.example.compare_db.constant.DataBaseEnum;
import com.example.compare_db.constant.constant.MessageConstant;
import com.example.compare_db.entity.connection.PostgreSqlConfig;
import com.example.compare_db.loader.DatabaseStructureLoader;
import com.example.compare_db.utils.ConnectionUtils;
import com.example.compare_db.utils.InfoUtils;
import com.example.compare_db.utils.NodeClearUtils;
import com.example.compare_db.utils.VerifyUtils;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * MySqlConfigView控制器
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@Slf4j
@FXMLController
public class PostgreSqlConfigLeftController implements Initializable {

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
    private TextField initDataBase;

    @FXML
    private ComboBox<String> database;

    @FXML
    public void doConnection() {
        try {
            //校验host是否合法
            Assert.isTrue(VerifyUtils.verifyHost(host.getText()), MessageConstant.HOST_NOT_VALID);
            PostgreSqlConfig config = new PostgreSqlConfig(host.getText(), port.getText(), user.getText(), password.getText(),initDataBase.getText());
            ConnectionUtils.init(config,Constant.LEFT);
            List<String> databaseNameList = databaseStructureLoader.loadAllDatabaseNames(Constant.LEFT, DataBaseEnum.SqlServer.getType());
            database.getItems().setAll(databaseNameList);
            InfoUtils.info(MessageConstant.CONNECT_SUCCESS);
        } catch (Exception e) {
            log.error(e.toString());
            InfoUtils.error(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initDataBaseName();
    }

    @FXML
    private void onChange(){
        MainController.leftDataBaseName = this.database.getValue();
    }

    public void init(){
        NodeClearUtils.clear(this.host, this.port, this.user, this.password);
        NodeClearUtils.clear(this.database);
        if (this.initDataBase != null) {
            this.initDataBaseName();
        }
    }

    private void initDataBaseName() {
        this.initDataBase.setText(Constant.SQL_SERVER_INIT_DATABASE);
    }
}
