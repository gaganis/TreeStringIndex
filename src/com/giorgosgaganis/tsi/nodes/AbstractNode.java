package com.giorgosgaganis.tsi.nodes;

/**
 * Created by gaganis on 7/9/2016.
 */
public abstract class AbstractNode implements Node {
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
