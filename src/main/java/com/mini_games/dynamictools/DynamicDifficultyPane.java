package com.mini_games.dynamictools;

import com.mini_games.interfaces.SubController;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DynamicDifficultyPane {
    
    private final StackPane DIFF_PANE;
    private final VBox VBOX;
    private final double BUTTONS_WIDTH = 150;
    
    public DynamicDifficultyPane(StackPane difficultyPane) {
        DIFF_PANE = difficultyPane;
        VBOX = new VBox();
        DIFF_PANE.getChildren().add(VBOX);
        DIFF_PANE.setPrefWidth(BUTTONS_WIDTH * 1.15);
    }
    
    public void buildButtons(SubController controller, List<String> diffNames) {
        DIFF_PANE.setVisible(true);
        for (String diffName : diffNames) {
            Button button = new Button(diffName);
            button.setOnAction(eh -> {
                DIFF_PANE.setVisible(false);
                VBOX.getChildren().clear();
                controller.startGame(diffName);
            });
            button.setPrefWidth(BUTTONS_WIDTH);
            VBOX.getChildren().add(button);
        }
        VBOX.setSpacing(20);
        setPaneParameters(diffNames.size());
    }
    
    private void setPaneParameters(int numOfButtons) {
        double paneHeight = numOfButtons * 30 + (numOfButtons) * 20;
        //DIFF_PANE.setPrefHeight(VBOX.getHeight() * 1.1);
        System.out.println(VBOX.getHeight());
        VBOX.setLayoutX(DIFF_PANE.getWidth() / 2 - VBOX.getWidth() / 2);
        VBOX.setLayoutY(DIFF_PANE.getHeight()/ 2 - VBOX.getHeight() / 2);
        
    }
    
}
