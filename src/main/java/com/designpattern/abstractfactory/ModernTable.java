package com.designpattern.abstractfactory;

class ModernTable implements Table {
    @Override
    public void placeOn() {
        System.out.println("Placing items on a modern table.");
    }
}

