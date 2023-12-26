package com.mini_games.puzzle;

import com.mini_games.SubController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PuzzleController implements SubController {
    
    private static PuzzleController controller;
    
    public static PuzzleController getInstance(Pane gamePane, Pane infoPane) {
        if (controller == null) {
            controller = new PuzzleController(gamePane, infoPane);
        }
        return controller;
    }
    
    private PuzzleController(Pane gamePane, Pane infoPane) {
        this.gamePane = gamePane;
        userInfoPane = infoPane;
        unfold();
        puzzleImageView.setImage(WIN_IMAGE);
    }
    
    private Pane gamePane;
    private Pane userInfoPane;
    private ImageView puzzleImageView;
    private final Image WIN_IMAGE = new Image(getClass().getResourceAsStream("/com/mini_games/GOLD-BARS.jpg"));

    @Override
    public void unfold() {
       puzzleImageView = (ImageView)checkedLookup(gamePane, "#puzzleImageView");
    }

    @Override
    public void restore() {
        
    }
    
    
    
}
