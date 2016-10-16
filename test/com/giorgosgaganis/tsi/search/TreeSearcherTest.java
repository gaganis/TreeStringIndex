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
package com.giorgosgaganis.tsi.search;

import com.giorgosgaganis.tsi.nodes.Node;
import com.giorgosgaganis.tsi.nodes.NodeFactory;
import com.giorgosgaganis.tsi.populator.TreeNodePopulator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TreeSearcherTest {

    private TreeSearcher treeSearcher;

    @Before
    public void setUp() {
        NodeFactory nodeFactory = new NodeFactory("hash");
        Node root = nodeFactory.createNode();
        TreeNodePopulator populator = new TreeNodePopulator(root, nodeFactory);

        populator.addWord("giannis");
        populator.addWord("giorgos");
        populator.addWord("mitsos");
        populator.addWord("aris");
        populator.addWord("arapikos");

        treeSearcher = new TreeSearcher(root);

    }

    @Test
    public void search_long_valid_nonshuffled() throws Exception {
        List<String> searchResult = treeSearcher.searchPermutatedWord("arapikos");

        assertThat(searchResult).containsExactly("arapikos");
    }

    @Test
    public void search_long_valid_shuffled() throws Exception {
        List<String> searchResult = treeSearcher.searchPermutatedWord("riaksapo");

        assertThat(searchResult).containsExactly("arapikos");
    }

    @Test
    public void search_medium_valid_nonshuffled() throws Exception {
        List<String> searchResult = treeSearcher.searchPermutatedWord("ioggosr");

        assertThat(searchResult).containsExactly("giorgos");
    }

    @Test
    public void search_medium_valid_shuffled() throws Exception {
        List<String> searchResult = treeSearcher.searchPermutatedWord("giorgos");

        assertThat(searchResult).containsExactly("giorgos");
    }

    @Test
    public void search_short_valid_nonshuffled() throws Exception {
        List<String> searchResult = treeSearcher.searchPermutatedWord("aris");

        assertThat(searchResult).containsExactly("aris");
    }

    @Test
    public void search_short_valid_shuffled() throws Exception {
        List<String> searchResult = treeSearcher.searchPermutatedWord("irsa");

        assertThat(searchResult).containsExactly("aris");
    }

    @Test
    public void search_invalid() throws Exception {
        List<String> searchResult = treeSearcher.searchPermutatedWord("dfhhk");

        assertThat(searchResult).isEmpty();
    }
}