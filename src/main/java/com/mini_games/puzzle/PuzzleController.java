package com.mini_games.puzzle;

import com.mini_games.Coordinates;
import com.mini_games.interfaces.SubController;
import com.mini_games.dynamictools.DynamicTools;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.layout.Pane;

public class PuzzleController implements SubController {
    
    private static PuzzleController controller;
    
    public static PuzzleController getInstance(Pane gamePane, Pane infoPane, DynamicTools dynamicTools) {
        if (controller == null) {
            controller = new PuzzleController(gamePane, infoPane, dynamicTools);
        }
        return controller;
    }
    
    private PuzzleController(Pane gamePane, Pane mainPane, DynamicTools dynamicTools) {
        this.gamePane = gamePane;
        this.mainPane = mainPane;
        this.dynamicTools = dynamicTools;
        dynamicTools.getBackButton().action(gamePane);
        unfold();
    }
    
    private final Image WIN_IMAGE = new Image(getClass().getResourceAsStream("/com/mini_games/PuzzleImages/GreenEarth.jpg"),
            350, 350, true, true);
    private final Pane gamePane;
    private final Pane mainPane;
    private Pane imagePane;
    private Puzzle puzzle;
    private PuzzleScale scale;
    private final DynamicTools dynamicTools;

    @Override
    public void unfold() {
       imagePane = (Pane)checkedLookup(gamePane, "#imagePane");
       imagePane.getChildren().clear();
       scale = PuzzleScale.THREE_TO_THREE;
       puzzle = Puzzle.createPuzzle(imagePane, WIN_IMAGE, scale);
       gamePane.setOnKeyPressed(eh -> handleKeyEvent(eh.getCode()));
    }

    @Override
    public void restore() {
        dynamicTools.getBackButton().action(gamePane);
        imagePane.getChildren().clear();
        scale = PuzzleScale.THREE_TO_THREE;
        puzzle = Puzzle.createPuzzle(imagePane, WIN_IMAGE, scale);
    }
    
    private void handleKeyEvent(KeyCode keyCode) {
        int blank = puzzle.getBlankElement();
        if (blank != -1) {
            boolean startOfRow = blank % scale.getScale() == 0;
            boolean endOfRow = (blank + 1) % scale.getScale() == 0;
            switch (keyCode) {
                case UP:
                    if (blank - scale.getScale() >= 0) puzzle.swap(blank - scale.getScale(), blank);
                    break;
                case DOWN:
                    if (blank + scale.getScale() < puzzle.getSize()) puzzle.swap(blank + scale.getScale(), blank);
                    break;
                case LEFT:
                    if (!startOfRow && blank - 1 >= 0) puzzle.swap(blank - 1, blank);
                    break;
                case RIGHT:
                    if (!endOfRow && blank + 1 < puzzle.getSize()) puzzle.swap(blank + 1, blank);
                    break;
                default:
            }
        }
    }
    
    
    
}
