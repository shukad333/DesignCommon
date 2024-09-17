package com.ratelimiter;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LeakyBucketRateLimiter {

    // Capacity of the bucket (max number of requests allowed in the bucket)
    private final int capacity;

    // Leak rate - the rate at which requests are processed (in milliseconds)
    private final int leakRateMs;

    // Current count of requests in the bucket
    private final AtomicInteger currentRequestCount = new AtomicInteger(0);

    // Scheduled executor to process the requests at a constant rate
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public LeakyBucketRateLimiter(int capacity, int leakRateMs) {
        this.capacity = capacity;
        this.leakRateMs = leakRateMs;

        // Start leaking (processing) requests at a constant rate
        scheduler.scheduleAtFixedRate(this::leakRequest, 0, leakRateMs, TimeUnit.MILLISECONDS);
    }

    // Method to add request
    public boolean allowRequest() {
        int currentCount = currentRequestCount.get();

        if (currentCount < capacity) {
            // Add the request if the bucket is not full
            currentRequestCount.incrementAndGet();
            System.out.println("Request allowed. Current count: " + currentRequestCount.get());
            return true;
        } else {
            // Reject the request if the bucket is full
            System.out.println("Request rejected. Bucket full.");
            return false;
        }
    }

    // Leak (process) one request from the bucket
    private void leakRequest() {
        if (currentRequestCount.get() > 0) {
            currentRequestCount.decrementAndGet();
            System.out.println("Leaked 1 request. Current count: " + currentRequestCount.get());
        }
    }

    // Shutdown the rate limiter
    public void shutdown() {
        scheduler.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a rate limit
        LeakyBucketRateLimiter rateLimiter = new LeakyBucketRateLimiter(5, 1000);

        // Simulating incoming requests
        for (int i = 0; i < 10; i++) {
            rateLimiter.allowRequest();
            Thread.sleep(200); // Time between incoming requests
        }

        // Wait a bit and shutdown the rate limiter
        Thread.sleep(10000);
        rateLimiter.shutdown();
    }
}
