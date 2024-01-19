
package com.mini_games.puzzle;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Puzzle {
    
    public static void shuffle(List<PuzzlePart> partList) {
        Random random = new Random();
        for (int i = 0; i < partList.size() * 2; i++) {
            int first = random.nextInt(partList.size());
            int second = random.nextInt(partList.size());
            //partList.get(first).changePosition(partList.get(second));
            swap(partList, first, second);
        }
    }
    
    public static void solve(List<PuzzlePart> partList) {
        partList.stream().forEach(PuzzlePart::restorePosition);
    }
    
    public static boolean isReady(List<PuzzlePart> partList) {
        return partList.stream().allMatch(PuzzlePart::isOnPlace);
    }
    
    public static void swap(List<PuzzlePart> partList, int first, int second) {
        Collections.swap(partList, first, second);
        partList.get(first).changePosition(partList.get(second));      
    }
    
    public static int getBlankElement(List<PuzzlePart> partList) {
        for (int i = 0; i < partList.size(); i++) {
            if (partList.get(i).isEmpty()) return i;
        }
        return -1;
    }

    
}
