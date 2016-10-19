package com.giorgosgaganis.tsi.nodes;

/**
 * Created by gaganis on 10/16/16.
 */
public interface CharacterToNodeMapper {
    Node getNextNode(Character nextNodeChar);
    void putNextNode(Character nextNodeChar, Node refNode);
}
