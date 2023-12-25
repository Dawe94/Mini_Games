
package com.mini_games.guess_numbers;

import com.mini_games.InvalidNumbersException;
import com.mini_games.Numbers;
import com.mini_games.SubController;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public final class GuessNumberController implements SubController {
    
    private static GuessNumberController controller;
    
    private boolean gameOver;
    private int round;
    
    public static GuessNumberController getInstance(Pane pane) {
        System.out.println("HHHHHEEEEEERRRRREEEE111!");
        if (controller == null) {
            controller = new GuessNumberController(pane);
            System.out.println("HHHHHEEEEEERRRRREEEE222!");
        }
        return controller;
    }
    
    public GuessNumberController(Pane pane) {
        System.out.println("HHHHHEEEEEERRRRREEEE333!");
        unfold(pane);
        System.out.println("HHHHHEEEEEERRRRREEEE444!");
        setListener();
        System.out.println("HHHHHEEEEEERRRRREEEE555!");
        numbers = Numbers.generate(10);
        System.out.println("HHHHHEEEEEERRRRREEEE666!");
        getRelations();
        System.out.println("HHHHHEEEEEERRRRREEEE777!");
    }
    
    private List<TextField> inputs = new ArrayList<>();
    private List<Label> relations = new ArrayList<>();
    private Numbers numbers;
    
    @Override
    public void unfold(Pane pane) {
        HBox hbox = (HBox)pane.lookup("#hbox");
        System.out.println(hbox);
        for (int i = 1; i <= 4; i++) {
            inputs.add((TextField)hbox.lookup("#input"+i));
            if (i < 4) {
                relations.add((Label)hbox.lookup("#relationLabel"+i));
            }
        }
        System.out.println(inputs.toString());
    }
    
    public void checkNumbersAction(Pane basePane, Pane userinfoPane, Image WIN_IMAGE, Image LOSE_IMAGE) throws  InvalidNumbersException {
        if (numbers.check(inputs)) {
            getResult("Congratulation! You Win!", Color.GREEN, WIN_IMAGE, basePane, userinfoPane);
        } else if (++round >= 4) {
            getResult("You Lose!", Color.RED, LOSE_IMAGE, basePane, userinfoPane);
        }
    }
    
    public void handleOkButton(Pane basePane, Pane userInfoPane) {
        userInfoPane.setVisible(false);
        Label userInfoLabel = (Label)userInfoPane.lookup("#userInfoLabel");
        userInfoLabel.setText("");
        userInfoLabel.setTextFill(Color.BLACK);
        basePane.setDisable(false);
        basePane.setOpacity(1);
        if (gameOver) {
            restore(basePane, userInfoPane);
        }
    }
    
    @Override
    public void restore(Pane mainPane, Pane userInfoPane) {
        System.out.println(numbers.toString());
        gameOver = true;
        round = 0;
        numbers = Numbers.generate(10);
        ImageView resultView = (ImageView)userInfoPane.lookup("#resultImage");
        resultView.setVisible(false);
        Label userInfoLabel = (Label)userInfoPane.lookup("#userInfoLabel");
        userInfoLabel.setFont(new Font(14));
        for (TextField tf : inputs) {
            tf.clear();
            tf.setStyle("-fx-text-fill:black; -fx-background-color: #dcdcdc;");
            tf.setDisable(false);
        }
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
    
    private void getResult(String result, Color color, Image image, Pane basePane, Pane userInfoPane) {
        basePane.setDisable(true);
        basePane.setOpacity(0.3);
        userInfoPane.setVisible(true);
        Label userInfoLabel = (Label)userInfoPane.lookup("#userInfoLabel");
        userInfoLabel.setFont(new Font(24));
        userInfoLabel.setText(result);
        userInfoLabel.setTextFill(color);
        gameOver = true;
        ImageView resultImage = (ImageView)userInfoPane.lookup("#resultImage");
        resultImage.setVisible(true);
        resultImage.setImage(image);      
    }
    
    private void getRelations() {
        for (int i = 0; i < relations.size(); i++) {
            relations.get(i).setText(relation(numbers.get(i), numbers.get(i+1)));
        }
    }
    
    private String relation(int first, int second) {
        return first < second ? "<" : ">";
    }
    
}
