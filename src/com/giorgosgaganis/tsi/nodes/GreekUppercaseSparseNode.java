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
package com.giorgosgaganis.tsi.nodes;

import java.util.HashMap;
import java.util.Map;

class GreekUppercaseSparseNode extends AbstractNode {
    private static final int ARRAY_SIZE = 24;

    private final Node[] sparseArray = new Node[ARRAY_SIZE];

    private Map<Character, Node> overflowMap = null;

    @Override
    public Node getNextNode(Character nextNodeChar) {
        int position = nextNodeChar - 0x0391;
        if(position >= 0 && position < ARRAY_SIZE) {
            return  sparseArray[position];
        } else {
            if(overflowMap == null) {
                return null;
            }
            return overflowMap.get(nextNodeChar);
        }
    }

    @Override
    public void putNextNode(Character nextNodeChar, Node refNode) {
        int position = nextNodeChar - 0x0391;
        if(position >= 0 && position < ARRAY_SIZE) {
            sparseArray[position] = refNode;
        } else {
            if(overflowMap == null) {
                overflowMap = new HashMap<>();
            }
            overflowMap.put(nextNodeChar, refNode);
        }
    }
}
