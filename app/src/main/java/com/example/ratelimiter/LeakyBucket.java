package com.example.ratelimiter;

public class LeakyBucket {

    private long currentLevel;
    private long lastLeakTimeStamp;
    private long maxRequests;
    private long leakPerInterval;

    public LeakyBucket(long maxRequests,  long leakPerInterval){

        this.maxRequests = maxRequests;
        lastLeakTimeStamp = 0l;
        this.leakPerInterval = leakPerInterval;
    }

    public synchronized  boolean isAllowed(){

        long now = System.currentTimeMillis();

        leak(now);

        if(currentLevel < maxRequests){

            currentLevel++;
            return true;
        }

        return false;
    }

    public void leak(long now){

        long elapsed = now - lastLeakTimeStamp;

        if(elapsed > 0){

            long leak = elapsed / leakPerInterval;
            currentLevel = Math.max(0, currentLevel - leak);
            lastLeakTimeStamp += leak * leakPerInterval;
        }
    }
}
