package com.tcarvi.faulttolerancequarkustemplate.controller.fallbacktimeout;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/fallbackTimeoutCtrl")
public class FallbackTimeoutResource {

    @Inject
    FallbackTimeoutService fallbackTimeoutService;

    @Fallback(fallbackMethod = "fallbackTimeoutService.fallbackhandler")
    @Timeout(50) // timeout is 5000ms = 5s
    @GET
    public String FallbackTimeoutResourceExecution() throws InterruptedException {
        return fallbackTimeoutService.checkTimeout();
    }

}
