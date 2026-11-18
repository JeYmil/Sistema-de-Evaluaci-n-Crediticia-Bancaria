package utp.edu.com.crediticio.controller.api;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.com.crediticio.exception.api.RecursoNoEncontradoException;
import utp.edu.com.crediticio.model.cliente.Cliente;
import utp.edu.com.crediticio.model.solicitud.SolicitudApiRespuesta;
import utp.edu.com.crediticio.security.ApiUserPrincipal;
import utp.edu.com.crediticio.service.cliente.ClienteService;
import utp.edu.com.crediticio.service.solicitud.SolicitudService;

/** Endpoints REST que permiten a cada cliente consultar únicamente sus propios datos. */
@RestController
@RequestMapping("/api/v1/clientes/mi-cuenta")
public class ApiClienteController {
    private final ClienteService clientes;
    private final SolicitudService solicitudes;

    public ApiClienteController(ClienteService clientes, SolicitudService solicitudes) {
        this.clientes = clientes;
        this.solicitudes = solicitudes;
    }

    @GetMapping
    public Cliente miPerfil(Authentication autenticacion) {
        return clienteActual(autenticacion);
    }

    @GetMapping("/solicitudes")
    public List<SolicitudApiRespuesta> misSolicitudes(Authentication autenticacion) {
        Cliente cliente = clienteActual(autenticacion);
        return solicitudes.listarPorCliente(cliente.getIdCliente()).stream()
                .map(SolicitudApiRespuesta::desde)
                .toList();
    }

    private Cliente clienteActual(Authentication autenticacion) {
        Long id;
        String tipo;
        if (autenticacion.getPrincipal() instanceof Jwt jwt) {
            id = ((Number) jwt.getClaim("accountId")).longValue();
            tipo = jwt.getClaimAsString("tipo");
        } else if (autenticacion.getPrincipal() instanceof ApiUserPrincipal principal) {
            id = principal.id();
            tipo = principal.tipo();
        } else {
            throw new RecursoNoEncontradoException("No se encontró el cliente de la sesión.");
        }
        if (!"CLIENTE".equals(tipo)) {
            throw new RecursoNoEncontradoException("No se encontró el cliente de la sesión.");
        }
        Cliente cliente = clientes.buscar(id);
        if (cliente == null) throw new RecursoNoEncontradoException("Cliente no encontrado.");
        return cliente;
    }
}
