package com.mini_games.dynamictools;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class DynamicInfoPane {
    
    private Pane infoPane;
    private ImageView imageView;
    private Label label;
    private Button button;
    
    DynamicInfoPane(Pane infoPane) {
        this.infoPane = infoPane;
    }
    
}
