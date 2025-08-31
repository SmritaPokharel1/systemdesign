package com.example.ratelimiter;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowLog {

    private long windowSize;
    private Deque<Long> timestamps;
    private long maxRequests;

    public SlidingWindowLog(long windowSize, long maxRequests){

        this.windowSize = windowSize;
        this.maxRequests = maxRequests;
        this.timestamps = new LinkedList<>();
    }

    public synchronized boolean isAllowed(){

        long now = System.currentTimeMillis();

        while(!timestamps.isEmpty() && (now - timestamps.peekFirst()) >= windowSize){
            timestamps.pollFirst();
        }

        if(timestamps.size() < maxRequests){

            timestamps.addLast(now);
            return true;
        }

        return false;
    }
}
