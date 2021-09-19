package com.tcarvi.faulttolerancequarkustemplate.fallbacktimeout;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/fallbackTimeoutCtrl")
@ApplicationScoped
public class FallbackTimeoutController {

    @Fallback(fallbackMethod = "fallbackhandler")
    @Timeout(50) // timeout is 5000ms = 5s
    @GET
    public String checkTimeout() throws InterruptedException {
        try {
            Thread.sleep(700L); // Suposição do tempo do processamento.
        }
        catch (InterruptedException ex) {
            throw new InterruptedException("TextInterruptedException");
        }
        return "Sem problema de Timeout. Processo concluiu com sucesso antes do timeout.";
    }

    public String fallbackhandler() {
        return "Processo executado por causa do problema de timeout.";
    }
}
