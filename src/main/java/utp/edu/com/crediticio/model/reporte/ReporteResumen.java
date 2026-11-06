package utp.edu.com.crediticio.model.reporte;

import java.math.BigDecimal;
import java.util.List;
import utp.edu.com.crediticio.model.solicitud.Solicitud;

/** Datos calculados para la pantalla de resumen y detalle del reporte. */
public class ReporteResumen {
    private final List<Solicitud> solicitudes;
    private final List<ReporteMensual> resumenMensual;
    private final long total;
    private final long aprobadas;
    private final long rechazadas;
    private final long pendientes;
    private final BigDecimal montoSolicitado;
    private final BigDecimal tasaAprobacion;
    private final BigDecimal tasaRechazo;

    public ReporteResumen(List<Solicitud> solicitudes, List<ReporteMensual> resumenMensual,
            long total, long aprobadas, long rechazadas, long pendientes,
            BigDecimal montoSolicitado, BigDecimal tasaAprobacion, BigDecimal tasaRechazo) {
        this.solicitudes = solicitudes;
        this.resumenMensual = resumenMensual;
        this.total = total;
        this.aprobadas = aprobadas;
        this.rechazadas = rechazadas;
        this.pendientes = pendientes;
        this.montoSolicitado = montoSolicitado;
        this.tasaAprobacion = tasaAprobacion;
        this.tasaRechazo = tasaRechazo;
    }

    public List<Solicitud> getSolicitudes() { return solicitudes; }
    public List<ReporteMensual> getResumenMensual() { return resumenMensual; }
    public long getTotal() { return total; }
    public long getAprobadas() { return aprobadas; }
    public long getRechazadas() { return rechazadas; }
    public long getPendientes() { return pendientes; }
    public BigDecimal getMontoSolicitado() { return montoSolicitado; }
    public BigDecimal getTasaAprobacion() { return tasaAprobacion; }
    public BigDecimal getTasaRechazo() { return tasaRechazo; }
}

