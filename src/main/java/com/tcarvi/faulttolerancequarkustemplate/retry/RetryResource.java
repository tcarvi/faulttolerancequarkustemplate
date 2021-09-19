package com.tcarvi.faulttolerancequarkustemplate.retry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/retryCtrl")
@Singleton
public class RetryController {

    @Inject private RetryInjection RetryInjection;

    @GET
    public void RetryControllerWithInjectionUse() {
        // If retryController.doWork() fails, try again until 2 times.
        String theResult = RetryInjection.doWork();
        System.out.println(theResult);
    }
}