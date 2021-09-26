package com.tcarvi.faulttolerancequarkustemplate.retry;

import org.eclipse.microprofile.faulttolerance.Retry;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/endpointRetry")
public class RetryResource {

    @Inject
    RetryService retryService;

    @Retry(
        maxRetries = 5,
        maxDuration = 2000
    )
    @GET
    public String retryResourceExecution() {
        return retryService.exec();
    }

}