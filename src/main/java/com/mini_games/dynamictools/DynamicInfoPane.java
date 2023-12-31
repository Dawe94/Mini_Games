package com.mini_games.dynamictools;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class DynamicInfoPane {
    
    private final double HEIGHT = 211;
    private final double WIDTH = 308;
    private Pane infoPane;
    private Rectangle rectangle;
    private ImageView imageView;
    private Label label;
    private Button button;
    
    public DynamicInfoPane(Pane gamePane) {
        setInfoPane(gamePane);
        setRectangle();
        setLabel();
        setButton();
        setImageView();
        infoPane.getChildren().addAll(rectangle, imageView, label, button);
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
        label.setFont(new Font(12));
        imageView.setVisible(false);

    }

    private void setInfoPane(Pane gamePane) {
        infoPane = new Pane();
        infoPane.setPrefSize(WIDTH, HEIGHT);
        infoPane.setLayoutX(74);
        infoPane.setLayoutY(159);
        gamePane.getChildren().add(infoPane);
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
        label.setPrefSize(infoPane.getPrefWidth()-25, 115);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setWrapText(true);
    }

    private void setButton() {
        button = new Button("Ok!");
        button.setPrefSize(60, 15);
        button.setLayoutX(infoPane.getPrefWidth() / 2 - button.getPrefWidth() / 2);
        System.out.println(button.getPrefWidth());
        button.setLayoutY(160);
        button.setOnAction(e -> handleDIPButton());
    }

    private void setImageView() {
        imageView = new ImageView();
        imageView.setFitHeight(HEIGHT);
        imageView.setFitWidth(WIDTH);
        imageView.setOpacity(0.3);
        imageView.setEffect(new GaussianBlur());
    }

    private void handleDIPButton() {
        infoPane.setVisible(false);
        restoreDefault();
    }
    
    
}
