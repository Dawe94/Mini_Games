package com.mini_games;

import com.mini_games.dynamictools.DynamicTools;
import com.mini_games.guess_numbers.GuessNumberController;
import com.mini_games.puzzle.PuzzleController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class MainController implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="Variables">
    private GuessNumberController guessNumber;
    private PuzzleController puzzle;
    private DynamicTools dynamicTools;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="FXML Objects">
    @FXML
    private Pane mainPane;
    @FXML
    private Pane guessNumberPane;
    @FXML
    private Pane puzzlePane;
    @FXML
    private Button backButton;
    @FXML 
    private Pane infoPane;
    //</editor-fold>
    
    @FXML
    public void handleGuessNumberButton() {       
        guessNumber = GuessNumberController.getInstance(guessNumberPane, dynamicTools);           
        guessNumber.startGame(mainPane);
    }
     
    @FXML
    public void handlePuzzleButton() {
        puzzle = PuzzleController.getInstance(puzzlePane, dynamicTools);
        puzzle.startGame(mainPane);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dynamicTools = new DynamicTools(mainPane, backButton, infoPane);
    }
}
