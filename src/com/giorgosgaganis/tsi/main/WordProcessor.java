package com.giorgosgaganis.tsi.main;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class WordProcessor {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        Scanner s = new Scanner(
                new InputStreamReader(
                        new BufferedInputStream(
                                new FileInputStream("capitalized_words")),
                        "utf-8"));


        Map<Character, Long> characterCountMap = new HashMap<>();
        while (s.hasNext()) {

            String word = s.next();
            char[] chars = word.toCharArray();
            for (char aChar : chars) {
                long count = 0;

                if (characterCountMap.get(aChar) != null) {
                    count = characterCountMap.get(aChar);
                }
                count++;
                characterCountMap.put(aChar, count);
            }

        }
        s.close();
        Map<Long, Character> countToCharMap = new TreeMap<>();
        System.out.println("time = " + (System.currentTimeMillis() - start));
        for (Character c : characterCountMap.keySet()) {
            countToCharMap.put(characterCountMap.get(c), c);
        }

        LinkedList<Long> list = new LinkedList<>(countToCharMap.keySet());
        Collections.reverse(list);
        for (Long l : list) {
//            System.out.println("l = " + l);
            System.out.println("countToCharMap.get(l) = " + countToCharMap.get(l));
        }
    }
}
