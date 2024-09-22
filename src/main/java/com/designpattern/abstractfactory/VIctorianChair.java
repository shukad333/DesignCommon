package com.designpattern.abstractfactory;

public class VIctorianChair implements Chair{

    @Override
    public void sitOn() {
        System.out.println("Sitting on a Victorian chair.");
    }
}
