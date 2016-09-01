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

import java.util.LinkedList;
import java.util.List;

public class TreeSearcher {
    private final Node root;

    public TreeSearcher(Node root) {
        this.root = root;
    }

    public List<String> searchPermutatedWord(String word) {

        LinkedList<Character> remainingCharacters = new LinkedList<>();
        char[] chars = word.toCharArray();
        for (char c : chars) {
            remainingCharacters.add(c);
        }
        TreeSearch treeSearch = new TreeSearch();
        treeSearch.searchInNode(remainingCharacters, root, new LinkedList<>());

        return treeSearch.getSearchResult();
    }
}
