package com.mini_games.puzzle;

import com.mini_games.interfaces.SubController;
import com.mini_games.dynamictools.DynamicTools;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.layout.Pane;

public class PuzzleController implements SubController {

    private static PuzzleController controller;

    public static PuzzleController getInstance(Pane mainPane, Pane gamePane, DynamicTools dynamicTools) {
        if (controller == null) {
            controller = new PuzzleController(mainPane, gamePane, dynamicTools);
        } else {
            controller.restore();
        }
        return controller;
    }

    private Image puzzleImage;
    private final Pane gamePane;
    private final Pane mainPane;
    private Pane imagePane;
    private Button solveButton;
    private Button undoButton;
    private Puzzle puzzle;
    private PuzzleScale scale;
    private Sliding slider;
    private final DynamicTools dynamicTools;
    private List<Integer> imageNumbers;
    private int imageCount = 1;
    
    private PuzzleController(Pane mainPane, Pane gamePane, DynamicTools dynamicTools) {
        this.mainPane = mainPane;
        this.gamePane = gamePane;
        this.dynamicTools = dynamicTools;           
        imageNumbers = new ArrayList<>();
        Stream.iterate(1, i -> i <= 10, i -> i + 1)
                .forEach(i -> imageNumbers.add(i));
        Collections.shuffle(imageNumbers);
        unfold();
    }
    
    @Override
    public void startGame(String diffName) {
        for (PuzzleScale difficulty : PuzzleScale.values()) {
            if (difficulty.getName().equals(diffName)) {
                scale = difficulty;
                break;
            }
        }
        dynamicTools.getBackButton().action(gamePane);
        setPuzzle();
        mainPane.setVisible(false);
        gamePane.setVisible(true);
    }

    @Override
    public void unfold() {
        imagePane = (Pane) checkedLookup(gamePane, "#imagePane");
        solveButton = (Button)checkedLookup(gamePane, "#solveButton");
        undoButton = (Button)checkedLookup(gamePane, "#undoButton");
        setButtons();       
        gamePane.setOnKeyPressed(eh -> handleKeyEvent(eh.getCode()));       
    }

    @Override
    public void restore() {
        imagePane.getChildren().clear();        
    }
    
    private void setDisableButtons(boolean value) {
        solveButton.setDisable(value);
        undoButton.setDisable(value);
    }

    private void setPuzzle() {
        if (imageCount >= imageNumbers.size()) {
            imageCount = 1;
        }
        puzzleImage = new Image(getClass().getResourceAsStream("/com/mini_games/PuzzleImages/PuzzleImage" + imageNumbers.get(imageCount++) + ".jpg"),
                350, 350, true, true);
        puzzle = Puzzle.createPuzzle(imagePane, puzzleImage, scale);
        slider = new RecordedSliding(puzzle);
        slider.shuffle();
        setDisableButtons(false);
    }

    private void handleKeyEvent(KeyCode keyCode) {
        if (!puzzle.isAnimationRunning()) {
            switch (keyCode) {
                case UP, W ->
                    slider.moveUp(true);                    
                case DOWN, S ->
                    slider.moveDown(true);                    
                case LEFT, A ->
                    slider.moveLeft(true);                   
                case RIGHT, D ->
                    slider.moveRight(true);                                 
            }
        }
        checkResult();
    }

    private void setButtons() {
        solveButton.setOnAction(eh -> {
            setDisableButtons(true);
            slider.solve(true);
        });
        undoButton.setOnAction(eh -> slider.undo(true));
        undoButton.setFocusTraversable(false);
        solveButton.setFocusTraversable(false);
    }

    private void checkResult() {
        if (puzzle.isReady()) {
            System.out.println("WIN!!!!!!!");
        }     
    }

}
