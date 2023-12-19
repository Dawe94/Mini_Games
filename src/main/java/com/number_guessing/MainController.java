package com.number_guessing;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class MainController implements Initializable{

    private Numbers numbers;
    private final List<TextField> inputs = new ArrayList<>();
    private int round;
    
    //<editor-fold defaultstate="collapsed" desc="FXML Objects">
    @FXML
    private Pane basePane;
    @FXML
    private Pane alertPane;
    @FXML
    private Label alertLabel;
    @FXML
    private Pane resultPane;
    @FXML
    private Label resultLabel;
    @FXML
    private TextField input1;
    @FXML
    private TextField input2;
    @FXML
    private TextField input3;
    @FXML
    private TextField input4;
    @FXML
    private Label hints;
//</editor-fold>

    @FXML
    public void checkNumbersAction() {
        numbers.check(inputs);
    }
    
    @FXML
    public void handleAlertButton() {
        alertPane.setVisible(false);
        basePane.setDisable(false);
        basePane.setOpacity(1);
    }
    
    @FXML
    public void handleResultButton() {
        resultPane.setVisible(false);
        basePane.setDisable(false);
        basePane.setOpacity(1);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numbers = Numbers.generate();
        hints.setText(getHints());
        inputsAsCollection();
        setListener();
    }
    
    private String getHints() {
        StringBuilder sb = new StringBuilder();
        sb.append("The first number is ").append(relation(numbers.get(0), numbers.get(1))).append(" the second number."+"\n");
        sb.append("The second number is ").append(relation(numbers.get(1), numbers.get(2))).append(" the third number."+"\n");
        sb.append("The third number is ").append(relation(numbers.get(2), numbers.get(3))).append(" the last number."+"\n");
        sb.append("The last number is ").append(relation(numbers.get(3), numbers.get(0))).append(" the first number."+"\n");
        return sb.toString();
    }
    
    private String relation(int first, int second) {
        return first < second ? "smaller than" : "bigger than";
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
}
