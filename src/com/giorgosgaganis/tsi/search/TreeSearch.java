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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class TreeSearch {

    private final List<String> results = new ArrayList<>();

    void searchInNode(LinkedList<Character> remainingCharacters, Node node, LinkedList<Character> foundCharacters) {

        if (remainingCharacters.isEmpty() && node.isEnd()) {
            final String found = foundCharacters.stream().map(Object::toString).collect(Collectors.joining());
            results.add(found);
        }
        searchRemainingCharacters(remainingCharacters, node, foundCharacters);
    }

    private void searchRemainingCharacters(LinkedList<Character> remainingCharacters, Node node, LinkedList<Character> foundCharacters) {
        for (Character c : remainingCharacters.stream().distinct().collect(Collectors.toList())) {
            LinkedList<Character> nextList = new LinkedList<>(remainingCharacters);
            nextList.remove(c);

            searchNextNode(foundCharacters, c, nextList, node);
        }
    }

    private void searchNextNode(LinkedList<Character> foundCharacters,
                                Character currentCharacter,
                                LinkedList<Character> remainingCharacters, Node node) {
        final Node nextNode = node.getNextNode(currentCharacter);

        if (nextNode != null) {
            LinkedList<Character> nextFoundCharacters = new LinkedList<>(foundCharacters);
            nextFoundCharacters.add(currentCharacter);
            searchInNode(remainingCharacters, nextNode, nextFoundCharacters);
        }
    }

    List<String> getSearchResult() {
        return results;
    }
}
