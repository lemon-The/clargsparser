package com.lemonthe.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CLArgsParserTest {
    CLArgsParser parser;

    public CLArgsParserTest() {
        parser = new CLArgsParser();
        parser.addPossibleOption(new CLOptionBuilder().name("n").description("descr").buildCLOption());
        parser.addPossibleOption(new CLOptionBuilder().name("help").description("descr").buildCLOption());
        parser.addPossibleOption(new CLOptionBuilder().name("l").description("descr").buildCLOption());
        parser.addPossibleOption(new CLOptionBuilder().name("new").hasArg().description("descr").buildCLOption());
        parser.addPossibleOption(new CLOptionBuilder().name("g").hasArg().description("descr").buildCLOption());
    } 

    @Test
    public void shouldHaveTheRightNumberOfArgsWhenPassString() {
        String[] args = {"--new", "Object", "justArg", "-n"};

        try {
            parser.processInput(args);
        } catch (InvalidOptionException e) {
            e.printStackTrace();
        }
        int argsCount = (int)parser.getArguments().stream().count();

        Assertions.assertEquals(1, argsCount);
    }
    @Test
    public void shouldHaveTheRightNumberOfOptionsWhenPassString() {
        String[] args = {"--new", "Object", "justArg", "-n"};

        try {
            parser.processInput(args);
        } catch (InvalidOptionException e) {
            e.printStackTrace();
        }
        int optsCount = (int)parser.getReceived().stream().count();

        Assertions.assertEquals(2, optsCount);
    }

}
