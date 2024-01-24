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
    
    public Coordinates getPosition() {
        return new Coordinates(imageView.getTranslateY(), imageView.getTranslateX());       
    }
    
    public void setPosition(Coordinates coordinates) {
        imageView.setTranslateY(coordinates.getRow());
        imageView.setTranslateX(coordinates.getColumn());
    }   
    
    public void changePosition(PuzzlePart other) {
        Coordinates otherPosition = other.getPosition();
        other.setPosition(this.getPosition());
        this.setPosition(otherPosition);
    }
    
    public void setPosition(Coordinates other, TranslateTransition animation) {
        animation.setNode(imageView);
        animation.setToX(other.getColumn());
        animation.setToY(other.getRow());
        animation.play();
        System.out.println("Y = "+other.getRow()+", X = "+other.getColumn());
        //imageView.setLayoutY(coordinates.getRow());
        //imageView.setLayoutX(coordinates.getColumn());
    }   
    
    public void changePosition(PuzzlePart other, TranslateTransition animation) {
        Coordinates otherPosition = other.getPosition();
        other.setPosition(this.getPosition(), animation);
        this.setPosition(otherPosition);
    }
    
    public void restorePosition() {
        this.setPosition(originalPosition);
    }
    
    public boolean isOnPlace() {
        return getPosition().equals(originalPosition);
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
