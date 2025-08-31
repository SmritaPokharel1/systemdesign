package com.example.ratelimiter;

public class TokenBucketII {

   private long refillInterval; //every 1s bucket gets refilled with 2 tokens
   private long refillTokens;
   private long remainingTokens;
   private long maxTokens;
   private long lastFilledTimestamp;

   public TokenBucketII(long refillInterval, long refillTokens, long maxTokens){

       this.refillInterval = refillInterval;
       this.refillTokens = refillTokens;
       this.maxTokens = maxTokens;

   }

   private synchronized boolean isAllowed(){

       long now = System.currentTimeMillis();

       refill(now);

       if(remainingTokens > 0){

           remainingTokens -= 1;
           return true;
       }

       return false;
   }

   private void refill(long now){

       long elapsed = now - lastFilledTimestamp;

       if(elapsed > 1){

           long tokensToAdd = elapsed/refillTokens * refillTokens;

           remainingTokens = Math.max(maxTokens, remainingTokens + tokensToAdd);

           lastFilledTimestamp = now;
       }
   }
}
