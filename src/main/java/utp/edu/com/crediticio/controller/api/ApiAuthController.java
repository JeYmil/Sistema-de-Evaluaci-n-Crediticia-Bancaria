package utp.edu.com.crediticio.controller.api;

import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import utp.edu.com.crediticio.model.cliente.RegistroClienteForm;
import utp.edu.com.crediticio.service.AutenticacionService;
import utp.edu.com.crediticio.service.security.JwtService;
import utp.edu.com.crediticio.exception.api.AccesoNoAutorizadoException;

@RestController
@RequestMapping("/api/v1/auth")
public class ApiAuthController {
    private final AutenticacionService autenticacion;
    private final JwtService jwtService;
    public ApiAuthController(AutenticacionService autenticacion, JwtService jwtService) {
        this.autenticacion=autenticacion;
        this.jwtService=jwtService;
    }
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody LoginApi datos) {
        var cuenta=autenticacion.autenticar(datos.usuario(), datos.password());
        if (cuenta==null) throw new AccesoNoAutorizadoException("Credenciales incorrectas o cuenta inactiva.");
        var token=jwtService.emitir(cuenta);
        return Map.of("accessToken",token.accessToken(),"tokenType","Bearer",
                "expiresInSeconds",token.expiresInSeconds(),"tipo",cuenta.tipo(),
                "id",cuenta.id(),"nombre",cuenta.nombre());
    }
    @PostMapping("/registro-cliente") @ResponseStatus(HttpStatus.CREATED)
    public Map<String,String> registro(@Valid @RequestBody RegistroClienteForm form) {
        autenticacion.registrarCliente(form); return Map.of("mensaje","Cuenta registrada. Ya puede iniciar sesión.");
    }
    @PostMapping("/logout")
    public Map<String,String> logout() {
        return Map.of("mensaje","Elimina el token del cliente. El token deja de ser válido al vencer.");
    }
    public record LoginApi(String usuario,String password) { }
}

