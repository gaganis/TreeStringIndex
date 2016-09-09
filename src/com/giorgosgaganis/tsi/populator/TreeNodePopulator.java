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
package com.giorgosgaganis.tsi.populator;

import com.giorgosgaganis.tsi.nodes.Node;
import com.giorgosgaganis.tsi.nodes.NodeFactory;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TreeNodePopulator {
    private static final Logger logger = Logger.getLogger(TreeNodePopulator.class.getName());

    private final Node root;
    private final NodeFactory nodeFactory;

    public TreeNodePopulator(Node root, NodeFactory nodeFactory) {
        this.root = root;
        this.nodeFactory = nodeFactory;
    }

    public void addWord(String word) {
        if (word.isEmpty()) {
            return;
        }

        LinkedList<Character> wordList = new LinkedList<>();

        char[] wordChars = word.toCharArray();

        for (char c : wordChars) {
            wordList.add(c);
        }


        processCharacters(wordList, root);
    }

    private void processCharacters(LinkedList<Character> characters, Node node) {
        if (characters.isEmpty()) {
            return;
        }

        processCharacter(characters, node, characters.removeFirst());
    }

    private void processCharacter(LinkedList<Character> characters, Node node, Character c) {
        Node refNode = node.getNextNode(c);
        if (refNode == null) {
            refNode = nodeFactory.createNode();
            node.putNextNode(c, refNode);
        }
        if (characters.size() == 0) {
            refNode.setIsEnd(true);
        } else {
            processCharacters(characters, refNode);
        }
    }

    public static Node createTreeFromFilePath(String dictionaryFilePath, String nodeType) throws IOException {
        NodeFactory nodeFactory = new NodeFactory(nodeType);
        Node root = nodeFactory.createNode();
        TreeNodePopulator populator = new TreeNodePopulator(root, nodeFactory);

        long start = System.currentTimeMillis();

        logger.setLevel(Level.FINE);
        Scanner s = new Scanner(
                new InputStreamReader(
                        new BufferedInputStream(
                                new FileInputStream(dictionaryFilePath)),
                        "utf-8"));
        while (s.hasNext()) {


            String word = s.next();
//            System.out.println("word = " + word);

            WordPreProcessor preProcessor = new WordPreProcessor();
            populator.addWord(preProcessor.process(word));
        }
        s.close();
        logger.info("nodeFactory.getNodeCount() = " + NodeFactory.getNodeCount());
        logger.info("population time = " + (System.currentTimeMillis() - start));
        return root;
    }
}
