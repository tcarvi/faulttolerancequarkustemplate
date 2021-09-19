package com.tcarvi.faulttolerancequarkustemplate.timeout;

import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/timeoutCtrl")
@ApplicationScoped
public class TimeoutController {
    @Timeout(5000) // timeout is 5000ms = 5s
    @GET
    public String checkTimeout() {
        try {
            Thread.sleep(70000L); // Suposição do tempo do processamento.
        } catch (InterruptedException e) {
            // Tratamento do problema de atraso no processamento.
        }
        return "Sem problema de Timeout. Processo concluiu com sucesso antes do timeout.";
    }
}

