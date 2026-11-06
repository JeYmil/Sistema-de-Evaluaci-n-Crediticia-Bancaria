package utp.edu.com.crediticio.repository.evaluacion;

import utp.edu.com.crediticio.model.evaluacion.Evaluacion;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/** Consultas y operaciones CRUD de evaluaciones. */
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
    Optional<Evaluacion> findFirstBySolicitud_IdSolicitud(Long idSolicitud);
}



