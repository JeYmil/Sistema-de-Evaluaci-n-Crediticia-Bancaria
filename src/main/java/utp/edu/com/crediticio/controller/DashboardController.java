package utp.edu.com.crediticio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import utp.edu.com.crediticio.service.cliente.ClienteService;
import utp.edu.com.crediticio.service.solicitud.SolicitudService;

@Controller
public class DashboardController {

    private final ClienteService clienteService;
    private final SolicitudService solicitudService;

    public DashboardController(ClienteService clienteService, SolicitudService solicitudService) {
        this.clienteService = clienteService;
        this.solicitudService = solicitudService;
    }

    @GetMapping("/dashboard")
    public String mostrarDashboard(Model model) {
        var solicitudes = solicitudService.listar();
        model.addAttribute("clientesTotal", clienteService.listar().size());
        model.addAttribute("solicitudesTotal", solicitudes.size());
        model.addAttribute("aprobadasTotal", solicitudes.stream()
                .filter(solicitud -> "Aprobada".equalsIgnoreCase(solicitud.getEstado())).count());
        model.addAttribute("pendientesTotal", solicitudes.stream()
                .filter(solicitud -> "Pendiente".equalsIgnoreCase(solicitud.getEstado())
                        || "En evaluación".equalsIgnoreCase(solicitud.getEstado())
                        || "Revisión manual".equalsIgnoreCase(solicitud.getEstado())).count());
        model.addAttribute("solicitudes", solicitudes.stream().limit(8).toList());
        return "administracion/dashboard";
    }
}

