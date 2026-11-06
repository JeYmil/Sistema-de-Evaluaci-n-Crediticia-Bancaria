package utp.edu.com.crediticio.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import utp.edu.com.crediticio.model.usuario.Usuario;
import utp.edu.com.crediticio.service.usuario.UsuarioService;

@Controller
public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) { this.usuarioService = usuarioService; }

    @GetMapping("/usuarios")
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        return "administracion/usuarios";
    }

    @GetMapping("/usuarios/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "administracion/registrar-usuario";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model, RedirectAttributes flash) {
        Usuario usuario = usuarioService.buscar(id);
        if (usuario == null) {
            flash.addFlashAttribute("error", "No se encontró el usuario.");
            return "redirect:/usuarios";
        }
        model.addAttribute("usuario", usuario);
        return "administracion/registrar-usuario";
    }

    @PostMapping("/usuarios/guardar")
    public String guardar(@Valid @ModelAttribute("usuario") Usuario usuario,
            BindingResult result, RedirectAttributes flash) {
        try {
            if (!result.hasErrors()) usuarioService.guardar(usuario);
        } catch (IllegalArgumentException exception) {
            result.reject("usuario.invalido", exception.getMessage());
        }
        if (result.hasErrors()) return "administracion/registrar-usuario";
        flash.addFlashAttribute("mensaje", "Usuario del banco guardado correctamente.");
        return "redirect:/usuarios";
    }

    @PostMapping("/usuarios/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id, RedirectAttributes flash) {
        usuarioService.eliminar(id);
        flash.addFlashAttribute("mensaje", "Usuario eliminado correctamente.");
        return "redirect:/usuarios";
    }
}
