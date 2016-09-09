package com.giorgosgaganis.tsi.main;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandLineParametersTest {
    @Test
    public void empty_parameters_results_empty_nodetype() throws Exception {
        CommandLineParameters clp = CommandLineParameters.getCommandLineParameters(new String[]{});

        assertThat(clp.getNumberOfSearches()).isZero();
    }

    @Test
    public void empty_parameters_results_zero_numberOfSearches() throws Exception {
        CommandLineParameters clp = CommandLineParameters.getCommandLineParameters(new String[]{});

        assertThat(clp.getNumberOfSearches()).isZero();
    }

    @Test
    public void test_numberOfSearches_value() throws Exception {
        CommandLineParameters clp = CommandLineParameters.getCommandLineParameters(new String[]{"--numberOfSearches=8"});

        assertThat(clp.getNumberOfSearches()).isEqualTo(8);
    }

    @Test
    public void test_numberOfSearches_2param_value() throws Exception {
        CommandLineParameters clp = CommandLineParameters.getCommandLineParameters(new String[]{"--numberOfSearches", "8"});

        assertThat(clp.getNumberOfSearches()).isEqualTo(8);
    }

    @Test
    public void test_nodeType_value() throws Exception {
        CommandLineParameters clp = CommandLineParameters.getCommandLineParameters(new String[]{"--nodeType=hash"});

        assertThat(clp.getNodeType()).isEqualTo("hash");
    }

    @Test
    public void test_nodeType_2param_value() throws Exception {
        CommandLineParameters clp = CommandLineParameters.getCommandLineParameters(new String[]{"--nodeType", "hash"});

        assertThat(clp.getNodeType()).isEqualTo("hash");
    }
}