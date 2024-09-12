package com.ratelimiter;

import java.util.concurrent.TimeUnit;

public class TokenBucketRateLimiter {

    private final long maxTokens;    // Maximum number of tokens the bucket can hold
    private final long refillTokens; // Tokens added per refill interval
    private final long refillIntervalInMillis; // Time in milliseconds for each refill

    private long availableTokens;    // Current number of available tokens
    private long lastRefillTimestamp; // Timestamp of the last refill


    public TokenBucketRateLimiter(long maxTokens, long refillTokens, long refillInterval, TimeUnit unit) {
        this.maxTokens = maxTokens;
        this.refillTokens = refillTokens;
        this.refillIntervalInMillis = unit.toMillis(refillInterval);
        this.availableTokens = maxTokens; // Initialize with full bucket
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    // Method to try and consume a token
    public synchronized boolean tryConsume() {
        refill(); // Refill the bucket before consuming

        if (availableTokens > 0) {
            availableTokens--;
            return true;  // Token consumed
        } else {
            return false; // No tokens available
        }
    }

    // Method to refill the bucket based on elapsed time
    private void refill() {
        long now = System.currentTimeMillis();
        long elapsedTime = now - lastRefillTimestamp;

        if (elapsedTime > refillIntervalInMillis) {
            long tokensToAdd = (elapsedTime / refillIntervalInMillis) * refillTokens;
            availableTokens = Math.min(maxTokens, availableTokens + tokensToAdd); // Cap tokens at maxTokens
            lastRefillTimestamp = now;
        }
    }

    // Get current available tokens (for monitoring purposes)
    public long getAvailableTokens() {
        refill();
        return availableTokens;
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a Token Bucket Rate Limiter with 10 max tokens, refilling 1 token every second
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(10, 1, 100, TimeUnit.SECONDS);

        // Simulate requests
        for (int i = 0; i < 15; i++) {
            if (rateLimiter.tryConsume()) {
                System.out.println("Request " + i + " allowed.");
            } else {
                System.out.println("Request " + i + " denied (rate limit exceeded).");
            }
            Thread.sleep(500); // Wait 500ms between requests
        }
    }
}