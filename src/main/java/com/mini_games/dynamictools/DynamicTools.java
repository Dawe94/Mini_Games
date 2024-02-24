
package com.mini_games.dynamictools;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class DynamicTools {
    
    private final DynamicBackButton backButton;
    private final DynamicInfoPane infoPane;
    private final DynamicDifficultyPane difficultyPane;
    
    public DynamicTools(Pane mainPane, Button backButton, Pane infoPane, StackPane difficultyPane) {
        this.backButton = new DynamicBackButton(mainPane, backButton);
        this.infoPane = new DynamicInfoPane(infoPane);
        this.difficultyPane = new DynamicDifficultyPane(difficultyPane);
    }
    
     public DynamicBackButton getBackButton() {
        return backButton;
    }

    public DynamicInfoPane getInfoPane() {
        return infoPane;
    } 
    
    public DynamicDifficultyPane getDifficultyPane() {
        return difficultyPane;
    }
}

