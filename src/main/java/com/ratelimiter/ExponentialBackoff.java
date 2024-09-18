package com.ratelimiter;

import java.util.concurrent.TimeUnit;

public class ExponentialBackoff {

    // Maximum number of retries before giving up
    private final int maxRetries;

    // Base delay in milliseconds for backoff
    private final long baseDelayMs;

    // Maximum delay allowed in milliseconds
    private final long maxDelayMs;

    // Counter to keep track of the current retry attempt
    private int retryCount = 0;

    public ExponentialBackoff(int maxRetries, long baseDelayMs, long maxDelayMs) {
        this.maxRetries = maxRetries;
        this.baseDelayMs = baseDelayMs;
        this.maxDelayMs = maxDelayMs;
    }

    // Method to simulate a request and apply exponential backoff on failure
    public boolean attemptRequest() throws InterruptedException {
        boolean requestSuccess = simulateRequest();

        if (requestSuccess) {
            System.out.println("Request succeeded.");
            reset();  // Reset the retry count on success
            return true;
        } else {
            System.out.println("Request failed. Applying exponential backoff...");
            retryWithBackoff();
            return false;
        }
    }

    // Method to simulate a request, where we randomly fail 50% of the time (for demo purposes)
    private boolean simulateRequest() {
        return Math.random() > 0.5;  // 50% chance of success
    }

    // Exponential backoff mechanism
    private void retryWithBackoff() throws InterruptedException {
        if (retryCount < maxRetries) {
            // Calculate the delay time (2^retryCount * baseDelayMs), but cap it at maxDelayMs
            long delay = Math.min((1L << retryCount) * baseDelayMs, maxDelayMs);

            System.out.println("Waiting for " + delay + "ms before retrying...");
            TimeUnit.MILLISECONDS.sleep(delay);  // Wait for the calculated delay

            retryCount++;  // Increment the retry count for the next backoff
        } else {
            System.out.println("Max retries reached. Request permanently failed.");
        }
    }

    // Reset the retry counter on a successful request
    private void reset() {
        retryCount = 0;
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a rate limiter with a max of 5 retries, base delay of 100ms, and max delay of 1600ms
        ExponentialBackoff rateLimiter = new ExponentialBackoff(5, 100, 1600);

        // Simulate several request attempts
        for (int i = 0; i < 10; i++) {
            rateLimiter.attemptRequest();
            Thread.sleep(200);  // Simulate time between consecutive requests
        }
    }
}
