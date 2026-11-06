package utp.edu.com.crediticio.service.evaluacion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.com.crediticio.model.evaluacion.Evaluacion;
import utp.edu.com.crediticio.model.politica.Politica;
import utp.edu.com.crediticio.model.solicitud.Solicitud;
import utp.edu.com.crediticio.repository.evaluacion.EvaluacionRepository;
import utp.edu.com.crediticio.repository.politica.PoliticaRepository;
import utp.edu.com.crediticio.repository.solicitud.SolicitudRepository;

/** Aplica reglas demostrativas y transparentes al registrar una solicitud. */
@Service
public class EvaluacionAutomaticaService {
    private final EvaluacionRepository evaluacionRepository;
    private final SolicitudRepository solicitudRepository;
    private final PoliticaRepository politicaRepository;

    public EvaluacionAutomaticaService(EvaluacionRepository evaluacionRepository,
            SolicitudRepository solicitudRepository, PoliticaRepository politicaRepository) {
        this.evaluacionRepository = evaluacionRepository;
        this.solicitudRepository = solicitudRepository;
        this.politicaRepository = politicaRepository;
    }

    @Transactional
    public Evaluacion evaluarNuevaSolicitud(Solicitud solicitud) {
        Politica politica = politicaRepository.findFirstByProductoIgnoreCaseAndEstadoIgnoreCase(
                nombreProducto(solicitud.getTipoCredito()), "Activa").orElse(null);
        int minimoAprobar = politica == null || politica.getPuntajeMinimoAprobar() == null ? 650 : politica.getPuntajeMinimoAprobar();
        int maximoRechazo = politica == null || politica.getPuntajeMaximoRechazo() == null ? 499 : politica.getPuntajeMaximoRechazo();
        BigDecimal dtiAprobar = politica == null || politica.getDtiMaximoAprobacion() == null ? new BigDecimal("35") : politica.getDtiMaximoAprobacion();
        BigDecimal dtiRechazar = politica == null || politica.getDtiMinimoRechazo() == null ? new BigDecimal("50") : politica.getDtiMinimoRechazo();

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setSolicitud(solicitud);
        evaluacion.setCodigo("EV-AUTO-%03d".formatted(solicitud.getIdSolicitud()));
        evaluacion.setPolitica(politica == null ? "Regla general" : politica.getCodigo() + " · " + politica.getVersion());
        BigDecimal ingresos = solicitud.getIngresosMensuales();
        BigDecimal gastos = cero(solicitud.getGastosMensuales());
        BigDecimal deudas = cero(solicitud.getDeudasMensuales());
        if (ingresos == null || ingresos.signum() <= 0 || solicitud.getMonto() == null || solicitud.getPlazoMeses() == null) {
            evaluacion.setResultado("Revisión manual");
            evaluacion.setNivelRiesgo("Por evaluar");
            evaluacion.setComentario("Faltan datos financieros; el personal del banco debe completar la evaluación.");
            solicitud.setEstado("Revisión manual");
        } else {
            BigDecimal cuota = solicitud.getMonto().divide(BigDecimal.valueOf(Math.max(1, solicitud.getPlazoMeses())), 2, RoundingMode.HALF_UP);
            BigDecimal dti = deudas.add(cuota).multiply(BigDecimal.valueOf(100)).divide(ingresos, 2, RoundingMode.HALF_UP);
            BigDecimal disponible = ingresos.subtract(gastos).subtract(deudas);
            int puntaje = Math.max(300, Math.min(850, 850 - dti.multiply(BigDecimal.valueOf(5)).intValue()));
            evaluacion.setDti(dti);
            evaluacion.setIngresoDisponible(disponible);
            evaluacion.setCapacidadNuevaCuota(disponible.max(BigDecimal.ZERO));
            evaluacion.setMontoMaximoEstimado(disponible.max(BigDecimal.ZERO).multiply(BigDecimal.valueOf(solicitud.getPlazoMeses())));
            evaluacion.setPuntaje(puntaje);
            evaluacion.setNivelRiesgo(dti.compareTo(dtiAprobar) <= 0 ? "Bajo" : dti.compareTo(dtiRechazar) >= 0 ? "Alto" : "Medio");
            if (dti.compareTo(dtiRechazar) >= 0 || disponible.signum() <= 0 || puntaje <= maximoRechazo) {
                evaluacion.setResultado("Rechazado");
                evaluacion.setComentario("Regla automática: el nivel de deuda o la capacidad de pago no cumple el mínimo.");
                solicitud.setEstado("Rechazada");
            } else if (puntaje >= minimoAprobar && dti.compareTo(dtiAprobar) <= 0 && disponible.compareTo(cuota) >= 0) {
                evaluacion.setResultado("Aprobado");
                evaluacion.setComentario("Regla automática: puntaje y capacidad de pago cumplen la política vigente.");
                solicitud.setEstado("Aprobada");
            } else {
                evaluacion.setResultado("Revisión manual");
                evaluacion.setComentario("El caso está entre los límites; requiere revisión del personal del banco.");
                solicitud.setEstado("Revisión manual");
            }
        }
        solicitudRepository.save(solicitud);
        return evaluacionRepository.save(evaluacion);
    }

    private static BigDecimal cero(BigDecimal valor) { return valor == null ? BigDecimal.ZERO : valor; }
    private static String nombreProducto(String tipo) {
        if (tipo == null) return "";
        return switch (tipo) {
            case "Personal" -> "Crédito personal";
            case "Vehicular" -> "Crédito vehicular";
            case "Hipotecario" -> "Crédito hipotecario";
            case "Emprendimiento" -> "Crédito para emprendimiento";
            case "Educativo" -> "Crédito educativo";
            default -> tipo;
        };
    }
}
