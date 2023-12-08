package com.number_guessing;

import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class MainController implements Initializable{

    private final Random RANDOM = new Random();
    private int[] numbers = new int[4];
    private int[] inputNumbers = {-1, -1, -1, -1};
    
    //<editor-fold defaultstate="collapsed" desc="FXML Objects">
    @FXML
    private Pane basePane;
    @FXML
    private Pane alertPane;
    @FXML
    private Label alertLabel;
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
        setValues(0, input1);
        setValues(1, input2);
        setValues(2, input3);
        setValues(3, input4);
        //int[] inputs = getInputNumbers();
        //System.out.println("nums: "+Arrays.toString(inputs));
    }
    
    public void handleAlertButton() {
        alertPane.setVisible(false);
        basePane.setDisable(false);
        basePane.setOpacity(1);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numbers = generateNumbers(numbers.length);
        hints.setText(getHints());
    }
    
    private void setValues(int index, TextField tf) {
        if (inputNumbers[index] != numbers[index]) {
            try {
                inputNumbers[index] = Integer.parseInt(tf.getText().substring(0,1));
            } catch (Exception ex) {
                basePane.setDisable(true);
                basePane.setOpacity(0.3);
                alertPane.setVisible(true);
                alertLabel.setText("Invalid input!");
            }
        }
        tf.setText(inputNumbers[index]+"");
        setColor(index, tf);
        System.out.println("Generated: "+Arrays.toString(numbers)+" My numbers: "+Arrays.toString(inputNumbers));
    }
    
    private void setColor(int index, TextField tf) {
        if (inputNumbers[index] == numbers[index]) {         
            tf.setStyle("-fx-text-fill: green;");
        } else if (contains(inputNumbers[index])) {
            tf.setStyle("-fx-text-fill: orange;");
        } else {
            tf.setStyle("-fx-text-fill: red;");
        }    
    }
    
    private boolean contains(int input) {
        for (int i = 0; i < numbers.length; i++) {
            if (input == numbers[i]) {
                return true;
            }
        }
        return false;
    }
    
    private int[] getInputNumbers() {
        int[] array = new int[4];
        try {
            array[0] = Integer.parseInt(input1.getText().substring(0,1));
            array[1] = Integer.parseInt(input2.getText().substring(0,1));
            array[2] = Integer.parseInt(input3.getText().substring(0,1));
            array[3] = Integer.parseInt(input4.getText().substring(0,1));
            
        } catch (Exception ex) {
            basePane.setDisable(true);
            basePane.setOpacity(0.3);
            alertPane.setVisible(true);
            alertLabel.setText("Invalid input!");
        }
        return array;
    }
    
    private String getHints() {
        StringBuilder sb = new StringBuilder();
        sb.append("The first number is ").append(relation(numbers[0], numbers[1])).append(" the second."+"\n");
        sb.append("The second number is ").append(relation(numbers[1], numbers[2])).append(" the third."+"\n");
        sb.append("The third number is ").append(relation(numbers[2], numbers[3])).append(" the fourth."+"\n");
        return sb.toString();
    }
    
    private String relation(int first, int second) {
        if (first < second) {
            return "smaller than";
        } else if (first > second) {
            return "bigger than";
        } else {
            return "is the same as";
        }
    }
    
    private int[] generateNumbers(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = RANDOM.nextInt(10);
        }
        return array;
    }
}
