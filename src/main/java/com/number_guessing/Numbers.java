
package com.number_guessing;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import javafx.scene.control.TextField;

public class Numbers {
    
    private static final Random RANDOM = new Random();
    
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
    
    public void check(List<TextField> inputNumbers) {
        for (int index = 0; index < numbers.size(); index++) {
        if (Objects.equals(Integer.valueOf(inputNumbers.get(index).getText()), numbers.get(index))) {
            inputNumbers.get(index).setDisable(true);
            inputNumbers.get(index).setText(inputNumbers.get(index).getText());
        }
        color(index, inputNumbers.get(index));
        }
    }
    
    private void color(int index, TextField tf) {
        if (Objects.equals(Integer.valueOf(tf.getText()), numbers.get(index))) {         
            tf.setStyle("-fx-text-fill: green;");
        } else if (numbers.contains(Integer.valueOf(tf.getText()))) {
            tf.setStyle("-fx-text-fill: orange;");
        } else {
            tf.setStyle("-fx-text-fill: red;");
        }    
    }
    
}
