package com.tcarvi.faulttolerancequarkustemplate.retry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/endpointRetry")
@Singleton
public class RetryResource {

    @Inject
    RetryService retryService;

    @GET
    public void retryExecution() {
        // If retryController.doWork() fails, try again until 2 times.
        String theResult = retryService.retry();
        System.out.println(theResult);
    }
}