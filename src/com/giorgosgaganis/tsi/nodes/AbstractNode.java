package com.giorgosgaganis.tsi.nodes;

abstract class AbstractNode implements Node {
    private boolean isEnd = false;

    @Override
    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    @Override
    public boolean isEnd() {
        return isEnd;
    }
}
