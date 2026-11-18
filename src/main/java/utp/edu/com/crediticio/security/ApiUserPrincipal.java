package utp.edu.com.crediticio.security;

/** Identidad mínima de una cuenta autenticada para solicitudes REST. */
public record ApiUserPrincipal(Long id, String tipo) { }
