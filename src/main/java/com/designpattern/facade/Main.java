package com.designpattern.facade;

public class Main {
    public static void main(String[] args) {
        HomeAutomationFacade facade = new HomeAutomationFacade();

        // Start Movie Night
        facade.startMovieNight();

        // End Movie Night
        facade.endMovieNight();
    }
}
