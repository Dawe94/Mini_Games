package com.mini_games.puzzle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PuzzlePart {
    
    private ImageView part;
    private final Coordinates originalPosition;
    
    public PuzzlePart(Image image, Coordinates position) {
        part = new ImageView(image);
        this.originalPosition = position;
        restorePosition();
    }
    
    public void setViewPort(double layoutY, double layoutX, double height, double width) {
        part.setViewport(new javafx.geometry.Rectangle2D(layoutX, layoutY, width, height));
    }
    
    public void setSize(double height, double width) {
        part.setFitHeight(height);
        part.setFitWidth(width);
    }
    
    public void decrementSize() {
        setSize(part.getFitHeight() * 0.97, part.getFitWidth() * 0.97);
        
    }
    
    public void setPosition(Coordinates coordinates) {
        part.setLayoutY(coordinates.getRow());
        part.setLayoutX(coordinates.getColumn());
    }
    
    public Coordinates getPositon() {
        return new Coordinates(part.getLayoutY(), part.getLayoutX());
    }
    
    public void changePosition(PuzzlePart other) {
        Coordinates otherPosition = other.getPositon();
        other.setPosition(this.getPositon());
        this.setPosition(otherPosition);
    }
    
    public void restorePosition() {
        this.setPosition(originalPosition);
    }

    public ImageView getImagePart() {
        return part;
    }
    
    public boolean isEmpty() {
        return part.getImage() == null;
    }
    
    
    
}
