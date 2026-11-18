package utp.edu.com.crediticio.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.com.crediticio.model.reporte.ReporteApiRespuesta;
import utp.edu.com.crediticio.service.reporte.ReporteService;

/** API JSON para consultar resúmenes y detalles de reportes. */
@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteApiController {
    private final ReporteService reporteService;

    public ReporteApiController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public ReporteApiRespuesta consultar(
            @RequestParam(name = "fechaInicio", required = false) String fechaInicio,
            @RequestParam(name = "fechaFin", required = false) String fechaFin,
            @RequestParam(name = "persona", required = false) String persona) {
        return ReporteApiRespuesta.desde(reporteService.generar(fechaInicio, fechaFin, persona));
    }
}
