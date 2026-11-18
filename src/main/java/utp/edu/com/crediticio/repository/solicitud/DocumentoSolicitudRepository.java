package utp.edu.com.crediticio.repository.solicitud;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import utp.edu.com.crediticio.model.solicitud.DocumentoSolicitud;

public interface DocumentoSolicitudRepository extends JpaRepository<DocumentoSolicitud, Long> {
    List<DocumentoSolicitud> findBySolicitud_IdSolicitudOrderByFechaSubidaDesc(Long idSolicitud);
}
