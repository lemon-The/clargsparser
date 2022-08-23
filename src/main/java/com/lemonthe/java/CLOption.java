package com.lemonthe.java;

import java.util.*;

public class CLOption {
    private String name;
    private String description;
    private boolean requireArg;
    //private List<String> acceptableValues;

    public CLOption(String name, boolean requireArg, String description) {
        this.name = name;
        this.description = description;
        this.requireArg = requireArg;
    }

    public String getName() {
        return this.name;
    }
    public boolean requireArg() {
        return this.requireArg;
    }
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null)
            return false;
        if (getClass() != otherObject.getClass())
            return false;
        CLOption other = (CLOption)otherObject;
        return this.name == other.name
                && this.requireArg == other.requireArg
                && this.description == other.description;
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, requireArg, description);
    }
    @Override
    public String toString() {
        return getClass().getName()
                + "[name=" + this.name
                + ", hasArg=" + this.requireArg
                + ", description=" + this.description
                + "]";
    }
}
