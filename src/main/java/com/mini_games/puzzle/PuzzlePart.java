package com.mini_games.puzzle;

import com.mini_games.Coordinates;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class PuzzlePart {
    
    private Pane pane;
    private Rectangle clipRectangle;
    private ImageView imageView;
    private final Coordinates originalPosition;
    
    public PuzzlePart(Image image, Coordinates position) {
        pane = new Pane();
        clipRectangle = new Rectangle();
        imageView = new ImageView(image);
        pane.getChildren().addAll(imageView);
        this.originalPosition = position;
        restorePosition();     
    }
    
    public PuzzlePart(Coordinates position) {
        pane = new Pane();
        clipRectangle = new Rectangle();
        imageView = new ImageView();
        pane.getChildren().addAll(imageView);
        this.originalPosition = position;
        restorePosition();     
    }
    
    public void setViewPort(double layoutY, double layoutX, double height, double width) {
        imageView.setViewport(new javafx.geometry.Rectangle2D(layoutX, layoutY, width, height));
    }
    
    public void setSize(double height, double width) {
        pane.setPrefHeight(height);
        pane.setPrefWidth(width);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        clip();
    }
    
    public void decrementSize() {
        setSize(pane.getPrefHeight() * 0.97, pane.getPrefWidth() * 0.97);   
    }
    
    public void setPosition(Coordinates coordinates) {
        pane.setLayoutY(coordinates.getRow());
        pane.setLayoutX(coordinates.getColumn());
        imageView.setLayoutY(coordinates.getRow());
        imageView.setLayoutX(coordinates.getColumn());
    }
    
    public Coordinates getPositon() {
        return new Coordinates(pane.getLayoutY(), pane.getLayoutX());
    }
    
    public void changePosition(PuzzlePart other) {
        Coordinates otherPosition = other.getPositon();
        other.setPosition(this.getPositon());
        this.setPosition(otherPosition);
    }
    
    public final void restorePosition() {
        this.setPosition(originalPosition);
    }

    public ImageView getImagePart() {
        return imageView;
    }
    
    public boolean isEmpty() {
        return imageView.getImage() == null;
    }
    
    private void clip() {
        clipRectangle.setWidth(pane.getPrefWidth());
        clipRectangle.setHeight(pane.getPrefHeight());
        clipRectangle.setArcWidth(20);
        clipRectangle.setArcHeight(20);
        imageView.setClip(clipRectangle);
    }
    
}
