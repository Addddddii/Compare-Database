package com.example.compare_db.controller;

import com.example.compare_db.config.ConnectPool;
import com.example.compare_db.constant.constant.Constant;
import com.example.compare_db.constant.DataBaseEnum;
import com.example.compare_db.entity.DataBase;
import com.example.compare_db.loader.DatabaseStructureLoader;
import com.example.compare_db.output.Display;
import com.example.compare_db.output.HTMLDisplay;
import com.example.compare_db.service.DatabaseComparator;
import com.example.compare_db.utils.InfoUtils;
import com.example.compare_db.view.BaseConfigView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Main 控制器
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 *
 */
@Slf4j
@FXMLController
public class MainController implements Initializable {

	/**
	 * 左数据库名称
	 */
	public static String leftDataBaseName = StringUtils.EMPTY;

	/**
	 * 右数据库名称
	 */
	public static String rightDataBaseName = StringUtils.EMPTY;

	/**
	 * 左数据库名称
	 */
	public static String leftSchemaName = StringUtils.EMPTY;

	/**
	 * 右数据库名称
	 */
	public static String rightSchemaName = StringUtils.EMPTY;

	@Autowired
	private List<BaseConfigView> baseConfigViewList;

	@Autowired
	private DatabaseComparator databaseComparator;

	@Autowired
	private DatabaseStructureLoader databaseStructureLoader;

	@FXML
	private ComboBox<DataBaseEnum> type;

	@FXML
	private WebView webView;

	@FXML
	private Pane left;

	@FXML
	private Pane right;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		type.getItems().setAll(DataBaseEnum.values());
		type.setValue(DataBaseEnum.MySql);
		BaseConfigView leftView = baseConfigViewList.stream().filter(v -> v.isCurrentView(type.getValue().getView() + Constant.LEFT_VIEW)).findFirst().orElse(null);
		BaseConfigView rightView = baseConfigViewList.stream().filter(v -> v.isCurrentView(type.getValue().getView() + Constant.RIGHT_VIEW)).findFirst().orElse(null);
		left.getChildren().setAll(leftView.getView());
		right.getChildren().setAll(rightView.getView());
	}

	@FXML
	public void onChange(){
		ConnectPool.clean();
		this.initDataBaseOrSchema();
		String view = type.getValue().getView();
		BaseConfigView leftView = baseConfigViewList.stream().filter(v -> v.isCurrentView(view + Constant.LEFT_VIEW)).findFirst().orElse(null);
		BaseConfigView rightView = baseConfigViewList.stream().filter(v -> v.isCurrentView(view + Constant.RIGHT_VIEW)).findFirst().orElse(null);
		//刷新UI
		leftView.init();
		rightView.init();
		left.getChildren().setAll(leftView.getView());
		right.getChildren().setAll(rightView.getView());
	}

	@FXML
	public void doCompare() {
		String type = this.type.getValue().getType();
		Display display = new HTMLDisplay();
		databaseComparator.setDisplay(display);
		long start = System.currentTimeMillis();
		try {
			DataBase left_db = databaseStructureLoader.loadDatabase(Constant.LEFT, leftDataBaseName, leftSchemaName, type);
			DataBase right_db = databaseStructureLoader.loadDatabase(Constant.RIGHT, rightDataBaseName, rightSchemaName, type);
			log.info("加载表结构数据成功.... 耗时:{} ms",System.currentTimeMillis() - start);
			// 加载db结构
			start = System.currentTimeMillis();
			databaseComparator.compare(left_db, right_db);
			log.info("比对数据库结构成功耗时:{} ms",System.currentTimeMillis() - start);
			webView.getEngine().loadContent(display.getContent().toString());
		}catch (Exception e){
			InfoUtils.error(e.getMessage());
		}

	}

	/**
	 * 清空初始化值
	 */
	private void initDataBaseOrSchema(){
		leftDataBaseName = StringUtils.EMPTY;
		leftSchemaName = StringUtils.EMPTY;
		rightDataBaseName = StringUtils.EMPTY;
		rightSchemaName = StringUtils.EMPTY;
	}

}
