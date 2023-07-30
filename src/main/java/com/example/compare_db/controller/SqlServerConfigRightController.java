package com.example.compare_db.controller;

import com.example.compare_db.constant.constant.Constant;
import com.example.compare_db.constant.DataBaseEnum;
import com.example.compare_db.constant.constant.MessageConstant;
import com.example.compare_db.entity.connection.SqlServerConfig;
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
import org.apache.commons.lang3.StringUtils;
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
public class SqlServerConfigRightController implements Initializable {

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
    private ComboBox<String> schema;

    @FXML
    private void doConnection() {
        try {
            //校验host是否合法
            Assert.isTrue(VerifyUtils.verifyHost(host.getText()), MessageConstant.HOST_NOT_VALID);
            SqlServerConfig config = new SqlServerConfig(this.host.getText(), this.port.getText(), this.user.getText(), this.password.getText(), this.initDataBase.getText());
            ConnectionUtils.init(config,Constant.RIGHT);
            List<String> databaseNameList = databaseStructureLoader.loadAllDatabaseNames(Constant.RIGHT, DataBaseEnum.SqlServer.getType());
            this.database.getItems().setAll(databaseNameList);
            InfoUtils.info(MessageConstant.CONNECT_SUCCESS);
        } catch (Exception e) {
            log.error(e.toString());
            InfoUtils.error(e.getMessage());
        }
    }

    @FXML
    private void databaseChange(){
        this.schema.setValue(null);
        if (StringUtils.isNotBlank(this.database.getValue())) {
            List<String> schemaNameList = databaseStructureLoader.loadSchemaByDataBaseName(Constant.RIGHT, this.database.getValue(), DataBaseEnum.SqlServer.getType());
            this.schema.getItems().setAll(schemaNameList);
        }
        MainController.rightDataBaseName = this.database.getValue();
    }

    @FXML
    private void schemaChange(){
        MainController.rightSchemaName = this.schema.getValue();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initDataBaseName();
    }

    public void init(){
        NodeClearUtils.clear(this.host, this.port, this.user, this.password);
        NodeClearUtils.clear(this.database, this.schema);
        if (this.initDataBase != null) {
            this.initDataBaseName();
        }
    }

    private void initDataBaseName() {
        this.initDataBase.setText(Constant.SQL_SERVER_INIT_DATABASE);
    }
}
