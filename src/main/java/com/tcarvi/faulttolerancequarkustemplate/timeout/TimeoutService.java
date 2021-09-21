package com.tcarvi.faulttolerancequarkustemplate.timeout;

public class TimeoutService {
    public String getText() {
        try {
            Thread.sleep(700L); // Suposição do tempo do processamento.
        } catch (InterruptedException e) {
            // Tratamento do problema de atraso no processamento.
        }
        return "Sem problema de Timeout. Processo concluiu com sucesso antes do timeout.";
    }
}

