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

    public static PuzzleController getInstance(Pane gamePane, Pane infoPane, DynamicTools dynamicTools) {
        if (controller == null) {
            controller = new PuzzleController(gamePane, infoPane, dynamicTools);
        }
        return controller;
    }

    private PuzzleController(Pane gamePane, Pane mainPane, DynamicTools dynamicTools) {
        this.gamePane = gamePane;
        this.dynamicTools = dynamicTools;
        dynamicTools.getBackButton().action(gamePane);
        imageNumbers = new ArrayList<>();
        Stream.iterate(1, i -> i <= 9, i -> i + 1)
                .forEach(i -> imageNumbers.add(i));
        Collections.shuffle(imageNumbers);
        System.out.println(imageNumbers);
        unfold();
    }

    private Image puzzleImage;
    private final Pane gamePane;
    private Pane imagePane;
    private Button solveButton;
    private Button undoButton;
    private Puzzle puzzle;
    private PuzzleScale scale;
    private RecordedSiding recorder;
    private final DynamicTools dynamicTools;
    private List<Integer> imageNumbers;
    private int imageCount = 1;

    @Override
    public void unfold() {
        imagePane = (Pane) checkedLookup(gamePane, "#imagePane");
        solveButton = (Button)checkedLookup(gamePane, "#solveButton");
        undoButton = (Button)checkedLookup(gamePane, "#undoButton");
        setButtons();
        setPuzzle();
        gamePane.setOnKeyPressed(eh -> handleKeyEvent(eh.getCode()));
    }

    @Override
    public void restore() {
        dynamicTools.getBackButton().action(gamePane);
        imagePane.getChildren().clear();
        setPuzzle();
    }
    
    private void setDisableButtons(boolean value) {
        solveButton.setDisable(value);
        undoButton.setDisable(value);
    }

    private void setPuzzle() {
        scale = PuzzleScale.FOUR_TO_FOUR;
        if (imageCount >= imageNumbers.size()) {
            imageCount = 1;
        }
        puzzleImage = new Image(getClass().getResourceAsStream("/com/mini_games/PuzzleImages/PuzzleImage" + imageNumbers.get(imageCount++) + ".jpg"),
                350, 350, true, true);
        puzzle = Puzzle.createPuzzle(imagePane, puzzleImage, scale);
        recorder = new RecordedSiding(puzzle);
        recorder.shuffle(false);
        setDisableButtons(false);
        System.out.println(puzzle.isReady());
    }

    private void handleKeyEvent(KeyCode keyCode) {
        if (!puzzle.isAnimationRunning()) {
            switch (keyCode) {
                case UP:
                    recorder.moveUp(true);
                    break;
                case DOWN:
                    recorder.moveDown(true);
                    break;
                case LEFT:
                    recorder.moveLeft(true);
                    break;
                case RIGHT:
                    recorder.moveRight(true);
                    break;
                default:
            }
        }
        checkResult();
    }

    private void setButtons() {
        solveButton.setOnAction(eh -> {
            setDisableButtons(true);
            recorder.solve(true);
        });
        undoButton.setOnAction(eh -> recorder.undo(true));
        undoButton.setFocusTraversable(false);
        solveButton.setFocusTraversable(false);
    }

    private void checkResult() {
        if (puzzle.isReady()) {
            System.out.println("WIN!!!!!!!");
        }     
    }

}
