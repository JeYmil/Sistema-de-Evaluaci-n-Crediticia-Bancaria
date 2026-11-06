package utp.edu.com.crediticio.service.solicitud;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import utp.edu.com.crediticio.model.solicitud.DocumentoSolicitud;
import utp.edu.com.crediticio.model.solicitud.Solicitud;
import utp.edu.com.crediticio.repository.solicitud.DocumentoSolicitudRepository;
import utp.edu.com.crediticio.repository.solicitud.SolicitudRepository;

@Service
public class DocumentoSolicitudService {
    private static final long MAXIMO_BYTES = 5L * 1024 * 1024;
    private final DocumentoSolicitudRepository documentoRepository;
    private final SolicitudRepository solicitudRepository;

    public DocumentoSolicitudService(DocumentoSolicitudRepository documentoRepository,
            SolicitudRepository solicitudRepository) {
        this.documentoRepository = documentoRepository;
        this.solicitudRepository = solicitudRepository;
    }

    public void validar(List<MultipartFile> archivos) {
        if (archivos == null) return;
        for (MultipartFile archivo : archivos) {
            if (archivo == null || archivo.isEmpty()) continue;
            String tipo=archivo.getContentType();
            if (archivo.getSize() > MAXIMO_BYTES) throw new IllegalArgumentException("Cada archivo debe pesar máximo 5 MB.");
            if (tipo == null || !List.of("application/pdf", "image/jpeg", "image/png").contains(tipo)) {
                throw new IllegalArgumentException("Solo se permiten archivos PDF, JPG o PNG.");
            }
        }
    }

    @Transactional
    public List<DocumentoSolicitud> guardar(Long idSolicitud, List<MultipartFile> archivos) {
        Solicitud solicitud = solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new IllegalArgumentException("No existe la solicitud."));
        validar(archivos);
        for (MultipartFile archivo : archivos) {
            if (archivo == null || archivo.isEmpty()) continue;
            String tipo = archivo.getContentType();
            try {
                String nombre = archivo.getOriginalFilename() == null ? "documento" : archivo.getOriginalFilename();
                nombre = nombre.replaceAll("[^A-Za-z0-9._-]", "_");
                if (nombre.length() > 180) nombre = nombre.substring(nombre.length() - 180);
                DocumentoSolicitud documento = new DocumentoSolicitud();
                documento.setSolicitud(solicitud);
                documento.setNombreArchivo(nombre);
                documento.setTipoContenido(tipo);
                documento.setTamanoBytes(archivo.getSize());
                documento.setContenido(archivo.getBytes());
                documentoRepository.save(documento);
            } catch (IOException e) {
                throw new IllegalArgumentException("No se pudo leer el archivo cargado.");
            }
        }
        return documentoRepository.findBySolicitud_IdSolicitudOrderByFechaSubidaDesc(idSolicitud);
    }

    @Transactional(readOnly = true)
    public List<DocumentoSolicitud> listar(Long idSolicitud) {
        return documentoRepository.findBySolicitud_IdSolicitudOrderByFechaSubidaDesc(idSolicitud);
    }

    @Transactional(readOnly = true)
    public DocumentoSolicitud buscar(Long id) { return documentoRepository.findById(id).orElse(null); }
}
