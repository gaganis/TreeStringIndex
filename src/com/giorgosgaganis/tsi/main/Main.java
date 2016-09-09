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
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import sun.rmi.runtime.Log;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.logging.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main {

    private static final int SEARCH_COUNT = 30;

    private static Logger logger = Logger.getLogger(Main.class.getName());

    private static int counter = 0;
    private static long totalTime = 0;

    public static void main(String[] args) throws IOException {

        configureLogging();

        logger.info("Starting");

        int totalCount = SEARCH_COUNT;
        String nodeType = "";
        OptionParser parser = new OptionParser();
        OptionSpec<Integer> countSpec =
                parser.accepts( "searchCount" ).withRequiredArg().ofType( Integer.class );
        ArgumentAcceptingOptionSpec<String> nodeFactorySpec = parser.accepts("nodeFactory").withRequiredArg().ofType(String.class);

        parser.accepts( "searchCount" ).withRequiredArg();

        OptionSet options = parser.parse( args );
        Integer countParameter = options.valueOf(countSpec);
        if(countParameter != null) {
            totalCount = countParameter;
        }
        String nodeTypeParameter = options.valueOf(nodeFactorySpec);
        if(nodeTypeParameter != null) {
            nodeType = nodeTypeParameter;
        }

        final String dictionaryFilePath = "el.wl.utf8";
        logger.info("Starting tree population");
        Node root = TreeNodePopulator.createTreeFromFilePath(dictionaryFilePath, nodeType);
        logger.info("Finished tree population");

        TreeSearcher searcher = new TreeSearcher(root);

        Scanner s = new Scanner(
                new InputStreamReader(
                        new BufferedInputStream(
                                new FileInputStream("shuffled_words")),
                        "utf-8"));
        while (s.hasNext() && counter < totalCount) {
            WordPreProcessor preProcessor = new WordPreProcessor();

            String word = preProcessor.process(s.next());
            searchWordShuffled(searcher, word);
        }
        s.close();
        Main.logger.info("counter = " + counter);
        Main.logger.info("totalTime = " + totalTime);
        Main.logger.info("totalTime / counter = " + totalTime / counter);
    }

    private static void configureLogging() throws IOException {
        Path logConfig = Paths.get("logging.properties");
        if(Files.exists(logConfig)) {
            LogManager.getLogManager().readConfiguration(Files.newInputStream(logConfig));
        }
    }

    private static void searchWordShuffled(TreeSearcher searcher, String suffledWord) {

        logger.fine("Searching suffledWord = " + suffledWord);
        long start = System.currentTimeMillis();
        List<String> searchResults = searcher.searchPermutatedWord(suffledWord);
        final long time = System.currentTimeMillis() - start;

        System.out.println("searchResult.getResults() = " + searchResults);
        System.out.println("search time = " + time);

        counter++;
        totalTime += time;
    }

}
