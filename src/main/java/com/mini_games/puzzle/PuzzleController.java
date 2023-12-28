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
    
    private ImageView[][] setImageViews() {
        double heightOfAPart = imagePane.getHeight() / numOfRows;
        double widthOfAPart = imagePane.getWidth() / numOfRows;
        int numOfParts = numOfRows * numOfRows;
        ImageView[][] imageArray = new ImageView[numOfRows][numOfRows];
        double currentHeight = 0;
        for (int i = 0; i < numOfRows; i++) {
            double currentWidth = 0;
            for (int j = 0; j < numOfRows; j++) {
                imageArray[i][j] = new ImageView();
                imageArray[i][j].setFitHeight(heightOfAPart);
                imageArray[i][j].setFitWidth(widthOfAPart);
                imageArray[i][j].setLayoutY(currentHeight);
                imageArray[i][j].setLayoutX(currentWidth);
                imageArray[i][j].setStyle("-fx-border-color: red;");
                imageArray[i][j].setVisible(true);
                imageArray[i][j].setImage(WIN_IMAGE);
                
                imagePane.getChildren().add(imageArray[i][j]);
                
                currentWidth += widthOfAPart;
            }
            currentHeight += heightOfAPart;
        }
        return imageArray;
    }
    
    private void imageCuter() {
        double squareSize = Math.min(WIN_IMAGE.getWidth(), WIN_IMAGE.getHeight());
       // puzzleImageView.setViewport(new javafx.geometry.Rectangle2D(25, 50, 25, 50));
    }
    
    private double calculator() {
        return imagePane.getHeight() / 16;
    }
    
}
