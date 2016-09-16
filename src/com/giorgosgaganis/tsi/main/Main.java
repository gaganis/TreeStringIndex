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
import com.giorgosgaganis.tsi.search.TreeSearcher;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static int counter = 0;
    private static long totalTime = 0;

    public static void main(String[] args) throws IOException {

        configureLogging();

        logger.info("Starting");

        CommandLineParameters clp = CommandLineParameters.getCommandLineParameters(args);


        final String dictionaryFilePath = "capitalized_words";
        logger.info("Starting tree population");
        Node root = TreeNodePopulator.createTreeFromFilePath(dictionaryFilePath, clp.getNodeType());
        logger.info("Finished tree population");

        TreeSearcher searcher = new TreeSearcher(root);

        Scanner s = new Scanner(
                new InputStreamReader(
                        new BufferedInputStream(
                                new FileInputStream("capitalized_shuffled_words")),
                        "utf-8"));
        while (s.hasNext() && counter < clp.getNumberOfSearches()) {
            searchWordShuffled(searcher, s.next());
        }
        s.close();
        Main.logger.info("counter = " + counter);
        Main.logger.info("totalTime = " + totalTime);
        Main.logger.info("totalTime / counter = " + totalTime / counter);
    }

    private static void configureLogging() throws IOException {
        Path logConfig = Paths.get("logging.properties");
        if (Files.exists(logConfig)) {
            LogManager.getLogManager().readConfiguration(Files.newInputStream(logConfig));
        }
    }

    private static void searchWordShuffled(TreeSearcher searcher, String suffledWord) {

        if (logger.isLoggable(Level.FINE)) {
            logger.fine("Searching suffledWord = " + suffledWord);
        }
        long start = System.currentTimeMillis();
        List<String> searchResults = searcher.searchPermutatedWord(suffledWord);
        final long time = System.currentTimeMillis() - start;

        if (logger.isLoggable(Level.FINE)) {
            logger.fine("searchResult.getResults() = " + searchResults);
            logger.fine("search time = " + time);
        }

        counter++;
        totalTime += time;
    }

}
