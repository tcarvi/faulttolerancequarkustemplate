package com.tcarvi.faulttolerancequarkustemplate.retry;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;

@ApplicationScoped
public class RetryService {

    private static final Logger LOG = Logger.getLogger(RetryService.class);

    public String exec() {
        try {
            Thread.sleep(70L); // A executar algo na Thread.
        } catch (InterruptedException ex) {
            // Lide com a exceção ou throws ex
            LOG.info(ex.getMessage());
        } finally {
            // Execução necessária por causa do código do try-catch anterior.
        }
        return "Sem problema. Ou resolvido com retry. Processo concluiu com sucesso antes do timeout.";
    }
}
