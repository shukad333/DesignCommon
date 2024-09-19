package com.clock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ConsoleClock {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {
        Runnable clockTask = () -> {
            while (true) {
                // Get current time
                LocalTime now = LocalTime.now();
                // Format the time as a string
                String formattedTime = now.format(TIME_FORMATTER);
                // Display the time in the console
                System.out.print("\r" + formattedTime);
                try {
                    // Sleep for 1 second before updating the time again
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Clock interrupted.");
                }
            }
        };

        // Start the clock in a separate thread
        Thread clockThread = new Thread(clockTask);
        clockThread.start();
    }
}
