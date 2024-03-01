package com.mini_games.puzzle;

import com.mini_games.Coordinates;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class PuzzlePart {
    
    private final Pane pane;
    private final Rectangle clipRectangle;
    private final ImageView imageView;
    private final Coordinates originalPosition;
    private final int place;
    
    public PuzzlePart(Image image, Coordinates position, int place) {
        pane = new Pane();
        clipRectangle = new Rectangle();
        imageView = new ImageView(image);       
        pane.getChildren().add(imageView);
        imageView.fitHeightProperty().bind(pane.heightProperty());
        imageView.fitWidthProperty().bind(pane.widthProperty());             
        this.originalPosition = position;
        this.place = place;
        restorePosition();     
    }
    
    public PuzzlePart(Coordinates position, int place) {
        this(null, position, place);
    }
    
    public void setViewPort(double layoutY, double layoutX, double height, double width) {
        imageView.setViewport(new javafx.geometry.Rectangle2D(layoutX, layoutY, width, height));
    }
    
    public void setSize(double height, double width) {
       pane.setPrefSize(width, height);
       clip();
    }
    
    public void showPlaceOnImage(PuzzleScale scale) {
        int fontSize = 90 / scale.getRatio();
        Label label = new Label(String.valueOf(place));
        label.setStyle("-fx-text-fill: white; -fx-opacity: 0.85; -fx-font-size: "+fontSize+"; -fx-font-weight: bold;");
        pane.getChildren().add(label);  
    }
    
    public void decrementSize() {
       pane.setPrefSize(pane.getPrefWidth() * 0.97, pane.getPrefHeight() * 0.97);
       clip();
    }
    
    public Coordinates getPosition() {
     return new Coordinates(pane.getTranslateY(), pane.getTranslateX());
    }
    
    public void setPosition(Coordinates coordinates) {
        pane.setTranslateY(coordinates.getRow());
        pane.setTranslateX(coordinates.getColumn());
    }   
    
    public void changePosition(PuzzlePart other) {
        Coordinates otherPosition = other.getPosition();
        other.setPosition(this.getPosition());
        this.setPosition(otherPosition);
    }
    
    public void setPosition(Coordinates other, TranslateTransition animation) {
        animation.setNode(pane);
        animation.setToX(other.getColumn());
        animation.setToY(other.getRow());
        animation.play();
    }   
    
    public void changePosition(PuzzlePart other, TranslateTransition animation) {
        Coordinates otherPosition = other.getPosition();
        other.setPosition(this.getPosition());
        this.setPosition(otherPosition, animation);
    }
    
    public void restorePosition() {
        this.setPosition(originalPosition);
    }
    
    public int getPlace() {
        return place;
    }

    public ImageView getImagePart() {
        return imageView;
    }
    
    public Pane getImagePane() {
        return pane;
    }
    
    public void hideImage() {
        pane.setVisible(false);
    }
    
    public void showImage() {
        pane.setVisible(true);
    }
    
    public boolean isBlank() {
        return !pane.isVisible();
    }
    
    private void clip() {       
        clipRectangle.setWidth(pane.getPrefWidth());
        clipRectangle.setHeight(pane.getPrefHeight());
        clipRectangle.setArcWidth(20);
        clipRectangle.setArcHeight(20);
        pane.setClip(clipRectangle);
    }
    
}
