package com.example.compare_db.view;

import de.felixroske.jfxsupport.AbstractFxmlView;

/**
 * @author <a href="mailto: addddddi79z@gmail.com">Adi</a>
 */
public abstract class BaseConfigView extends AbstractFxmlView {

    public abstract boolean isCurrentView(String viewName);

    public abstract void init();
}
