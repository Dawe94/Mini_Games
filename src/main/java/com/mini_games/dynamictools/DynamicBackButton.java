package com.mini_games.dynamictools;

import com.mini_games.MainController;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class DynamicBackButton {
    
    private Button button;
    
    public DynamicBackButton(Pane gamePane, Pane mainPane) {
        button = new Button("Back");
        gamePane.getChildren().add(button);
        button.setOnAction(e -> handleDynamicOkButton(gamePane, mainPane));
        button.setLayoutY(gamePane.getHeight() - 50);
        button.setLayoutX(gamePane.getWidth() -80);
    }
    
    public void setStyle(String cssStyle) {
        button.setStyle(cssStyle);
    }

    private void handleDynamicOkButton(Pane gamePane, Pane mainPane) {
        gamePane.setVisible(false);
        mainPane.setVisible(true);
    }
}
    
    
    

