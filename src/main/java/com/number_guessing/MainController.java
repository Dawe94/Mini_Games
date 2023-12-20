package com.number_guessing;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainController implements Initializable{

    //<editor-fold defaultstate="collapsed" desc="Variables">
    private final String MISSING_NUMBER = "You have to write a number in all of number fields!";
    private Numbers numbers;
    private final List<TextField> inputs = new ArrayList<>();
    private int round;
    private boolean gameOver;
    private final Image WIN_IMAGE = new Image(getClass().getResourceAsStream("GOLD-BARS.jpg"));
    private final Image LOSE_IMAGE = new Image(getClass().getResourceAsStream("policeCar.jpg"));
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="FXML Objects">
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
            if (numbers.check(inputs)) {
                getResult("Congratulation! You Win!", Color.GREEN, WIN_IMAGE);
            } else if (++round >= 4) {
                getResult("You Lose!", Color.RED, LOSE_IMAGE);
            }
        } catch (InvalidNumbersException ex) {
            basePane.setDisable(true);
            basePane.setOpacity(0.3);
            userInfoPane.setVisible(true);
            userInfoLabel.setText(MISSING_NUMBER);
            userInfoLabel.setTextFill(Color.RED);
        }
    }
    
    @FXML
    public void handleInfoOkButton() {
        userInfoPane.setVisible(false);
        userInfoLabel.setText("");
        userInfoLabel.setTextFill(Color.BLACK);
        basePane.setDisable(false);
        basePane.setOpacity(1);
        if (gameOver) {
            restore();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numbers = Numbers.generate();
        getRelations();
        inputsAsCollection();
        setListener();
    }
    
    private void getRelations() {
        relationLabel1.setText(relation(numbers.get(0), numbers.get(1)));
        relationLabel2.setText(relation(numbers.get(1), numbers.get(2)));
        relationLabel3.setText(relation(numbers.get(2), numbers.get(3)));
    }
    
    private String relation(int first, int second) {
        return first < second ? "<" : ">";
    }

    private void inputsAsCollection() {
        inputs.add(input1);
        inputs.add(input2);
        inputs.add(input3);
        inputs.add(input4);
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
        numbers = Numbers.generate();
        resultImage.setVisible(false);
        userInfoLabel.setFont(new Font(14));
        getRelations();
        for (TextField tf : inputs) {
            tf.clear();
            tf.setStyle("-fx-text-fill:black; -fx-background-color: #dcdcdc;");
            tf.setDisable(false);
        }
    }
}
