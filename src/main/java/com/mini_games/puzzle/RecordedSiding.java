package com.mini_games.puzzle;

import java.util.List;
import java.util.LinkedList;
import java.util.Deque;
import java.util.ArrayList;
import java.util.Objects;
import javafx.application.Platform;

public class RecordedSiding {

    private final Puzzle puzzle;
    private Deque<Integer> recorder = new LinkedList<>();
    private final int RATIO;
    private int blankPos;

    public RecordedSiding(Puzzle puzzle) {
        this.puzzle = puzzle;
        RATIO = puzzle.getPuzzleScale().getRatio();
        blankPos = puzzle.getBlankElement();
    }

    public boolean moveUp(boolean withAnimation) {
        if (blankPos - RATIO >= 0) {
            puzzle.swap(blankPos, blankPos - RATIO, withAnimation);
            recorder.addLast(blankPos);
            blankPos = blankPos - RATIO;
            return true;
        }
        return false;
    }

    public boolean moveDown(boolean withAnimation) {
        if (blankPos + RATIO < puzzle.getSize()) {
            puzzle.swap(blankPos, blankPos + RATIO, withAnimation);
            recorder.addLast(blankPos);
            blankPos = blankPos + RATIO;
            return true;

        }
        return false;
    }

    public boolean moveLeft(boolean withAnimation) {
        if (blankPos % RATIO != 0) {
            puzzle.swap(blankPos, blankPos - 1, withAnimation);
            recorder.addLast(blankPos);
            blankPos = blankPos - 1;
            return true;
        }
        return false;
    }

    public boolean moveRight(boolean withAnimation) {
        if ((blankPos + 1) % RATIO != 0) {
            puzzle.swap(blankPos, blankPos + 1, withAnimation);
            recorder.addLast(blankPos);
            blankPos = blankPos + 1;
            return true;
        }
        return false;
    }

    public boolean replace(boolean withAnimation) {
        if (!recorder.isEmpty() && !puzzle.isAnimationRunning()) {            
            Integer element = recorder.removeLast();
            puzzle.swap(blankPos, element, withAnimation);
            blankPos = element;
            return true;
        }
        return false;
    }

    public void solve(boolean withAnimation) {
        deleteDoubleMills();
        while (replace(withAnimation)) {
            
        }
    }

    public void shuffle(boolean withAnimation) {
        int index = 0;
        int counter = 0;
        Direction current = Direction.getRandomDirection();
        Direction prev = current;
        while (index < 50) {
            if (move(current, withAnimation)) {
                prev = current;
                index++;
            }
            counter++;
            current = Direction.getNonOppositeRandomDirection(prev);
        }
        System.out.println(counter);
    }

    private boolean move(Direction direction, boolean withAnimation) {
        switch (direction) {
            case UP:
                return moveUp(withAnimation);
            case DOWN:
                return moveDown(withAnimation);
            case LEFT:
                return moveLeft(withAnimation);
            case RIGHT:
                return moveRight(withAnimation);
            default:
                return false;
        }
    }

    private void deleteDoubleMills() {
        List<Integer> list = new ArrayList<>(recorder);
        for (int i = 2; i < list.size(); i++) {
            if (Objects.equals(list.get(i), list.get(i - 2))) {
                list.remove(i);
                list.remove(i - 1);
                i -= 1;
            }
        }
        recorder.clear();
        for (Integer element : list) {
            recorder.addLast(element);
        }
    }

}
