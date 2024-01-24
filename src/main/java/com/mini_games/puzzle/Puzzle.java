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
    private final TranslateTransition animation;
    private boolean isAnimationRunning;

    public Puzzle(Pane puzzlePane, Image image, PuzzleScale scale) {
        partList = new ArrayList<>();
        buildPuzzlePane(puzzlePane, image, scale);
        animation = new TranslateTransition(Duration.seconds(0.3));
        animation.setOnFinished(eh -> isAnimationRunning = false);

    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < partList.size() * 2; i++) {
            int first = random.nextInt(partList.size());
            int second = random.nextInt(partList.size());
            swap(first, second);
        }
    }

    public void solve() {
        partList.stream().forEach(PuzzlePart::restorePosition);
    }

    public boolean isReady() {
        return partList.stream().allMatch(PuzzlePart::isOnPlace);
    }

    public void swap(int first, int second) {
        Collections.swap(partList, first, second);
        partList.get(first).changePosition(partList.get(second));
    }

    public void swap(int first, int second, boolean withAnimation) {
        if (!isAnimationRunning) {
            isAnimationRunning = true;
            Collections.swap(partList, first, second);
            partList.get(first).changePosition(partList.get(second), animation);
        }
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

    private void buildPuzzlePane(Pane puzzlePane, Image image, PuzzleScale scale) {
        double heightOfAPart = puzzlePane.getHeight() / scale.getScale();
        double widthOfAPart = puzzlePane.getWidth() / scale.getScale();
        int numOfParts = scale.getScale() * scale.getScale();
        double currHeight = 0;
        double currWidth = 0;
        for (int i = 0; i < numOfParts; i++) {
            Coordinates position = new Coordinates(currHeight, currWidth);
            PuzzlePart currentPart = i == numOfParts - 1 ? new PuzzlePart(position) : new PuzzlePart(image, position);
            currentPart.setSize(heightOfAPart, widthOfAPart);
            currentPart.getImagePart().setStyle("-fx-border-color: red;");
            currentPart.setViewPort(currHeight, currWidth, heightOfAPart, widthOfAPart);
            currentPart.decrementSize();
            partList.add(currentPart);
            puzzlePane.getChildren().add(currentPart.getImagePart());
            currHeight = (i + 1) % scale.getScale() == 0 ? currHeight + heightOfAPart : currHeight;
            currWidth = (i + 1) % scale.getScale() == 0 ? 0 : currWidth + widthOfAPart;
        }
    }

}
