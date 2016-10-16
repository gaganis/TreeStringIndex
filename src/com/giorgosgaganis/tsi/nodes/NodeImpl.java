package com.giorgosgaganis.tsi.nodes;

class NodeImpl implements Node {

    private boolean isEnd = false;

    private CharacterToNodeMapper mapper;

    NodeImpl(CharacterToNodeMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Node getNextNode(Character nextNodeChar) {
        return mapper.getNextNode(nextNodeChar);
    }

    @Override
    public void putNextNode(Character nextNodeChar, Node refNode) {
        mapper.putNextNode(nextNodeChar, refNode);
    }

    @Override
    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    @Override
    public boolean isEnd() {
        return isEnd;
    }
}
