package com.designpattern.abstractfactory;

public class Main {

    public static void main(String[] args) {
        FurnitureFactory modernFactory = new ModernFurnitureFactory();
        Chair modernChair = modernFactory.createChair();
        Table modernTable = modernFactory.createTable();

        modernChair.sitOn();  // Output: Sitting on a modern chair.
        modernTable.placeOn(); // Output: Placing items on a modern table.

        // Create a Victorian Furniture Factory
        FurnitureFactory victorianFactory = new VictorianFurnitureFactory();
        Chair victorianChair = victorianFactory.createChair();
        Table victorianTable = victorianFactory.createTable();

        victorianChair.sitOn();  // Output: Sitting on a Victorian chair.
        victorianTable.placeOn(); // Output: Placing items on a Victorian table.
    }

}
