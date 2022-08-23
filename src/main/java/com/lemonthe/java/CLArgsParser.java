package com.lemonthe.java;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CLArgsParser {
    private List<CLOption> possibleOptions;
    private List<CLReceivedOption> receivedOptions;
    private List<String> arguments;
    private Logger logger;
    
    public CLArgsParser() {
        this.possibleOptions = new LinkedList<CLOption>();
        this.receivedOptions = new LinkedList<CLReceivedOption>();
        this.arguments = new LinkedList<String>();
        this.logger = LoggerFactory.getLogger(CLArgsParser.class);
    }

    public void processInput(String[] args)
            throws InvalidOptionException
    {
        boolean shouldBeArg = false;
        CLReceivedOption lastOption = null;
        logger.debug("All arguments: {}", Arrays.toString(args));
        for (String arg : args) {
            logger.debug("Current argument is: {}", arg);
            if (arg.isBlank()) {
                logger.debug("{} is blank", arg);
                continue;
            }
            if (isOption(arg)) {
                if (shouldBeArg == true) {
                    throw new InvalidOptionException(
                            "Option: " + arg + " requires argument");
                }
                if (arg.toCharArray()[1] != '-' && arg.length() > 2) {
                    addGroupedOptions(arg);
                    shouldBeArg = false;
                } else {
                    lastOption = addSingleOption(arg);
                    shouldBeArg = lastOption.getOption().requireArg();
                }
                logger.debug("{} added to 'receivedOptions'", arg);
            } else {
                if (shouldBeArg) {
                    lastOption.setArgument(arg);
                    shouldBeArg = false;
                    logger.debug("{} added as argument to {}", arg, lastOption);
                } else {
                    arguments.add(arg);
                    logger.debug("{} added to 'arguments'", arg);
                }
            }
        }
        if (shouldBeArg)
            throw new InvalidOptionException("Option: "
                    + args[args.length-1] 
                    + " requires argument");
    }

    private CLOption doesOptionExist(String option) 
            throws InvalidOptionException {
        for (CLOption o : possibleOptions)
            if (option.equals(o.getName()))
                return o;
        throw new InvalidOptionException("Option: " 
                + option + " does not exist");
    }

    private CLReceivedOption addSingleOption(String option)
            throws InvalidOptionException {
        String optBody;
        if (option.charAt(1) == '-')
            optBody = option.substring(2);
        else
            optBody = option.substring(1);
        CLOption tmp = doesOptionExist(optBody);
        CLReceivedOption result = new CLReceivedOption(tmp);
        receivedOptions.add(result);
        return result;
    }

    private void addGroupedOptions(String options) 
            throws InvalidOptionException {
        String optBody = options.substring(1);
        String[] opts = new String[optBody.length()];
        for (int i = 0; i < optBody.length(); i++)
            opts[i] = String.valueOf(optBody.toCharArray()[i]);
        for (String t : opts) {
            CLOption tmp = doesOptionExist(t);
            CLReceivedOption result = new CLReceivedOption(tmp);
            if (tmp.requireArg())
                throw new InvalidOptionException("Option: -" 
                        + t + " need argument");
            receivedOptions.add(result);
        }
    }

    private boolean isOption(String opt)
            throws InvalidOptionException
    {
        if (opt.isBlank() || opt.charAt(0) != '-')
            return false;
        if (opt.length() < 2)
            throw new InvalidOptionException("Empty option");
        char[] chars = opt.toCharArray();
        if (chars[1] == '-') {
            if (opt.length() < 3)
                throw new InvalidOptionException("Empty option");
            if (chars[2] == '-')
                throw new InvalidOptionException("Incorrect option syntax");
            return true;
        } else {
            if (opt.substring(1).contains("-"))
                throw new InvalidOptionException("Incorrect option syntax");
            return true;
        }
    }

    public void addPossibleOption(CLOption option) {
        if (option == null)
            return;
        possibleOptions.add(option);
    }

    public List<CLReceivedOption> getReceived() {
        return receivedOptions;
    }
    public List<CLOption> getPossible() {
        return possibleOptions;
    }
    public List<String> getArguments() {
        return arguments;
    }
}
