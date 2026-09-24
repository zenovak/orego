package edu.lclark.orego.patterns;

import edu.lclark.orego.core.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

import static edu.lclark.orego.patterns.PatternFinder.generatePatternMap;

public class PatternFinderTest {

    @BeforeEach
    public void setup() {

    }

    // resource don't exist. Ignore for now
    public void testPatternPrint() {
        HashMap<String, Float> map = new HashMap<>();
        HashMap<String, Long> hashMap = new HashMap<>();
        Board board = new Board(19);
        ShapeTable table = new ShapeTable("patterns" + File.separator
                + "patterns9x9-SHAPE-sf99.data", 0.99f);
        int centerColumn = 11;
        int centerRow = 16;
        int patternRadius = 4;
        int minStoneCount = 4;
        int maxStoneCount = 4;
        ArrayList<Short> stones = new ArrayList<>();
        generatePatternMap(board, map, hashMap, table, stones, minStoneCount,
                maxStoneCount, centerRow, centerColumn, patternRadius);
        System.out.println(map.size());
        ArrayList<Map.Entry<String, Float>> entries = new ArrayList<>(
                map.entrySet());
        Collections.sort(entries, new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Float> entry1,
                               Map.Entry<String, Float> entry2) {
                if (entry1.getValue() > entry2.getValue()) {
                    return 1;
                } else if (entry1.getValue() < entry2.getValue()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        System.out.println("Bottom Twenty\n");
        for (int i = 0; i < 20; i++) {
            System.out.println(entries.get(i).getValue());
            System.out.println(hashMap.get(entries.get(i).getKey()));
            table.printIndividualWinRates(hashMap.get(entries.get(i).getKey()));
            System.out.println(entries.get(i).getKey());
        }
        System.out.println("Top Twenty\n");
        for (int i = entries.size() - 20; i < entries.size(); i++) {
            System.out.println(entries.get(i).getValue());
            System.out.println(hashMap.get(entries.get(i).getKey()));
            table.printIndividualWinRates(hashMap.get(entries.get(i).getKey()));
            System.out.println(entries.get(i).getKey());
        }
    }
}
