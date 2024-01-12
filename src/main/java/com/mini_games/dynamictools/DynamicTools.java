
package com.mini_games.dynamictools;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class DynamicTools {
    
    private final DynamicBackButton backButton;
    private final DynamicInfoPane infoPane;
    
    public DynamicTools(Pane mainPane, Button backButton, Pane infoPane) {
        this.backButton = new DynamicBackButton(mainPane, backButton);
        this.infoPane = new DynamicInfoPane(infoPane);
    }
    
     public DynamicBackButton getBackButton() {
        return backButton;
    }

    public DynamicInfoPane getInfoPane() {
        return infoPane;
    }
    
    
}
