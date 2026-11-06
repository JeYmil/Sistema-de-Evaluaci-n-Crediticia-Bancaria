package utp.edu.com.crediticio.controller.api;

import java.util.List;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utp.edu.com.crediticio.model.solicitud.DocumentoSolicitud;
import utp.edu.com.crediticio.service.solicitud.DocumentoSolicitudService;

@RestController
@RequestMapping("/api/v1/solicitudes/{idSolicitud}/documentos")
public class DocumentoApiController {
    private final DocumentoSolicitudService servicio;
    public DocumentoApiController(DocumentoSolicitudService servicio) { this.servicio = servicio; }

    @GetMapping public List<DocumentoSolicitud> listar(@PathVariable Long idSolicitud) { return servicio.listar(idSolicitud); }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<DocumentoSolicitud> subir(@PathVariable Long idSolicitud, @RequestParam("archivos") List<MultipartFile> archivos) {
        return servicio.guardar(idSolicitud, archivos);
    }
    @GetMapping("/{idDocumento}/descargar")
    public ResponseEntity<ByteArrayResource> descargar(@PathVariable Long idDocumento) {
        DocumentoSolicitud documento = servicio.buscar(idDocumento);
        if (documento == null) throw new RecursoNoEncontradoException("Documento no encontrado.");
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(documento.getTipoContenido()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documento.getNombreArchivo() + "\"")
                .body(new ByteArrayResource(documento.getContenido()));
    }
}
