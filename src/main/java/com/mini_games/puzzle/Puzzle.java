
package com.mini_games.puzzle;

import java.util.List;
import java.util.Random;

public class Puzzle {
    
    public static void shuffle(List<PuzzlePart> partList) {
        Random random = new Random();
        for (int i = 0; i < partList.size() * 2; i++) {
            int first = random.nextInt(partList.size());
            int second = random.nextInt(partList.size());
            partList.get(first).changePosition(partList.get(second));
        }
    }
    
    public static void unload(List<PuzzlePart> partList) {
        for (int i = 0; i < partList.size(); i++) {
            partList.get(i).restorePosition();
        }
    }

    
}
