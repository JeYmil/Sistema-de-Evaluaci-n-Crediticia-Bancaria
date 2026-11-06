package utp.edu.com.crediticio.service.reporte;

import utp.edu.com.crediticio.model.reporte.ReporteResumen;
import utp.edu.com.crediticio.model.reporte.ReporteMensual;
import utp.edu.com.crediticio.model.solicitud.Solicitud;
import utp.edu.com.crediticio.repository.solicitud.SolicitudRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Consulta solicitudes persistidas y calcula indicadores de lectura para los reportes. */
@Service
public class ReporteService {
    private static final DateTimeFormatter ETIQUETA_MES = DateTimeFormatter.ofPattern("MM/yyyy");
    private final SolicitudRepository solicitudRepository;

    public ReporteService(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    @Transactional(readOnly = true)
    public ReporteResumen generar(String fechaInicioTexto, String fechaFinTexto) {
        return generar(fechaInicioTexto, fechaFinTexto, null);
    }

    @Transactional(readOnly = true)
    public ReporteResumen generar(String fechaInicioTexto, String fechaFinTexto, String persona) {
        LocalDate fechaInicio = parsearFecha(fechaInicioTexto);
        LocalDate fechaFin = parsearFecha(fechaFinTexto);
        if (fechaInicio != null && fechaFin != null && fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha inicial no puede ser posterior a la fecha final.");
        }

        List<Solicitud> solicitudes = consultarSolicitudes(fechaInicio, fechaFin).stream()
                .filter(solicitud -> dentroDelPeriodo(solicitud, fechaInicio, fechaFin))
                .filter(solicitud -> coincidePersona(solicitud, persona))
                .sorted((a, b) -> compararFechas(b.getFecha(), a.getFecha()))
                .toList();

        long total = solicitudes.size();
        long aprobadas = solicitudes.stream().filter(ReporteService::estaAprobada).count();
        long rechazadas = solicitudes.stream().filter(ReporteService::estaRechazada).count();
        long pendientes = total - aprobadas - rechazadas;
        BigDecimal monto = solicitudes.stream().map(Solicitud::getMonto)
                .filter(valor -> valor != null).reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<YearMonth, ContadoresMes> meses = new TreeMap<>();
        for (Solicitud solicitud : solicitudes) {
            LocalDate fecha = parsearFechaSinError(solicitud.getFecha());
            if (fecha == null) continue;
            ContadoresMes contador = meses.computeIfAbsent(YearMonth.from(fecha), ignorado -> new ContadoresMes());
            contador.total++;
            if (estaAprobada(solicitud)) contador.aprobadas++;
            else if (estaRechazada(solicitud)) contador.rechazadas++;
            else contador.pendientes++;
        }
        List<ReporteMensual> mensual = new ArrayList<>();
        meses.forEach((mes, contador) -> mensual.add(new ReporteMensual(
                mes.format(ETIQUETA_MES), contador.total, contador.aprobadas,
                contador.rechazadas, contador.pendientes)));

        return new ReporteResumen(solicitudes, mensual, total, aprobadas, rechazadas,
                pendientes, monto, porcentaje(aprobadas, total), porcentaje(rechazadas, total));
    }

    private static boolean coincidePersona(Solicitud solicitud, String persona) {
        if (persona == null || persona.isBlank()) return true;
        if (solicitud.getCliente() == null) return false;
        String filtro=persona.trim().toLowerCase();
        String nombre=String.valueOf(solicitud.getCliente().getNombre()).toLowerCase();
        String dni=String.valueOf(solicitud.getCliente().getDni()).toLowerCase();
        return nombre.contains(filtro) || dni.contains(filtro);
    }

    private List<Solicitud> consultarSolicitudes(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio != null && fechaFin != null) {
            return solicitudRepository.buscarParaReporteEntre(fechaInicio.toString(), fechaFin.toString());
        }
        if (fechaInicio != null) return solicitudRepository.buscarParaReporteDesde(fechaInicio.toString());
        if (fechaFin != null) return solicitudRepository.buscarParaReporteHasta(fechaFin.toString());
        return solicitudRepository.buscarTodasParaReporte();
    }

    private static boolean dentroDelPeriodo(Solicitud solicitud, LocalDate inicio, LocalDate fin) {
        if (inicio == null && fin == null) return true;
        LocalDate fecha = parsearFechaSinError(solicitud.getFecha());
        if (fecha == null) return false;
        return (inicio == null || !fecha.isBefore(inicio)) && (fin == null || !fecha.isAfter(fin));
    }

    private static LocalDate parsearFecha(String texto) {
        if (texto == null || texto.isBlank()) return null;
        try {
            return LocalDate.parse(texto.trim());
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("Las fechas deben tener el formato válido AAAA-MM-DD.");
        }
    }

    private static LocalDate parsearFechaSinError(String texto) {
        try { return texto == null || texto.isBlank() ? null : LocalDate.parse(texto.trim()); }
        catch (DateTimeParseException exception) { return null; }
    }

    private static int compararFechas(String primera, String segunda) {
        return String.valueOf(primera).compareTo(String.valueOf(segunda));
    }

    private static boolean estaAprobada(Solicitud solicitud) {
        return "Aprobada".equalsIgnoreCase(solicitud.getEstado())
                || "Aprobado".equalsIgnoreCase(solicitud.getEstado());
    }

    private static boolean estaRechazada(Solicitud solicitud) {
        return "Rechazada".equalsIgnoreCase(solicitud.getEstado())
                || "Rechazado".equalsIgnoreCase(solicitud.getEstado());
    }

    private static BigDecimal porcentaje(long parte, long total) {
        if (total == 0) return BigDecimal.ZERO.setScale(1);
        return BigDecimal.valueOf(parte).multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(total), 1, RoundingMode.HALF_UP);
    }

    private static class ContadoresMes {
        private long total;
        private long aprobadas;
        private long rechazadas;
        private long pendientes;
    }
}


