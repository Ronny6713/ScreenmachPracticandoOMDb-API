package com.alura.screenmatch.excepcion;

public class FalloConvertirDuracionException extends RuntimeException {
    private String mensaje;
    public FalloConvertirDuracionException(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}
