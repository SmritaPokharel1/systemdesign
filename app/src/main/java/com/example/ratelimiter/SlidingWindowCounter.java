package com.example.ratelimiter;

public class SlidingWindowCounter {

    private long windowSize; // 60s
    private int noOfBuckets;
    private int maxRequests;
    private int start;
    private int[] buckets;
    private int bucketIndex;

    public SlidingWindowCounter(long windowSize, int noOfBuckets, int maxRequests){

        this.windowSize = windowSize;
        this.noOfBuckets = noOfBuckets;
        this.maxRequests = maxRequests;
        this.buckets = new int[noOfBuckets];
        bucketIndex = 0;
    }
}
