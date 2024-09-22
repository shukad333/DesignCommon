package com.designpattern.abstractfactory;

class VictorianTable implements Table {
    @Override
    public void placeOn() {
        System.out.println("Placing items on a Victorian table.");
    }
}