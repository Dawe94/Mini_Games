package com.mini_games.dynamictools;

import java.util.List;
import java.util.function.Consumer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class DynamicInfoPane {
    
    private final Pane infoPane;
    private final ImageView imageView;
    private final Label label;
    private final Button button;
    private final List<String> originalLabelStyle;
    private final List<String> originalImageViewStyle;
    
    DynamicInfoPane(Pane infoPane) {
        this.infoPane = infoPane;
        imageView = (ImageView)infoPane.lookup("#infoImage");
        originalImageViewStyle = imageView.getStyleClass();
        label = (Label)infoPane.lookup("#infoLabel");
        originalLabelStyle = label.getStyleClass();
        button = (Button)infoPane.lookup("#infoButton");
    }
    
    public void action(Consumer<DynamicInfoPane> consumer) {
        clean();
        consumer.accept(this);
    }
    
    public void setPaneVisible(boolean value) {
        infoPane.setVisible(value);
    }
    
    public void showImage() {
        imageView.setVisible(true);
    }
    
    public void setImage(Image image) {
        imageView.setImage(image);
    }
    
    public void setImageStyle(String cssStyle) {
        imageView.setStyle(cssStyle);
    }
    
    public void setImageEffect(Effect effect) {
        imageView.setEffect(effect);
    }
    
    public void setLabelText(String text) {
        label.setText(text);
    }
    
    public void setLabelStyle(String cssStyle) {
        label.setStyle(cssStyle);
    }
    
    public void setButtonAction(Consumer<DynamicInfoPane> consumer) {
        clean();
        button.setOnAction(eh -> consumer.accept(this));
    }
    
    public void setButtonStyle(String cssStyle) {
        button.setStyle(cssStyle);
    }
    
    private void clean() {
        label.setText("");
        label.getStyleClass().setAll(originalLabelStyle);
        imageView.getStyleClass().setAll(originalImageViewStyle);
        imageView.setEffect(null);
        imageView.setVisible(false);
    }
    
}
