package utp.edu.com.crediticio.model.reporte;

/** Totales agrupados por mes para presentarlos en la vista de reportes. */
public class ReporteMensual {
    private final String mes;
    private final long total;
    private final long aprobadas;
    private final long rechazadas;
    private final long pendientes;

    public ReporteMensual(String mes, long total, long aprobadas, long rechazadas, long pendientes) {
        this.mes = mes;
        this.total = total;
        this.aprobadas = aprobadas;
        this.rechazadas = rechazadas;
        this.pendientes = pendientes;
    }

    public String getMes() { return mes; }
    public long getTotal() { return total; }
    public long getAprobadas() { return aprobadas; }
    public long getRechazadas() { return rechazadas; }
    public long getPendientes() { return pendientes; }
}

