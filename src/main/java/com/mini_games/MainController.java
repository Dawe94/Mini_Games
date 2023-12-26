package com.mini_games;

import com.mini_games.guess_numbers.GuessNumberController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class MainController implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="Variables">
    private GuessNumberController guessNumber;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="FXML Objects">
    @FXML
    private Pane mainPane;
    @FXML
    private Pane guessNumberPane;
    @FXML
    private Pane userInfoPane;
    //</editor-fold>

    @FXML
    public void checkNumbersAction() {
            guessNumber.checkNumbersAction();    
    }
    
    @FXML
    public void handleGuessNumberButton() {
        mainPane.setVisible(false);
        guessNumberPane.setVisible(true);
        guessNumber = GuessNumberController.getInstance(guessNumberPane, userInfoPane);
    }
    
    @FXML
    public void handleInfoOkButton() {
        guessNumber.handleOkButton();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
    }
}
