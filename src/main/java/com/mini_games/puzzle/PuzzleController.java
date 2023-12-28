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
        
    }
    
    private final Image WIN_IMAGE = new Image(getClass().getResourceAsStream("/com/mini_games/GOLD-BARS.jpg"));
    private final int numOfRows = 4;
    private Pane gamePane;
    private Pane userInfoPane;
    private Pane imagePane;

    @Override
    public void unfold() {
       imagePane = (Pane)checkedLookup(gamePane, "#imagePane");
       setImageViews();
    }

    @Override
    public void restore() {
        
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
                partArray[i][j] = new PuzzlePart(WIN_IMAGE, new Coordinates(currentHeight, currentWidth));
                partArray[i][j].setSize(heightOfAPart, widthOfAPart);
                partArray[i][j].getImagePart().setStyle("-fx-border-color: red;");
                
                imagePane.getChildren().add(partArray[i][j].getImagePart());
                
                currentWidth += widthOfAPart;
            }
            currentHeight += heightOfAPart;
        }
    }
    
    private void imageCuter() {
        double squareSize = Math.min(WIN_IMAGE.getWidth(), WIN_IMAGE.getHeight());
       // puzzleImageView.setViewport(new javafx.geometry.Rectangle2D(25, 50, 25, 50));
    }
    
    private double calculator() {
        return imagePane.getHeight() / 16;
    }
    
}
