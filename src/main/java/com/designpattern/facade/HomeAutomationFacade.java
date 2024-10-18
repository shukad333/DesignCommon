package com.designpattern.facade;

class HomeAutomationFacade {
    private Light light;
    private MusicSystem musicSystem;
    private AirConditioner airConditioner;

    public HomeAutomationFacade() {
        light = new Light();
        musicSystem = new MusicSystem();
        airConditioner = new AirConditioner();
    }

    public void startMovieNight() {
        light.turnOff();
        musicSystem.playMusic();
        airConditioner.turnOn();
        System.out.println("Movie night mode is ON");
    }

    public void endMovieNight() {
        light.turnOn();
        musicSystem.stopMusic();
        airConditioner.turnOff();
        System.out.println("Movie night mode is OFF");
    }
}

class Light {
    public void turnOn() {
        System.out.println("Lights are turned on");
    }

    public void turnOff() {
        System.out.println("Lights are turned off");
    }
}

class MusicSystem {
    public void playMusic() {
        System.out.println("Music is playing");
    }

    public void stopMusic() {
        System.out.println("Music stopped");
    }
}

class AirConditioner {
    public void turnOn() {
        System.out.println("Air conditioner is turned on");
    }

    public void turnOff() {
        System.out.println("Air conditioner is turned off");
    }
}

