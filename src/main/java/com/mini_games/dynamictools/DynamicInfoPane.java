package com.mini_games.dynamictools;

import java.util.function.Consumer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class DynamicInfoPane {
    
    private Group group;
    private final double HEIGHT = 211;
    private final double WIDTH = 308;
    private final Consumer consumer;
    //private Pane infoPane;
    private Rectangle rectangle;
    private ImageView imageView;
    private Label label;
    private Button button;
    
    public DynamicInfoPane(Pane gamePane, Consumer consumer) {
        group = new Group();
        
        setInfoPane(gamePane);
        setRectangle();
        setLabel();
        setButton();
        setImageView();
        this.consumer = consumer;
        group.getChildren().addAll(rectangle, imageView, label, button);
        gamePane.getChildren().add(group);
        group.setVisible(false);
    }
    
    public void show() {
        group.toFront();
        group.setVisible(true);
        group.setDisable(false);
        group.setOpacity(10);
        System.out.println("Test: "+ rectangle.getOpacity());
    }
    
    public void setImage(Image image) {
        imageView.setImage(image);
        imageView.setVisible(true);
    }
    
    public void setLabelText(String text) {
        label.setText(text);
    }
    
    public void setLabelColor(Color color) {
        label.setTextFill(color);
    }
    
    public void setLabelLeterSize(int size) {
        label.setFont(new Font(size));
    }
    
    private void restoreDefault() {
        label.setText("");
        label.setTextFill(Color.BLACK);
        label.setFont(new Font(14));
        imageView.setVisible(false);

    }

    private void setInfoPane(Pane gamePane) {
        //group = new Pane();
       // group.setPrefSize(WIDTH, HEIGHT);
        group.setLayoutX(74);
        group.setLayoutY(159);
    }

    private void setRectangle() {
        rectangle = new Rectangle(WIDTH, HEIGHT);
        rectangle.setArcHeight(50);
        rectangle.setArcWidth(50);
        rectangle.setFill(Color.valueOf("#d4d7da"));
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeType(StrokeType.INSIDE);
    }
    
    private void setLabel() {
        label = new Label();
        label.setLayoutX(15);
        label.setLayoutY(15);
        //label.setPrefSize(infoPane.getPrefWidth()-25, 115);
        //label.setTextAlignment(TextAlignment.CENTER);
        label.setWrapText(true);
    }

    private void setButton() {
        button = new Button("Ok!");
        button.setPrefSize(60, 15);
        //button.setLayoutX(infoPane.getPrefWidth() / 2 - button.getPrefWidth() / 2);
        System.out.println(button.getPrefWidth());
        button.setLayoutY(160);
        button.setOnAction(e -> handleDIPButton());
    }

    private void setImageView() {
        imageView = new ImageView();
        imageView.setFitHeight(HEIGHT);
        imageView.setFitWidth(WIDTH);
        imageView.setEffect(new GaussianBlur());
    }

    private void handleDIPButton() {
        group.setVisible(false);
        restoreDefault();
        consumer.accept(this);
    }
    
    
}
