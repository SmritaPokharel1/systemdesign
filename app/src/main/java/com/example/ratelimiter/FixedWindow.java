package com.example.ratelimiter;

public class FixedWindow {

    private long maxRequests;
    private long windowStart;
    private int count;
    private int windowSize;

    public FixedWindow(long maxRequests, long windowStart, int count, int windowSize){

        this.maxRequests = maxRequests;
        this.windowStart = windowStart;
        this.count = count;
        this.windowSize = windowSize;
    }

    public synchronized boolean isAllowed(){

        long now = System.currentTimeMillis();

        if(now - windowStart > windowSize){

            windowStart = now;
            count = 0;
        }

        if(count > maxRequests) return false;

        count++;

        return true;
    }

}
