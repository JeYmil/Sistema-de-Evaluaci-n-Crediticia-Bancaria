package utp.edu.com.crediticio.controller.api;

public class AccesoNoAutorizadoException extends RuntimeException {
    public AccesoNoAutorizadoException(String mensaje) { super(mensaje); }
}
