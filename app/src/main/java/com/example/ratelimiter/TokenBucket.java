package com.example.ratelimiter;


/**
 */
public class TokenBucket {

    private long maxTokens;
    private long availableTokens;
    private long refillInterval;
    private long tokensPerInterval;
    private long lastRefillTimestamp;

    public TokenBucket(long maxTokens, long availableTokens, long refillInterval, long tokensPerInterval, long lastRefillTimestamp){

        this.maxTokens = maxTokens;
        this.availableTokens = availableTokens;
        this.refillInterval = refillInterval;
        this.tokensPerInterval = tokensPerInterval;
        this.lastRefillTimestamp = lastRefillTimestamp;
    }

    private synchronized  boolean isAllowed(){

        long now = System.currentTimeMillis();

        refill(now);

        if(availableTokens >=1){

            availableTokens -= 1;
            return true;
        }
        return false;
    }

    private void refill(long now){

        long elapsed = (now - lastRefillTimestamp);

        if(elapsed > 1){

            long tokensToAdd = elapsed / refillInterval * tokensPerInterval;
            availableTokens = Math.max(maxTokens, availableTokens + tokensToAdd);
            lastRefillTimestamp = now;
        }
    }
}
