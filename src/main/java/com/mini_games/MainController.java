package com.mini_games;

import com.mini_games.dynamictools.DynamicBackButton;
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
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="FXML Objects">
    @FXML
    private Pane mainPane;
    @FXML
    private Pane guessNumberPane;
    @FXML
    private Pane puzzlePane;
    @FXML
    private Pane userInfoPane;
    @FXML
    private Button backButton;
    //</editor-fold>

    @FXML
    public void checkNumbersAction() {
        guessNumber.checkNumbersAction();    
    }
    
    @FXML
    public void handleGuessNumberButton() {
        //mainPane.setVisible(false);
        if (guessNumber == null) {
            guessNumber = GuessNumberController.getInstance(guessNumberPane, mainPane);
        } else {
            guessNumber.restore();
        }
        guessNumberPane.setVisible(true);
        mainPane.setVisible(false);
    }
    
    @FXML
    public void handleInfoOkButton() {
        guessNumber.handleOkButton();
    }
    
    @FXML
    public void handlePuzzleButton() {
        mainPane.setVisible(false);
        puzzlePane.setVisible(true);
        puzzle = PuzzleController.getInstance(puzzlePane, userInfoPane);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DynamicBackButton.getInstance(backButton);
    }
}
