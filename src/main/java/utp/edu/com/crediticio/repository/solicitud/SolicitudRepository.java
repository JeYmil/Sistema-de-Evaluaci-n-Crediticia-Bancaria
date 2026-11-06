package utp.edu.com.crediticio.repository.solicitud;

import utp.edu.com.crediticio.model.solicitud.Solicitud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/** Consultas y operaciones CRUD de solicitudes. */
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    List<Solicitud> findByCliente_IdCliente(Long idCliente);
    @Query("select s from Solicitud s join fetch s.cliente order by s.idSolicitud desc")
    List<Solicitud> findByClienteIsNotNull();

    @Query("select s from Solicitud s join fetch s.cliente order by s.fecha desc")
    List<Solicitud> buscarTodasParaReporte();

    @Query("select s from Solicitud s join fetch s.cliente where s.fecha >= :fechaInicio order by s.fecha desc")
    List<Solicitud> buscarParaReporteDesde(@Param("fechaInicio") String fechaInicio);

    @Query("select s from Solicitud s join fetch s.cliente where s.fecha <= :fechaFin order by s.fecha desc")
    List<Solicitud> buscarParaReporteHasta(@Param("fechaFin") String fechaFin);

    @Query("select s from Solicitud s join fetch s.cliente where s.fecha >= :fechaInicio "
            + "and s.fecha <= :fechaFin order by s.fecha desc")
    List<Solicitud> buscarParaReporteEntre(@Param("fechaInicio") String fechaInicio,
            @Param("fechaFin") String fechaFin);
}



