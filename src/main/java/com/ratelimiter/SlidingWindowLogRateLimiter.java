package com.ratelimiter;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowLogRateLimiter {

    private final int maxRequests;
    private final long windowDurationInMs;
    private final Deque<Long> requestTimestamps;

    public SlidingWindowLogRateLimiter(int maxRequests, int windowDurationInMs) {
        this.maxRequests = maxRequests;
        this.windowDurationInMs = windowDurationInMs;
        this.requestTimestamps = new ArrayDeque<>();
    }

    public synchronized boolean allowRequest() {

        long current = System.currentTimeMillis();
        while(!requestTimestamps.isEmpty() && current - requestTimestamps.peekFirst() >= windowDurationInMs) {
            requestTimestamps.pollFirst();
        }

        if(requestTimestamps.size() < maxRequests) {
            requestTimestamps.addLast(current);
            System.out.println("Request allowed. Current window size: " + requestTimestamps.size());
            return true;
        }
        else {
            // Reject the request if the limit is reached
            System.out.println("Request rejected. Limit reached for the current window.");
            return false;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        // Create a rate limiter with 5 requests per 1000ms (1 second window)
        SlidingWindowLogRateLimiter rateLimiter = new SlidingWindowLogRateLimiter(5, 1000);

        // Simulate incoming requests
        for (int i = 0; i < 10; i++) {
            rateLimiter.allowRequest();
            Thread.sleep(200); // Time between incoming requests
        }

        // Simulate a pause to show sliding window effect
        Thread.sleep(1200);

        for (int i = 0; i < 5; i++) {
            rateLimiter.allowRequest();
            Thread.sleep(200);
        }
    }
}
