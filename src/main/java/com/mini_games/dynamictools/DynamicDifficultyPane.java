package com.mini_games.dynamictools;

import com.mini_games.interfaces.SubController;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class DynamicDifficultyPane {
    
    private final StackPane DIFF_PANE;
    private final VBox VBOX;
    private final double BUTTONS_WIDTH = 150;
    
    public DynamicDifficultyPane(StackPane difficultyPane) {
        DIFF_PANE = difficultyPane;
        VBOX = new VBox();
        Rectangle rectangle = new Rectangle();
        DIFF_PANE.getChildren().addAll(rectangle,VBOX);
        DIFF_PANE.setPrefWidth(BUTTONS_WIDTH * 1.15);
        VBOX.setAlignment(Pos.CENTER);
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
        setPaneParameters();
    }
    
    private void setPaneParameters() {
        System.out.println(VBOX.getHeight());
        VBOX.setLayoutX(DIFF_PANE.getWidth() / 2 - VBOX.getWidth() / 2);
        VBOX.setLayoutY(DIFF_PANE.getHeight()/ 2 - VBOX.getHeight() / 2);
        
    }
    
}
