package com.lemonthe.java;

import java.io.*;

public class InvalidOptionException extends IOException{
    InvalidOptionException() {}
    InvalidOptionException(String gripe) {
        super(gripe);
    }
}
