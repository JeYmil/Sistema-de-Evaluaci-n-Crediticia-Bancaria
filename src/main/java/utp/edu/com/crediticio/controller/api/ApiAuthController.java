package utp.edu.com.crediticio.controller.api;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import utp.edu.com.crediticio.model.cliente.RegistroClienteForm;
import utp.edu.com.crediticio.service.AutenticacionService;

@RestController
@RequestMapping("/api/v1/auth")
public class ApiAuthController {
    private final AutenticacionService autenticacion;
    public ApiAuthController(AutenticacionService autenticacion) { this.autenticacion=autenticacion; }
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody LoginApi datos, HttpSession session) {
        var cuenta=autenticacion.autenticar(datos.usuario(), datos.password());
        if (cuenta==null) throw new AccesoNoAutorizadoException("Credenciales incorrectas o cuenta inactiva.");
        session.setAttribute("tipoCuenta",cuenta.tipo()); session.setAttribute("cuentaId",cuenta.id()); session.setAttribute("nombreCuenta",cuenta.nombre());
        return Map.of("tipo",cuenta.tipo(),"id",cuenta.id(),"nombre",cuenta.nombre());
    }
    @PostMapping("/registro-cliente") @ResponseStatus(HttpStatus.CREATED)
    public Map<String,String> registro(@Valid @RequestBody RegistroClienteForm form) {
        autenticacion.registrarCliente(form); return Map.of("mensaje","Cuenta registrada. Ya puede iniciar sesión.");
    }
    @PostMapping("/logout") public Map<String,String> logout(HttpSession session) { session.invalidate(); return Map.of("mensaje","Sesión cerrada."); }
    public record LoginApi(String usuario,String password) { }
}
