package utp.edu.com.crediticio.controller;

import java.time.LocalDate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import utp.edu.com.crediticio.model.reporte.ReporteResumen;
import utp.edu.com.crediticio.service.reporte.ReporteExcelService;
import utp.edu.com.crediticio.service.reporte.ReporteService;
import utp.edu.com.crediticio.service.evaluacion.EvaluacionService;

@Controller
public class ReporteController {
    private final ReporteService reporteService;
    private final ReporteExcelService excelService;
    private final EvaluacionService evaluacionService;
    public ReporteController(ReporteService reporteService, ReporteExcelService excelService, EvaluacionService evaluacionService) {
        this.reporteService=reporteService; this.excelService=excelService; this.evaluacionService=evaluacionService;
    }
    @GetMapping("/reportes")
    public String mostrarReportes(@RequestParam(name="fechaInicio",required=false) String inicio,
            @RequestParam(name="fechaFin",required=false) String fin,
            @RequestParam(name="persona",required=false) String persona, Model model) {
        try {
            model.addAttribute("resumen",reporteService.generar(inicio,fin,persona));
            model.addAttribute("fechaInicio",inicio==null?"":inicio); model.addAttribute("fechaFin",fin==null?"":fin);
            model.addAttribute("persona",persona==null?"":persona);
        } catch(IllegalArgumentException ex) {
            model.addAttribute("error",ex.getMessage()); model.addAttribute("resumen",reporteService.generar(null,null));
            model.addAttribute("fechaInicio",""); model.addAttribute("fechaFin",""); model.addAttribute("persona","");
        }
        return "reportes/reportes";
    }
    @GetMapping("/reportes/detalles")
    public String verDetalles(@RequestParam(name="fechaInicio",required=false) String inicio,
            @RequestParam(name="fechaFin",required=false) String fin,
            @RequestParam(name="persona",required=false) String persona, Model model) {
        try {
            ReporteResumen r=reporteService.generar(inicio,fin,persona); model.addAttribute("solicitudes",r.getSolicitudes());
            java.util.Map<Long,utp.edu.com.crediticio.model.evaluacion.Evaluacion> porSolicitud=new java.util.HashMap<>();
            r.getSolicitudes().forEach(s -> { var ev=evaluacionService.buscarPorSolicitud(s.getIdSolicitud()); if(ev!=null) porSolicitud.put(s.getIdSolicitud(),ev); });
            model.addAttribute("evaluacionesPorSolicitud",porSolicitud);
            model.addAttribute("fechaInicio",inicio==null?"":inicio); model.addAttribute("fechaFin",fin==null?"":fin); model.addAttribute("persona",persona==null?"":persona);
        } catch(IllegalArgumentException ex) {
            model.addAttribute("error",ex.getMessage()); model.addAttribute("solicitudes",reporteService.generar(null,null).getSolicitudes());
            model.addAttribute("evaluacionesPorSolicitud",java.util.Map.of());
            model.addAttribute("fechaInicio",""); model.addAttribute("fechaFin",""); model.addAttribute("persona","");
        }
        return "reportes/detalle-reportes";
    }
    @GetMapping("/reportes/descargar")
    public Object descargarExcel(@RequestParam(name="fechaInicio",required=false) String inicio,
            @RequestParam(name="fechaFin",required=false) String fin,
            @RequestParam(name="persona",required=false) String persona, RedirectAttributes flash) {
        try { return respuestaExcel(reporteService.generar(inicio,fin,persona)); }
        catch(IllegalArgumentException ex) { flash.addFlashAttribute("error",ex.getMessage()); return "redirect:/reportes"; }
    }
    /** El mismo reporte también se ofrece como endpoint REST para integraciones. */
    @GetMapping("/api/v1/reportes/excel")
    public ResponseEntity<ByteArrayResource> descargarExcelApi(@RequestParam(name="fechaInicio",required=false) String inicio,
            @RequestParam(name="fechaFin",required=false) String fin, @RequestParam(name="persona",required=false) String persona) {
        return respuestaExcel(reporteService.generar(inicio,fin,persona));
    }
    private ResponseEntity<ByteArrayResource> respuestaExcel(ReporteResumen reporte) {
        byte[] archivo=excelService.generar(reporte);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=reporte-crediticio-"+ LocalDate.now()+".xlsx")
                .body(new ByteArrayResource(archivo));
    }
}
