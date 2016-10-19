package com.giorgosgaganis.tsi.nodes;


import org.junit.Test;

public class GreekUppercaseSparseMapperTest extends AbstractNodeTest {

    @Override
    protected CharacterToNodeMapper getMapper() {
        return new GreekUppercaseSparseMapper();
    }

    @Override
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testNodeCheck() {
        super.testNodeCheck();
    }

    @Override
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void first_greek_lowercase() {
        super.first_greek_lowercase();
    }

    @Override
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void last_greek_lowercase() {
        super.last_greek_lowercase();
    }
}