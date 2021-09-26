package com.tcarvi.faulttolerancequarkustemplate.fallbacktimeout;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/endpointFallbackTimeout")
@SessionScoped
public class FallbackTimeoutResource {

    @Inject
    FallbackTimeoutService fallbackTimeoutService;

    @Fallback(fallbackMethod = "callToFallbackTimeoutServiceHandler")
    @Timeout(50) // timeout is 5000ms = 5s
    @GET
    public String fallbackTimeoutResourceExecution() {
        return fallbackTimeoutService.check();
    }

    public String callToFallbackTimeoutServiceHandler() {
        return fallbackTimeoutService.fallback();
    }

}