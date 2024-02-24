
package com.mini_games.interfaces;

import javafx.scene.layout.Pane;

public interface SubController extends Unfoldable {
    
    void restore();
    
    void startGame(Pane mainPane);
    
}
