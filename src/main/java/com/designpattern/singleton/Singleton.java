package com.designpattern.singleton;

public class Singleton {

    // Instance is created at the time of class loading
    private static final Singleton INSTANCE = new Singleton();

    // Private constructor to prevent instantiation
    private Singleton() {}

    // Public method to provide access to the instance
    public static Singleton getInstance() {
        return INSTANCE;
    }

}
