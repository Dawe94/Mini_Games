
package com.mini_games.puzzle;

public enum PuzzleScale {
    
    THREE_TO_THREE(3),
    FOUR_TO_FOUR(4),
    FIVE_TO_FIVE(5);
    
    private PuzzleScale(int scale) {
        this.scale = scale;
    }
    
    private int scale;
    
    public int getScale() {
        return scale;
    }
    
}
