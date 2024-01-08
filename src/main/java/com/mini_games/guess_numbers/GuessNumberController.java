package com.mini_games.guess_numbers;

import com.mini_games.SubController;
import com.mini_games.dynamictools.DynamicBackButton;
import com.mini_games.dynamictools.DynamicInfoPane;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public final class GuessNumberController implements SubController {

    private static GuessNumberController controller;

    public static GuessNumberController getInstance(Pane gamePane, Pane mainPane) {
        if (controller == null) {
            controller = new GuessNumberController(gamePane, mainPane);
        }
        return controller;
    }

    private GuessNumberController(Pane gamePane, Pane mainPane) {
        this.gamePane = gamePane;
        numbers = Numbers.generate(10);
        unfold();
        setListener();
        getRelations();
        DynamicBackButton.getInstance().setOnAction(mainPane, gamePane);
        
    }

    private final List<TextField> inputs = new ArrayList<>();
    private final List<Label> relations = new ArrayList<>();
    private final Image WIN_IMAGE = new Image(getClass().getResourceAsStream("/com/mini_games/GOLD-BARS.jpg"));
    private final Image LOSE_IMAGE = new Image(getClass().getResourceAsStream("/com/mini_games/policeCar.jpg"));
    private final String MISSING_NUMBER = "You have to write a number in all of number fields!";
    private final Pane gamePane;
    private Numbers numbers;
    private boolean gameOver;
    private int round;

    @Override
    public void unfold() {
        //Unfold guessNumber Pane (Game Pane)
        HBox hbox = (HBox)checkedLookup(gamePane, "#hbox");
        for (int i = 1; i <= 4; i++) {
            inputs.add((TextField)checkedLookup(hbox, "#input" + i));
            if (i < 4) {
                relations.add((Label)checkedLookup(hbox, "#relationLabel" + i));
            }
        }
    }

    public void checkNumbersAction() {
        try {
            if (numbers.check(inputs)) {
                getResult("Congratulation! You Win!", Color.GREEN, WIN_IMAGE);
            } else if (++round >= 4) {
                getResult("You Lose!", Color.RED, LOSE_IMAGE);
            }
        } catch (InvalidNumbersException ex) {
            gamePane.setDisable(true);
            gamePane.setOpacity(0.3);
        }
    }

    public void handleOkButton() {
        
    }

    @Override
    public void restore() {
        DynamicBackButton.getInstance();
        gameOver = false;
        round = 0;
        numbers = Numbers.generate(10);
        getRelations();
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

    private void getResult(String result, Color color, Image image) {
        gamePane.setDisable(true);
        gamePane.setOpacity(0.3);
        gameOver = true;
    }

    private void getRelations() {
        for (int i = 0; i < relations.size(); i++) {
            relations.get(i).setText(relation(numbers.get(i), numbers.get(i + 1)));
        }
    }

    private String relation(int first, int second) {
        return first < second ? "<" : ">";
    }

    private Consumer onAction() {
        gamePane.setDisable(false);
        gamePane.setOpacity(1);
        if (gameOver) {
            restore();
        }
        return null;
    }

}
