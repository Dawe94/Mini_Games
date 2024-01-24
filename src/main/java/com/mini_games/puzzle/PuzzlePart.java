package com.mini_games.puzzle;

import com.mini_games.Coordinates;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class PuzzlePart {
    
    private final Rectangle clipRectangle;
    private final ImageView imageView;
    private final Coordinates originalPosition;
    
    public PuzzlePart(Image image, Coordinates position) {
        clipRectangle = new Rectangle();
        imageView = new ImageView(image);
        this.originalPosition = position;
        restorePosition();     
    }
    
    public PuzzlePart(Coordinates position) {
        this(null, position);
    }
    
    public void setViewPort(double layoutY, double layoutX, double height, double width) {
        imageView.setViewport(new javafx.geometry.Rectangle2D(layoutX, layoutY, width, height));
    }
    
    public void setSize(double height, double width) {
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        clip();
    }
    
    public void decrementSize() {
        setSize(imageView.getFitHeight() * 0.97, imageView.getFitWidth() * 0.97);   
    }
    
    public void setPosition(Coordinates coordinates) {
        imageView.setLayoutY(coordinates.getRow());
        imageView.setLayoutX(coordinates.getColumn());
    }
    
    public Coordinates getPositon() {
        return new Coordinates(imageView.getLayoutY(), imageView.getLayoutX());
        
    }
    
    public void changePosition(PuzzlePart other) {
        Coordinates otherPosition = other.getPositon();
        other.setPosition(this.getPositon());
        this.setPosition(otherPosition);
    }
    
    public void restorePosition() {
        this.setPosition(originalPosition);
    }
    
    public boolean isOnPlace() {
        return getPositon().equals(originalPosition);
    }

    public ImageView getImagePart() {
        return imageView;
    }
    
    public boolean isBlank() {
        return imageView.getImage() == null;
    }
    
    private void clip() {
        clipRectangle.setWidth(imageView.getFitWidth());
        clipRectangle.setHeight(imageView.getFitHeight());
        clipRectangle.setArcWidth(20);
        clipRectangle.setArcHeight(20);
        imageView.setClip(clipRectangle);
    }
    
}
