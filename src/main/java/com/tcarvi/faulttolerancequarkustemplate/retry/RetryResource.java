package com.tcarvi.faulttolerancequarkustemplate.retry;

import javax.inject.Inject;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/endpointRetry")
@SessionScoped
public class RetryResource {

    @Inject
    RetryService retryService;

    @GET
    public void retryResourceExecution() {
        // If retryController.doWork() fails, try again until 2 times.
        String theResult = retryService.retryServiceExecution();
        System.out.println(theResult);
    }

}