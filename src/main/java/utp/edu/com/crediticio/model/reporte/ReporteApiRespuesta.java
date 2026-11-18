package utp.edu.com.crediticio.model.reporte;

import java.math.BigDecimal;
import java.util.List;
import utp.edu.com.crediticio.model.solicitud.Solicitud;
import utp.edu.com.crediticio.model.solicitud.SolicitudApiRespuesta;

/** Resumen del reporte entregado por REST como JSON. */
public record ReporteApiRespuesta(
        long total,
        long aprobadas,
        long rechazadas,
        long pendientes,
        BigDecimal montoSolicitado,
        BigDecimal tasaAprobacion,
        BigDecimal tasaRechazo,
        List<ReporteMensual> resumenMensual,
        List<SolicitudApiRespuesta> solicitudes) {

    public static ReporteApiRespuesta desde(ReporteResumen resumen) {
        List<SolicitudApiRespuesta> solicitudes = resumen.getSolicitudes().stream()
                .map(SolicitudApiRespuesta::desde)
                .toList();
        return new ReporteApiRespuesta(resumen.getTotal(), resumen.getAprobadas(), resumen.getRechazadas(),
                resumen.getPendientes(), resumen.getMontoSolicitado(), resumen.getTasaAprobacion(),
                resumen.getTasaRechazo(), resumen.getResumenMensual(), solicitudes);
    }
}
