package utp.edu.com.crediticio.model.solicitud;

import java.math.BigDecimal;

/** Datos simples que el API entrega al navegador en JSON. */
public record SolicitudApiRespuesta(
        Long id,
        String codigo,
        String cliente,
        String dni,
        String tipoCredito,
        BigDecimal monto,
        Integer plazoMeses,
        String fecha,
        String estado) {

    public static SolicitudApiRespuesta desde(Solicitud solicitud) {
        var cliente = solicitud.getCliente();
        return new SolicitudApiRespuesta(
                solicitud.getIdSolicitud(),
                solicitud.getCodigo(),
                cliente == null ? "Cliente sin vincular" : cliente.getNombre(),
                cliente == null ? "" : cliente.getDni(),
                solicitud.getTipoCredito(),
                solicitud.getMonto(),
                solicitud.getPlazoMeses(),
                solicitud.getFecha(),
                solicitud.getEstado());
    }
}
