package com.example.compare_db;

import com.example.compare_db.view.MainView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
@SpringBootApplication
public class CompareDbApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launch(CompareDbApplication.class, MainView.class, args);
    }

}
