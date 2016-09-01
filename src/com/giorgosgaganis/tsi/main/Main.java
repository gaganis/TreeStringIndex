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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        final String dictionaryFilePath = "el.wl.utf8";
        Node root = TreeNodePopulator.createTreeFromFilePath(dictionaryFilePath);

        TreeSearcher searcher = new TreeSearcher(root);

        Scanner s = new Scanner(new File("dictionary_sorted_by_wordlength"));
        while (s.hasNext()) {
            WordPreProcessor preProcessor = new WordPreProcessor();

            String word = preProcessor.process(s.next());
            searchWordShuffled(searcher, word);
        }
        s.close();
    }

    private static void searchWordShuffled(TreeSearcher searcher, String word) {
        System.out.println("word = " + word);

        final String suffledWord = Utils.shuffle(word);
        System.out.println("suffledWord = " + suffledWord);

        long start = System.currentTimeMillis();
        List<String> searchResults = searcher.searchPermutatedWord(suffledWord);
        final long time = System.currentTimeMillis() - start;

        System.out.println("searchResult.getResults() = " + searchResults);
        System.out.println("search time = " + time);
    }

}
