package com.number_guessing;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class MainController implements Initializable{

    private final Random RANDOM = new Random();
    private int[] numbers = new int[4];
    
    //<editor-fold defaultstate="collapsed" desc="FXML Objects">
    @FXML
    private TextField number1;
    @FXML
    private TextField number2;
    @FXML
    private TextField number3;
    @FXML
    private TextField number4;
//</editor-fold>

    @FXML
    public void checkNumbersAction() {
        System.out.println("nums: "+number1.getText()+", "+number2.getText()+", "
        +number3.getText()+", "+number4.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numbers = generateNumbers(numbers.length);
    }
    
    private int[] generateNumbers(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = RANDOM.nextInt(10);
        }
        return array;
    }
}
