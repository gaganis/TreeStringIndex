package com.giorgosgaganis.tsi.nodes;

import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

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

    private void testForCharacter(char testedCharacter) {
        Node refNode = Mockito.mock(Node.class);

        CharacterToNodeMapper mapper = getMapper();

        mapper.putNextNode(testedCharacter, refNode);
        Node resultRefNode = mapper.getNextNode(testedCharacter);

        assertThat(resultRefNode).isSameAs(refNode);
    }

    protected abstract CharacterToNodeMapper getMapper();
}
