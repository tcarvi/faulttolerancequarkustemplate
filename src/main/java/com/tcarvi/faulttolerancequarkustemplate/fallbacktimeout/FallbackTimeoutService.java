package com.tcarvi.faulttolerancequarkustemplate.fallbacktimeout;

public class FallbackTimeoutService {

    public String check() {
        // try {
        //     Thread.sleep(700L); // Suposição do tempo do processamento.
        // }
        // catch (InterruptedException ex) {
        //     throw new InterruptedException("TextInterruptedException");
        // }
        return "Sem problema de Timeout. Processo concluiu com sucesso antes do timeout.";
    }

    public String fallback() {
        return "fallbackTimeoutServiceHandler executed";
    }

}
