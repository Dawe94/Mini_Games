package com.mini_games.puzzle;

import java.util.Random;

public enum Direction {
		
	UP, DOWN, RIGHT, LEFT;
	
	private static Random RANDOM = new Random();
	
	public static Direction getRandomDirection() {
		return getDirection(RANDOM.nextInt(4));
	}
	
	public static Direction getNonOppositeRandomDirection(Direction prev) {
		int randomNumber;
		Direction next;
		do {
			randomNumber = RANDOM.nextInt(4);
			next = getDirection(randomNumber);
		} while (isOpposite(prev, next));
		return next;
	}
	
	private static Direction getDirection(int randomNumber) {
		switch (randomNumber) {
			case 0:
			return Direction.UP;
			case 1:
			return Direction.DOWN;
			case 2:
			return Direction.RIGHT;
			case 3:
			return Direction.LEFT;
			default:
			return null;
		}
	}
	
	private static boolean isOpposite(Direction prev, Direction next) {
		switch(prev) {
			case UP:
			return next == Direction.DOWN;
			case DOWN:
			return next == Direction.UP;
			case RIGHT:
			return next == Direction.LEFT;
			case LEFT:
			return next == Direction.RIGHT;
			default: 
			return false;
		}
	}	
	
}