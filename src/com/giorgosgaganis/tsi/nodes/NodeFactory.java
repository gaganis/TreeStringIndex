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

import java.util.logging.Logger;

public class NodeFactory {
    private static final Logger logger = Logger.getLogger(NodeFactory.class.getName());
    private static long nodeCount = 0;
    private final String nodeType;

    public NodeFactory(String nodeType) {
        logger.fine("Configuring NodeFactory to nodeType = " + nodeType);
        this.nodeType = nodeType;
    }

    public static long getNodeCount() {
        return nodeCount;
    }

    public Node createNode() {
        nodeCount++;
        if ("hash".equals(nodeType)) {
            return new HashMapNode();
        } else if ("bighash".equals(nodeType)) {
            return new BigHashMapNode();
        } else {
            return new GreekUppercaseSparseNode();
        }
    }
}
