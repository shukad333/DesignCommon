package com.ratelimiter;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiter {

    // Maximum number of requests allowed in a window
    private final int maxRequests;

    // Duration of the window in milliseconds
    private final long windowDurationMs;

    // Count of the requests in the current window
    private final AtomicInteger requestCount = new AtomicInteger(0);

    // Start time of the current window
    private long windowStartTime;

    public FixedWindowRateLimiter(int maxRequests, long windowDurationMs) {
        this.maxRequests = maxRequests;
        this.windowDurationMs = windowDurationMs;
        this.windowStartTime = System.currentTimeMillis();
    }

    // Method to check if a request is allowed
    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();

        // Check if the current window has expired
        if (currentTime - windowStartTime >= windowDurationMs) {
            // Reset for the new window
            windowStartTime = currentTime;
            requestCount.set(0);
            System.out.println("New window started. Request count reset.");
        }

        // Check if the request can be allowed in the current window
        if (requestCount.get() < maxRequests) {
            requestCount.incrementAndGet();
            System.out.println("Request allowed. Current count: " + requestCount.get());
            return true;
        } else {
            // Reject the request if the limit has been reached
            System.out.println("Request rejected. Limit reached for the current window.");
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a rate limiter with 5 requests per 1000ms (1 second)
        FixedWindowRateLimiter rateLimiter = new FixedWindowRateLimiter(5, 1000);

        // Simulate incoming requests
        for (int i = 0; i < 10; i++) {
            rateLimiter.allowRequest();
            Thread.sleep(200); // Time between incoming requests
        }
    }
}
