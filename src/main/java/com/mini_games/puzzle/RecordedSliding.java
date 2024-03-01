package com.mini_games.puzzle;

import java.util.List;
import java.util.LinkedList;
import java.util.Deque;
import java.util.ArrayList;
import java.util.Objects;

public class RecordedSliding implements Sliding {

    private final Puzzle puzzle;
    private final Deque<Integer> recorder = new LinkedList<>();
    private final int RATIO;
    private int blankPos;

    public RecordedSliding(Puzzle puzzle) {
        this.puzzle = puzzle;
        RATIO = puzzle.getPuzzleScale().getRatio();
        blankPos = puzzle.getBlankElement();
    }

    @Override
    public boolean moveUp(boolean withAnimation) {
        if (blankPos - RATIO >= 0) {
            return swap(blankPos - RATIO, withAnimation);
        }
        return false;
    }

    @Override
    public boolean moveDown(boolean withAnimation) {
        if (blankPos + RATIO < puzzle.getSize()) {
            return swap(blankPos + RATIO, withAnimation);
        }
        return false;
    }

    @Override
    public boolean moveLeft(boolean withAnimation) {
        if (blankPos % RATIO != 0) {
            return swap(blankPos - 1, withAnimation);
        }
        return false;
    }

    @Override
    public boolean moveRight(boolean withAnimation) {
        if ((blankPos + 1) % RATIO != 0) {
            return swap(blankPos + 1, withAnimation);
        }
        return false;
    }

    @Override
    public boolean undo(boolean withAnimation) {
        if (!recorder.isEmpty() && !puzzle.isAnimationRunning()) {
            Integer element = recorder.removeLast();
            puzzle.swap(blankPos, element, withAnimation);
            blankPos = element;
            return true;
        }
        return false;
    }

    @Override
    public void solve(boolean withAnimation) {
        if (!puzzle.isReady()) {
            deleteDoubleMills();
            puzzle.getAnimation().setOnFinished(eh -> {
                puzzle.setAnimationFalse();
                solve();
            });
            solve();
        }
    }

    @Override
    public void shuffle() {
        int index = 0;
        Direction current = Direction.getRandomDirection();
        Direction prev = current;
        int numberOfSwaps = puzzle.getPartList().size() * 3;
        while (index < numberOfSwaps) {
            if (move(current)) {
                prev = current;
                index++;
            }
            current = Direction.getNonOppositeRandomDirection(prev);
        }
    }

    private boolean swap(int newBlankPos, boolean withAnimation) {
        puzzle.swap(blankPos, newBlankPos, withAnimation);
        recorder.addLast(blankPos);
        blankPos = newBlankPos;
        return true;
    }

    private void solve() {
        if (!recorder.isEmpty()) {
            undo(true);
        }
    }

    private boolean move(Direction direction) {
        return switch (direction) {
            case UP -> moveUp(false);
            case DOWN -> moveDown(false);
            case LEFT -> moveLeft(false);
            case RIGHT -> moveRight(false);
            default -> false;
        };
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
