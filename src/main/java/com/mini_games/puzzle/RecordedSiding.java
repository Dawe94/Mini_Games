package com.mini_games.puzzle;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Deque;
import java.util.ArrayList;
import java.util.Objects;

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
	
	public boolean moveUp() {
		if (blankPos - RATIO >= 0) {
			puzzle.swap(blankPos, blankPos - RATIO);
			recorder.addLast(blankPos);
			blankPos = blankPos - RATIO;
			return true;
		}
		return false;
	}
	
	public boolean moveDown() {
		if (blankPos + RATIO < puzzle.getSize()) {
			puzzle.swap(blankPos, blankPos + RATIO);
			recorder.addLast(blankPos);
			blankPos = blankPos + RATIO;
			return true;
		}
		return false;
	}
	
	public boolean moveLeft() {
		if (blankPos % RATIO != 0) {
			puzzle.swap(blankPos, blankPos - 1);
			recorder.addLast(blankPos);
			blankPos = blankPos - 1;
			return true;
		}
		return false;
	}
	
	public boolean moveRight() {
		if ((blankPos + 1) % RATIO != 0) {
			puzzle.swap(blankPos, blankPos + 1);
			recorder.addLast(blankPos);
			blankPos = blankPos + 1;
			return true;
		}
		return false;
	}
	
	public boolean replace() {
		if (!recorder.isEmpty()) {
			Integer element = recorder.removeLast();
			puzzle.swap(blankPos, element);
			blankPos = element;
			return true;
		}
		return false;
	}
	
	public void solve() {
		deleteDoubleMills();
		while (replace());
	}
	
	public void shuffle() {
		int index = 0;
		int counter = 0;
		Direction current = Direction.getRandomDirection();
		Direction prev = current;
		while (index < 50) {
			if (move(current)) {
				prev = current;
				index++;
			}
			counter++;
			current = Direction.getNonOppositeRandomDirection(prev);
		}
		System.out.println(counter);
	}
	
	private boolean move(Direction direction) {
		switch (direction) {
			case UP:
			return moveUp();
			case DOWN:
			return moveDown();
			case LEFT:
			return moveLeft();
			case RIGHT:
			return moveRight();
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