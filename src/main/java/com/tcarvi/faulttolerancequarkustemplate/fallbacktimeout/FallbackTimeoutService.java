package com.tcarvi.faulttolerancequarkustemplate.fallbacktimeout;

public class FallbackTimeoutService {

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
