package com.mini_games.puzzle;

public interface Sliding {
    
    boolean moveUp(boolean withAnimation);
    
    boolean moveDown(boolean withAnimation);
    
    boolean moveLeft(boolean withAnimation);
    
    boolean moveRight(boolean withAnimation);
    
    boolean undo(boolean withAnimation);
    
    void solve(boolean withAnimation);
    
    void shuffle();
    
}
