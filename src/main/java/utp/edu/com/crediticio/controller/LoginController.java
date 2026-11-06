package utp.edu.com.crediticio.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import utp.edu.com.crediticio.model.cliente.RegistroClienteForm;
import utp.edu.com.crediticio.service.AutenticacionService;

@Controller
public class LoginController {
    private final AutenticacionService autenticacion;
    public LoginController(AutenticacionService autenticacion) { this.autenticacion=autenticacion; }
    @GetMapping("/") public String inicio() { return "redirect:/login"; }
    @GetMapping("/login") public String mostrarLogin() { return "login"; }

    @PostMapping("/login")
    public String ingresar(@RequestParam String usuario, @RequestParam String password,
            HttpSession session, RedirectAttributes flash) {
        var cuenta=autenticacion.autenticar(usuario, password);
        if (cuenta==null) { flash.addFlashAttribute("error", "Credenciales incorrectas o cuenta inactiva."); return "redirect:/login"; }
        session.setAttribute("tipoCuenta", cuenta.tipo()); session.setAttribute("cuentaId", cuenta.id()); session.setAttribute("nombreCuenta", cuenta.nombre());
        return "CLIENTE".equals(cuenta.tipo()) ? "redirect:/portal-cliente" : "redirect:/dashboard";
    }

    @GetMapping("/registro-cliente")
    public String registro(Model model) { model.addAttribute("registro", new RegistroClienteForm()); return "registro-cliente"; }

    @PostMapping("/registro-cliente")
    public String registrar(@Valid @ModelAttribute("registro") RegistroClienteForm form, BindingResult result,
            HttpSession session, Model model, RedirectAttributes flash) {
        if (result.hasErrors()) return "registro-cliente";
        try {
            autenticacion.registrarCliente(form);
            var cuenta=autenticacion.autenticar(form.getDni(), form.getClave());
            session.setAttribute("tipoCuenta", cuenta.tipo()); session.setAttribute("cuentaId", cuenta.id()); session.setAttribute("nombreCuenta", cuenta.nombre());
            flash.addFlashAttribute("mensaje", "Cuenta de cliente creada correctamente.");
            return "redirect:/portal-cliente";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage()); return "registro-cliente";
        }
    }

    @GetMapping("/logout")
    public String salir(HttpSession session) { session.invalidate(); return "redirect:/login"; }
}
