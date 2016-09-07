/*
    Copyright (C) 2016 Giorgos Gaganis

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Website <http://giorgosgaganis.com>
    e-mail  <gaganis@yahoo.com>
 */
package com.giorgosgaganis.tsi.main;

import com.giorgosgaganis.tsi.nodes.Node;
import com.giorgosgaganis.tsi.populator.TreeNodePopulator;
import com.giorgosgaganis.tsi.populator.WordPreProcessor;
import com.giorgosgaganis.tsi.search.TreeSearcher;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    public static final int SEARCH_COUNT = 30;

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static int counter = 0;
    private static long totalTime = 0;

    public static void main(String[] args) throws IOException {

        LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.FINE);

        final String dictionaryFilePath = "el.wl.utf8";
        Node root = TreeNodePopulator.createTreeFromFilePath(dictionaryFilePath);

        TreeSearcher searcher = new TreeSearcher(root);

        Scanner s = new Scanner(new BufferedReader(new FileReader("shuffled_words")));
        while (s.hasNext() && counter <= SEARCH_COUNT) {
            WordPreProcessor preProcessor = new WordPreProcessor();

            String word = preProcessor.process(s.next());
            searchWordShuffled(searcher, word);
            counter++;
        }
        s.close();
        logger.info("counter = " + counter);
        logger.info("totalTime = " + totalTime);
        logger.info("totalTime / counter = " + totalTime / counter);
    }

    private static void searchWordShuffled(TreeSearcher searcher, String suffledWord) {

        long start = System.currentTimeMillis();
        List<String> searchResults = searcher.searchPermutatedWord(suffledWord);
        final long time = System.currentTimeMillis() - start;

//        System.out.println("searchResult.getResults() = " + searchResults);
//        System.out.println("search time = " + time);

        counter++;
        totalTime += time;
    }

}
