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
import javafx.scene.layout.Pane;

public class MainController implements Initializable{

    private final String MISSING_NUMBER = "You have to write a number in all of number fields!";
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
    private Label relationLabel1;
    @FXML
    private Label relationLabel2;
    @FXML
    private Label relationLabel3;
//</editor-fold>

    @FXML
    public void checkNumbersAction() {
        try {
            numbers.check(inputs);
        } catch (InvalidNumbersException ex) {
            basePane.setDisable(true);
            basePane.setOpacity(0.3);
            alertPane.setVisible(true);
            alertLabel.setText(MISSING_NUMBER);
        }
    }
    
    @FXML
    public void handleAlertButton() {
        alertPane.setVisible(false);
        alertLabel.setText("");
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
}
