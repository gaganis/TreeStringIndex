package com.giorgosgaganis.tsi.nodes;

import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by gaganis on 7/9/2016.
 */
public abstract class AbstractNodeTest {
    @Test
    public void testNodeCheck() {

        testForCharacter('a');
    }

    @Test
    public void first_greek_lowercase() {
        testForCharacter('α');
    }

    @Test
    public void last_greek_lowercase() {
        testForCharacter('ω');
    }

    @Test
    public void first_greek_uppercase() {
        testForCharacter('Α');
    }

    @Test
    public void last_greek_uppercase() {
        testForCharacter('Ω');
    }

    protected void testForCharacter(char testedCharacter) {
        Node refNode = Mockito.mock(Node.class);

        Node node = getNode();

        node.putNextNode(testedCharacter, refNode);
        Node resultRefNode = node.getNextNode(testedCharacter);

        assertThat(resultRefNode).isSameAs(refNode);
    }

    protected abstract Node getNode();
}
