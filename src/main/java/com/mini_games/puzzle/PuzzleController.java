package com.mini_games.puzzle;

import com.mini_games.Coordinates;
import com.mini_games.SubController;
import com.mini_games.dynamictools.DynamicBackButton;
import com.mini_games.dynamictools.DynamicTools;
import javafx.scene.image.Image;
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
    private final int numOfRows = 4;
    private final Pane gamePane;
    private final Pane mainPane;
    private Pane imagePane;
    private final DynamicTools dynamicTools;

    @Override
    public void unfold() {
       imagePane = (Pane)checkedLookup(gamePane, "#imagePane");
       setImageViews();
    }

    @Override
    public void restore() {
        dynamicTools.getBackButton().action(gamePane);
    }
    
    private void setImageViews() {
        double heightOfAPart = imagePane.getHeight() / numOfRows;
        double widthOfAPart = imagePane.getWidth() / numOfRows;
        int numOfParts = numOfRows * numOfRows;
        PuzzlePart[][] partArray = new PuzzlePart[numOfRows][numOfRows];
        double currentHeight = 0;
        for (int i = 0; i < numOfRows; i++) {
            double currentWidth = 0;
            for (int j = 0; j < numOfRows; j++) {
                Coordinates position = new Coordinates(currentHeight, currentWidth);
                partArray[i][j] = new PuzzlePart(WIN_IMAGE, position);
                partArray[i][j].setSize(heightOfAPart, widthOfAPart);
                partArray[i][j].getImagePart().setStyle("-fx-border-color: red;");
                partArray[i][j].setViewPort(currentHeight, currentWidth, heightOfAPart,  widthOfAPart);
                partArray[i][j].decrementSize();
                imagePane.getChildren().add(partArray[i][j].getImagePart());
                
                currentWidth += widthOfAPart;
            }
            currentHeight += heightOfAPart;
        }
        //partArray[0][0].changePosition(partArray[numOfRows-1][numOfRows-1]);
    }
    
    private void imageCuter() {
        double squareSize = Math.min(WIN_IMAGE.getWidth(), WIN_IMAGE.getHeight());
       // puzzleImageView.setViewport(new javafx.geometry.Rectangle2D(25, 50, 25, 50));
    }
    
    private double calculator() {
        return imagePane.getHeight() / 16;
    }
    
}
