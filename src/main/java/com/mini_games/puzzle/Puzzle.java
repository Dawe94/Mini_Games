
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
    
    public static void solve(List<PuzzlePart> partList) {
        partList.stream().forEach(PuzzlePart::restorePosition);
    }
    
    public static boolean isReady(List<PuzzlePart> partList) {
        return partList.stream().allMatch(PuzzlePart::isOnPlace);
    }

    
}
