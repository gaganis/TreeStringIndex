package com.giorgosgaganis.tsi.main;

import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

class CommandLineParameters {
    private String nodeType = "";
    private int numberOfSearches = 0;

    public int getNumberOfSearches() {
        return numberOfSearches;
    }

    public void setNumberOfSearches(int numberOfSearches) {
        this.numberOfSearches = numberOfSearches;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    static CommandLineParameters getCommandLineParameters(String[] args) {
        CommandLineParameters clp = new CommandLineParameters();

        OptionParser parser = new OptionParser();
        OptionSpec<Integer> countSpec =
                parser.accepts( "numberOfSearches" ).withRequiredArg().ofType( Integer.class );
        OptionSpec<String> nodeFactorySpec =
                parser.accepts("nodeType").withRequiredArg().ofType(String.class);

        OptionSet options = parser.parse( args );
        Integer countParameter = options.valueOf(countSpec);
        if(countParameter != null) {
            clp.setNumberOfSearches(countParameter);
        }
        String nodeTypeParameter = options.valueOf(nodeFactorySpec);
        if(nodeTypeParameter != null) {
            clp.setNodeType(nodeTypeParameter);
        }
        return clp;
    }
}
