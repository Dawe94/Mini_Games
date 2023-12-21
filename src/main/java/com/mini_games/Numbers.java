
package com.mini_games;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.StringJoiner;
import javafx.scene.control.TextField;

public class Numbers {
    
    private static final Random RANDOM = new Random();
    private int numOfCorrectNumbers;
    
    public static Numbers generate() {
        Set<Integer> set = new LinkedHashSet<>();
        while (set.size() < 4) {
            set.add(RANDOM.nextInt(10));
        }
        return new Numbers(new ArrayList<>(set));
    }
    
    private final List<Integer> numbers;
    
    private Numbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
    
    public int get(int index) {
        return numbers.get(index);
    }
    
    public boolean check(List<TextField> inputNumbers) throws InvalidNumbersException {
        int index = 0;
        try {
        for (; index < numbers.size(); index++) {
            if (inputNumbers.get(index).isDisabled()) continue;
            if (Objects.equals(Integer.valueOf(inputNumbers.get(index).getText()), numbers.get(index))) {
                inputNumbers.get(index).setDisable(true);
                inputNumbers.get(index).setText(inputNumbers.get(index).getText());
                numOfCorrectNumbers++;
            }
            color(index, inputNumbers.get(index));
        }
        if (numOfCorrectNumbers >= inputNumbers.size()) return true;
        } catch (Exception ex) {
            throw new InvalidNumbersException("Exception while check "+ ++index+"-th TextField!");
        }
        return false;
    }
    
    private void color(int index, TextField tf) {
        if (Objects.equals(Integer.valueOf(tf.getText()), numbers.get(index))) {         
            tf.setStyle("-fx-text-fill: green; -fx-background-color: #dcdcdc");
        } else if (numbers.contains(Integer.valueOf(tf.getText()))) {
            tf.setStyle("-fx-text-fill: orange; -fx-background-color: #dcdcdc;");
        } else {
            tf.setStyle("-fx-text-fill: red; -fx-background-color: #dcdcdc;");
        }    
    }
    
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ");
        for (Integer number : numbers) {
            sj.add(number.toString());
        }
        return sj.toString();
    }
    
}