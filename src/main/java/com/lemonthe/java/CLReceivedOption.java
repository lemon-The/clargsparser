package com.lemonthe.java;

import java.util.*;

public class CLReceivedOption {
    private CLOption option;
    private String argument;
    
    public CLReceivedOption(CLOption option, String argument) {
        this.option = option;
        this.argument = argument;
    }
    public CLReceivedOption(CLOption option) {
        this.option = option;
        this.argument = null;
    }

    public CLOption getOption() {
        return this.option;
    }
    public String getArgument() {
        return this.argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null)
            return false;
        if (getClass() != otherObject.getClass())
            return false;
        CLReceivedOption other = (CLReceivedOption)otherObject;
        return this.option.equals(other.option)
                && this.argument.equals(other.argument);
    }
    @Override
    public int hashCode() {
        return Objects.hash(option, argument);
    }
    @Override
    public String toString() {
        return getClass().getName()
                + "[option=" + this.option
                + ", argument=" + this.argument
                + "]";
    }
}
