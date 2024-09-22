package com.designpattern.abstractfactory;

public class VictorianFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new VIctorianChair();
    }

    @Override
    public Table createTable() {
        return new VictorianTable();
    }
}
