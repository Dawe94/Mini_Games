package com.mini_games.dynamictools;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class DynamicBackButton {
    
    private static DynamicBackButton button;
    
    public static DynamicBackButton getInstance() {
        button.setVisible(true);
        return button;
    }
    
    public static DynamicBackButton getInstance(Button bButton) {
        if (button == null) {
            button = new DynamicBackButton(bButton);
        }
        return getInstance();
    }
    
    private final Button backButton;
    
    private DynamicBackButton(Button backButton) {
        this.backButton = backButton;
        this.backButton.toFront();
    }
    
    public void setOnAction(Pane mainPane, Pane gamePane) {
        backButton.setOnAction(e -> handleDynamicOkButton(mainPane, gamePane));
    }
    
    public void setStyle(String cssStyle) {
        backButton.setStyle(cssStyle);
    }
    
    private void setVisible(boolean a) {
        backButton.setVisible(a);
    }

    private void handleDynamicOkButton(Pane mainPane, Pane gamePane) {
        gamePane.setVisible(false);
        mainPane.setVisible(true);
        setVisible(false);
    }
}
    
    
    

