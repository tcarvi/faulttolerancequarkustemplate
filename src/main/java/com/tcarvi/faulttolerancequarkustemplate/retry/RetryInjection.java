package com.tcarvi.faulttolerancequarkustemplate.retry;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RetryInjection {

   @Retry(maxRetries = 2)
   @Fallback(fallbackMethod = "doWorkFallbackHandler")
   public String doWork() {
      return callServiceA(); // This service usually works but sometimes
                             // throws a RuntimeException
   }

   private String doWorkFallbackHandler() {
      return "doWorkFallback() executed";
   }

   private String callServiceA() {
       return "Service A called with possible 2 retries";
   }
}