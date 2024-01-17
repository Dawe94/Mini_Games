package com.mini_games.guess_numbers;

import com.mini_games.dynamictools.DynamicInfoPane;
import com.mini_games.dynamictools.DynamicTools;
import com.mini_games.interfaces.SubController;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public final class GuessNumberController implements SubController {

    private static GuessNumberController controller;

    public static GuessNumberController getInstance(Pane gamePane, Pane mainPane, DynamicTools dynamicTools) {
        if (controller == null) {
            controller = new GuessNumberController(gamePane, mainPane, dynamicTools);
        }
        return controller;
    }

    private GuessNumberController(Pane gamePane, Pane mainPane, DynamicTools dynamicTools) {
        this.gamePane = gamePane;
        this.mainPane = mainPane;
        this.dynamicTools = dynamicTools;
        dynamicTools.getBackButton().action(gamePane);
        dynamicTools.getInfoPane().setButtonAction(d -> handleOkButton(d));
        numbers = Numbers.generate(10);
        unfold();
        setListener();
        getRelations();
    }

    private final List<TextField> inputs = new ArrayList<>();
    private final List<Label> relations = new ArrayList<>();
    private final Image WIN_IMAGE = new Image(getClass().getResourceAsStream("/com/mini_games/GOLD-BARS.jpg"));
    private final Image LOSE_IMAGE = new Image(getClass().getResourceAsStream("/com/mini_games/policeCar.jpg"));
    private final String MISSING_NUMBER = "You have to write a number in all of number fields!";
    private final Pane gamePane;
    private final Pane mainPane;
    private Numbers numbers;
    private boolean gameOver;
    private int round;
    private final DynamicTools dynamicTools;

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
                getResult("Congratulation! You Win!", "green", WIN_IMAGE);
            } else if (++round >= 4) {
                getResult("You Lose!", "red", LOSE_IMAGE);
            }
        } catch (InvalidNumbersException ex) {
            gamePane.setDisable(true);
            gamePane.setOpacity(0.3);
            dynamicTools.getInfoPane().action(d -> {
                d.setLabelText(MISSING_NUMBER);
                d.setLabelStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                d.setPaneVisible(true);
            });
        }
    }

    public void handleOkButton(DynamicInfoPane dip) {
        dip.setPaneVisible(false);
        gamePane.setOpacity(1);
        gamePane.setDisable(false);
        if (gameOver) {
            restore();
        }
    }

    @Override
    public void restore() {
        dynamicTools.getBackButton().action(gamePane);
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

    private void getResult(String result, String color, Image image) {
        gamePane.setDisable(true);
        gamePane.setOpacity(0.3);
        gameOver = true;
        dynamicTools.getInfoPane().action(d -> {
            d.setPaneVisible(true);
            d.setLabelText(result);
            d.setLabelStyle("-fx-text-fill: "+color+"; -fx-font-size: 24px;");
            d.setImage(image);
            d.showImage();
            d.setImageStyle("-fx-opacity: 0.3;");
            d.setImageEffect(new GaussianBlur());
        });
    }

    private void getRelations() {
        for (int i = 0; i < relations.size(); i++) {
            relations.get(i).setText(relation(numbers.get(i), numbers.get(i + 1)));
        }
    }

    private String relation(int first, int second) {
        return first < second ? "<" : ">";
    }

}
