package com.mini_games;

import com.mini_games.guess_numbers.GuessNumberController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MainController implements Initializable{

    //<editor-fold defaultstate="collapsed" desc="Variables">
    private final String MISSING_NUMBER = "You have to write a number in all of number fields!";
    private GuessNumberController guessNumber;
    private final Image WIN_IMAGE = new Image(getClass().getResourceAsStream("GOLD-BARS.jpg"));
    private final Image LOSE_IMAGE = new Image(getClass().getResourceAsStream("policeCar.jpg"));
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="FXML Objects">
    @FXML
    private Pane mainPane;
    @FXML
    private Pane basePane;
    @FXML
    private Pane userInfoPane;
    @FXML
    private Label userInfoLabel;
    @FXML
    private TextField input1;
    @FXML
    private TextField input2;
    @FXML
    private TextField input3;
    @FXML
    private TextField input4;
    @FXML
    private Label relationLabel1;
    @FXML
    private Label relationLabel2;
    @FXML
    private Label relationLabel3;
    @FXML
    private ImageView resultImage;
//</editor-fold>

    @FXML
    public void checkNumbersAction() {
        try {
            guessNumber.checkNumbersAction(basePane, userInfoPane, WIN_IMAGE, LOSE_IMAGE);
        } catch (InvalidNumbersException ex) {
            basePane.setDisable(true);
            basePane.setOpacity(0.3);
            userInfoPane.setVisible(true);
            userInfoLabel.setText(MISSING_NUMBER);
            userInfoLabel.setTextFill(Color.RED);
        }
    }
    
    @FXML
    public void handleGuessNumberButton() {
        mainPane.setVisible(false);
        basePane.setVisible(true);
        guessNumber = new GuessNumberController(basePane);
    }
    
    @FXML
    public void handleInfoOkButton() {
        guessNumber.handleOkButton(basePane, userInfoPane);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
    /*
    private void getRelations() {
        relationLabel1.setText(relation(numbers.get(0), numbers.get(1)));
        relationLabel2.setText(relation(numbers.get(1), numbers.get(2)));
        relationLabel3.setText(relation(numbers.get(2), numbers.get(3)));
    }
    
    private String relation(int first, int second) {
        return first < second ? "<" : ">";
    }

    
    private void setListener() {
        for (TextField tf : inputs) {
            tf.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.isEmpty() && !newValue.matches("^\\d$")) {
                        tf.setText(oldValue);
                    }
                }
            });
        }
    }   

    private void getResult(String result, Color color, Image image) {
        basePane.setDisable(true);
        basePane.setOpacity(0.3);
        userInfoPane.setVisible(true);
        userInfoLabel.setFont(new Font(24));
        userInfoLabel.setText(result);
        userInfoLabel.setTextFill(color);
        gameOver = true;
        resultImage.setVisible(true);
        resultImage.setImage(image);      
    }

    private void restore() {
        System.out.println(numbers.toString());
        gameOver = true;
        round = 0;
        numbers = Numbers.generate(10);
        resultImage.setVisible(false);
        userInfoLabel.setFont(new Font(14));
        getRelations();
        for (TextField tf : inputs) {
            tf.clear();
            tf.setStyle("-fx-text-fill:black; -fx-background-color: #dcdcdc;");
            tf.setDisable(false);
        }
    }   */
}
