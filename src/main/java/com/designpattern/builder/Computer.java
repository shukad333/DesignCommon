package com.designpattern.builder;

// Product class
public class Computer {
    // Required parameters
    private String CPU;
    private String RAM;

    // Optional parameters
    private String GPU;
    private String storage;
    private boolean hasBluetooth;
    private boolean hasWiFi;

    // Private constructor accessible only through the builder
    private Computer(ComputerBuilder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.GPU = builder.GPU;
        this.storage = builder.storage;
        this.hasBluetooth = builder.hasBluetooth;
        this.hasWiFi = builder.hasWiFi;
    }

    // Getters for the fields (if needed)
    public String getCPU() {
        return CPU;
    }

    public String getRAM() {
        return RAM;
    }

    public String getGPU() {
        return GPU;
    }

    public String getStorage() {
        return storage;
    }

    public boolean hasBluetooth() {
        return hasBluetooth;
    }

    public boolean hasWiFi() {
        return hasWiFi;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", GPU=" + GPU +
                ", Storage=" + storage + ", Bluetooth=" + hasBluetooth +
                ", WiFi=" + hasWiFi + "]";
    }

    // Builder Class
    public static class ComputerBuilder {
        // Required parameters
        private String CPU;
        private String RAM;

        // Optional parameters
        private String GPU;
        private String storage;
        private boolean hasBluetooth;
        private boolean hasWiFi;

        // Constructor for required fields
        public ComputerBuilder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }

        // Methods for setting optional fields
        public ComputerBuilder setGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }

        public ComputerBuilder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public ComputerBuilder setBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }

        public ComputerBuilder setWiFi(boolean hasWiFi) {
            this.hasWiFi = hasWiFi;
            return this;
        }

        // Build method to create the final product
        public Computer build() {
            return new Computer(this);
        }
    }
}
