package com.mini_games.puzzle;

import com.mini_games.Coordinates;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Puzzle {

    public static Puzzle createPuzzle(Pane puzzlePane, Image image, PuzzleScale scale) {
        return new Puzzle(puzzlePane, image, scale);
    }

    private final List<PuzzlePart> partList;
    private final PuzzleScale scale;
    private final TranslateTransition animation;
    private boolean animationRunning;

    public Puzzle(Pane puzzlePane, Image image, PuzzleScale scale) {
        partList = new ArrayList<>();
        this.scale = scale;
        buildPuzzlePane(puzzlePane, image);
        partList.get(scale.getRatio() * scale.getRatio() - 1).hideImage();
        animation = new TranslateTransition(Duration.seconds(0.2));
        animation.setOnFinished(eh -> setAnimationFalse());
    }

    public boolean isAnimationRunning() {
        return animationRunning;
    }

    public void setAnimationFalse() {
        animationRunning = false;
    }

    public List<PuzzlePart> getPartList() {
        return partList;
    }

    public int getBlankElement() {
        for (int i = 0; i < partList.size(); i++) {
            if (partList.get(i).isBlank()) {
                return i;
            }
        }
        return -1;
    }

    public int getSize() {
        return partList.size();
    }

    public PuzzleScale getPuzzleScale() {
        return scale;
    }

    public TranslateTransition getAnimation() {
        return animation;
    }

    public void showBlank() {
        int blankPos = getBlankElement();
        if (blankPos >= 0) {
            partList.get(blankPos).showImage();
        }
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < partList.size() * 100; i++) {
            int first = random.nextInt(partList.size());
            int second = random.nextInt(partList.size());
            swap(first, second, false);
        }
    }

    public void solve() {
        partList.stream().forEach(PuzzlePart::restorePosition);
    }

    public boolean isReady() {
        for (int i = 0; i < partList.size(); i++) {
            if (partList.get(i).getPlace() != i + 1) {
                return false;
            }
        }
        return true;
    }

    public boolean swap(int first, int second, boolean withAnimation) {
        if (withAnimation) {
            if (!animationRunning) {
                animationRunning = true;
                Collections.swap(partList, first, second);
                partList.get(first).changePosition(partList.get(second), animation);
                return true;
            }
            return false;
        } else {
            Collections.swap(partList, first, second);
            partList.get(first).changePosition(partList.get(second));
            return true;
        }
    }

    private void buildPuzzlePane(Pane puzzlePane, Image image) {
        double sizeOfPart = puzzlePane.getHeight() / scale.getRatio();
        double currHeight = 0;
        double currWidth = 0;
        int numOfParts = scale.getRatio() * scale.getRatio();
        for (int i = 0; i < numOfParts; i++) {
            Coordinates position = new Coordinates(currHeight, currWidth);
            PuzzlePart currentPart;
            currentPart = new PuzzlePart(image, position, i + 1);
            currentPart.showPlaceOnImage(scale);
            currentPart.setSize(sizeOfPart, sizeOfPart);
            currentPart.getImagePart().setStyle("-fx-border-color: red;");
            currentPart.setViewPort(currHeight, currWidth, sizeOfPart, sizeOfPart);
            currentPart.decrementSize();
            partList.add(currentPart);
            puzzlePane.getChildren().add(currentPart.getImagePane());
            currHeight = (i + 1) % scale.getRatio() == 0 ? currHeight + sizeOfPart : currHeight;
            currWidth = (i + 1) % scale.getRatio() == 0 ? 0 : currWidth + sizeOfPart;
        }
    }

}
