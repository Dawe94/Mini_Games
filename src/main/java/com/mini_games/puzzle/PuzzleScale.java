
package com.mini_games.puzzle;

public enum PuzzleScale {
    
    THREE_TO_THREE(3),
    FOUR_TO_FOUR(4),
    FIVE_TO_FIVE(5),
    SIX_TO_SIX(6);
    
    private PuzzleScale(int ratio) {
        this.ratio = ratio;
    }
    
    private int ratio;
    
    public int getRatio() {
        return ratio;
    }
    
}
