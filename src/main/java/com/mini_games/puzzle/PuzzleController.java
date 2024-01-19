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
    private final int RATIO = 3;
    private final Pane gamePane;
    private final Pane mainPane;
    private Pane imagePane;
    private List<PuzzlePart> listOfParts;
    private final DynamicTools dynamicTools;

    @Override
    public void unfold() {
       imagePane = (Pane)checkedLookup(gamePane, "#imagePane");
       imagePane.getChildren().clear();
       listOfParts = setImageViews();
       gamePane.setOnKeyPressed(eh -> handleKeyEvent(eh.getCode()));
    }

    @Override
    public void restore() {
        dynamicTools.getBackButton().action(gamePane);
        imagePane.getChildren().clear();
        listOfParts = setImageViews();
    }
    
    private List<PuzzlePart> setImageViews() {
        double heightOfAPart = imagePane.getHeight() / RATIO;
        double widthOfAPart = imagePane.getWidth() / RATIO;
        int numOfParts = RATIO * RATIO;
        double currHeight = 0;
        double currWidth = 0;
        List<PuzzlePart> list = new ArrayList<>();
        for (int i = 0; i < numOfParts; i++) {
            Coordinates position = new Coordinates(currHeight, currWidth);
            PuzzlePart currentPart = i == numOfParts - 1 ? new PuzzlePart(position) : new PuzzlePart(WIN_IMAGE, position);
            currentPart.setSize(heightOfAPart, widthOfAPart);
            currentPart.getImagePart().setStyle("-fx-border-color: red;");
            currentPart.setViewPort(currHeight, currWidth, heightOfAPart,  widthOfAPart);
            currentPart.decrementSize();
            list.add(currentPart);
            imagePane.getChildren().add(currentPart.getImagePart());           
            currHeight = (i + 1) % RATIO == 0 ? currHeight + heightOfAPart : currHeight;
            currWidth = (i + 1) % RATIO == 0 ? 0 : currWidth + widthOfAPart;
        }      
        Puzzle.shuffle(list);
        return list;
    }

    private void handleKeyEvent(KeyCode keyCode) {
        int blank = Puzzle.getBlankElement(listOfParts);
        if (blank != -1) {
            boolean startOfRow = blank % RATIO == 0;
            boolean endOfRow = (blank + 1) % RATIO == 0;
            switch (keyCode) {
                case UP:
                    if (blank - RATIO >= 0) Puzzle.swap(listOfParts, blank - RATIO, blank, true);
                    break;
                case DOWN:
                    if (blank + RATIO < listOfParts.size()) Puzzle.swap(listOfParts, blank + RATIO, blank, true);
                    break;
                case LEFT:
                    if (!startOfRow && blank - 1 >= 0) Puzzle.swap(listOfParts, blank - 1, blank, true);
                    break;
                case RIGHT:
                    if (!endOfRow && blank + 1 < listOfParts.size()) Puzzle.swap(listOfParts, blank + 1, blank, true);
                    break;
                default:
            }
        }
    }
    
    
    
}
