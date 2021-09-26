package com.tcarvi.faulttolerancequarkustemplate.timeout;

import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/endpointTimeout")
public class TimeoutResource {

    @Inject
    TimeoutService timeoutService;

    @Timeout(5000) // timeout is 5000ms = 5s
    @GET
    public String timeoutResourceExecution() {
        return timeoutService.exec();
    }

}

