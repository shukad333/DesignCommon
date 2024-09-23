package com.designpattern.builder;

public class Main {
    public static void main(String[] args) {
        // Building a computer with required and some optional attributes
        Computer myComputer = new Computer.ComputerBuilder("Intel i7", "16GB")
                .setGPU("NVIDIA GTX 3080")
                .setStorage("1TB SSD")
                .setBluetooth(true)
                .setWiFi(true)
                .build();

        System.out.println(myComputer);
    }
}
