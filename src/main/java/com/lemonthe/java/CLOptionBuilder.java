package com.lemonthe.java;

public class CLOptionBuilder {
    private String optionName;
    private boolean hasOptionArg = false;
    private String optionDescription = null;

    public CLOptionBuilder name(String name) {
        this.optionName = name;
        return this;
    }
    public CLOptionBuilder hasArg() {
        this.hasOptionArg = true;
        return this;
    }
    public CLOptionBuilder description(String description) {
        this. optionDescription = description;
        return this;
    }
    
    public CLOption buildCLOption() {
        return new CLOption(optionName, hasOptionArg, optionDescription);
    }
}
