package com.tcarvi.faulttolerancequarkustemplate.timeout;

import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/endpointTimeout")
@ApplicationScoped
public class TimeoutResource {

    @Inject
    TimeoutService timeoutService;

    @Timeout(5000) // timeout is 5000ms = 5s
    @GET
    public String checkTimeout() {
        try {
            Thread.sleep(700L); // Suposição do tempo do processamento.
        } catch (InterruptedException e) {
            // Tratamento do problema de atraso no processamento.
        }
        return timeoutService.getText();
    }
}

