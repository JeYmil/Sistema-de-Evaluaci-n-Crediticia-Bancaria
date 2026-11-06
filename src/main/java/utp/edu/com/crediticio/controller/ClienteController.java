package utp.edu.com.crediticio.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import utp.edu.com.crediticio.model.cliente.Cliente;
import utp.edu.com.crediticio.service.cliente.ClienteService;
import utp.edu.com.crediticio.model.solicitud.Solicitud;
import utp.edu.com.crediticio.model.solicitud.SolicitudPortalForm;
import utp.edu.com.crediticio.service.solicitud.SolicitudService;
import utp.edu.com.crediticio.service.solicitud.DocumentoSolicitudService;

@Controller
public class ClienteController {
    private final ClienteService clienteService;
    private final SolicitudService solicitudService;
    private final DocumentoSolicitudService documentoService;

    public ClienteController(ClienteService clienteService, SolicitudService solicitudService, DocumentoSolicitudService documentoService) {
        this.clienteService = clienteService;
        this.solicitudService = solicitudService;
        this.documentoService = documentoService;
    }

    @GetMapping("/clientes")
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listar());
        return "administracion/clientes";
    }

    @GetMapping("/clientes/registrar")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/registrar-cliente";
    }

    @GetMapping("/clientes/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model, RedirectAttributes flash) {
        Cliente cliente = clienteService.buscar(id);
        if (cliente == null) {
            flash.addFlashAttribute("error", "No se encontró el cliente solicitado.");
            return "redirect:/clientes";
        }
        model.addAttribute("cliente", cliente);
        return "clientes/registrar-cliente";
    }

    @PostMapping("/clientes/guardar")
    public String guardar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, RedirectAttributes flash) {
        if (result.hasErrors()) return "clientes/registrar-cliente";
        try {
            clienteService.guardar(cliente);
            flash.addFlashAttribute("mensaje", "Cliente guardado correctamente.");
            return "redirect:/clientes";
        } catch (IllegalArgumentException exception) {
            result.rejectValue("dni", "duplicado", exception.getMessage());
            return "clientes/registrar-cliente";
        }
    }

    @PostMapping("/clientes/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id, RedirectAttributes flash) {
        try {
            clienteService.eliminar(id);
            flash.addFlashAttribute("mensaje", "Cliente eliminado correctamente.");
        } catch (RuntimeException exception) {
            flash.addFlashAttribute("error", "No se puede eliminar: el cliente tiene solicitudes asociadas.");
        }
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/perfil")
    public String perfil(@RequestParam("id") Long id, Model model, RedirectAttributes flash) {
        Cliente cliente = clienteService.buscar(id);
        if (cliente == null) {
            flash.addFlashAttribute("error", "No se encontró el cliente solicitado.");
            return "redirect:/clientes";
        }
        model.addAttribute("cliente", cliente);
        model.addAttribute("solicitudes", solicitudService.listarPorCliente(id));
        return "clientes/perfil-cliente";
    }

    @GetMapping("/portal-cliente")
    public String portalCliente(HttpSession session, Model model) {
        Long idCliente = (Long) session.getAttribute("cuentaId");
        Cliente cliente = idCliente == null ? null : clienteService.buscar(idCliente);
        model.addAttribute("clienteActual", cliente);
        model.addAttribute("solicitudPortal", new SolicitudPortalForm());
        model.addAttribute("solicitudes", cliente == null ? java.util.List.of()
                : solicitudService.listarPorCliente(cliente.getIdCliente()));
        return "clientes/portal-cliente";
    }

    @PostMapping("/portal-cliente/solicitar")
    public String solicitudPortal(@Valid @ModelAttribute("solicitudPortal") SolicitudPortalForm form,
            BindingResult result, Model model, RedirectAttributes flash, HttpSession session,
            @RequestParam(name = "archivos", required = false) List<MultipartFile> archivos) {
        Long idCliente = (Long) session.getAttribute("cuentaId");
        Cliente cliente = idCliente == null ? null : clienteService.buscar(idCliente);
        if (cliente == null) return "redirect:/login";
        if (form.getPlazo() != null && !List.of(6, 12, 18, 24, 36, 48, 60, 120).contains(form.getPlazo())) {
            result.rejectValue("plazo", "plazo.invalido", "Selecciona uno de los plazos disponibles.");
        }
        if (result.hasErrors()) {
            model.addAttribute("clienteActual", cliente);
            model.addAttribute("solicitudes", solicitudService.listarPorCliente(cliente.getIdCliente()));
            return "clientes/portal-cliente";
        }

        try {
            documentoService.validar(archivos);
        } catch (IllegalArgumentException exception) {
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("clienteActual", cliente);
            model.addAttribute("solicitudes", solicitudService.listarPorCliente(cliente.getIdCliente()));
            return "clientes/portal-cliente";
        }

        Solicitud solicitud = new Solicitud();
        solicitud.setIdCliente(cliente.getIdCliente());
        solicitud.setTipoCredito(form.getTipoCredito());
        solicitud.setMonto(form.getMontoSolicitado());
        solicitud.setPlazoMeses(form.getPlazo());
        solicitud.setMotivo(form.getMotivoCredito().trim());
        solicitud.setIngresosMensuales(form.getIngresosMensuales());
        solicitud.setGastosMensuales(form.getGastosMensuales());
        solicitud.setDeudasMensuales(form.getDeudasMensuales());
        solicitud.setEstado("Pendiente");
        Solicitud guardada = solicitudService.guardar(solicitud);
        if (archivos != null && !archivos.isEmpty()) documentoService.guardar(guardada.getIdSolicitud(), archivos);
        flash.addFlashAttribute("mensaje", "Solicitud registrada y evaluada automáticamente.");
        return "redirect:/portal-cliente";
    }
}

