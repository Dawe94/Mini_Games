package com.mini_games.dynamictools;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class DynamicBackButton {
    
    private final Button backButton;
    private final Pane mainPane;
    
    DynamicBackButton(Pane mainPane, Button backButton) {
        this.mainPane = mainPane;
        this.backButton = backButton;
        this.backButton.toFront();
    }
    
    public void action(Pane gamePane) {
        backButton.setOnAction(e -> handleDynamicOkButton(gamePane));
        backButton.setVisible(true);
    }
    
    public void setStyle(String cssStyle) {
        backButton.setStyle(cssStyle);
    }

    private void handleDynamicOkButton(Pane gamePane) {
        gamePane.setVisible(false);
        mainPane.setVisible(true);
        backButton.setVisible(false);
    }
}
    
    
    

