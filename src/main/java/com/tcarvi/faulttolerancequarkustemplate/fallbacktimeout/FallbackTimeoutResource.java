package com.tcarvi.faulttolerancequarkustemplate.fallbacktimeout;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/endpointFallbackTimeout")
public class FallbackTimeoutResource {

    @Inject
    FallbackTimeoutService fallbackTimeoutService;

    @Fallback(fallbackMethod = "callToFallbackTimeoutService")
    @Timeout(5000) // timeout is 5000ms = 5s
    @GET
    public String fallbackTimeoutResourceExecution() {
        return fallbackTimeoutService.exec();
    }

    public String callToFallbackTimeoutService() {
        return fallbackTimeoutService.fallback();
    }

}