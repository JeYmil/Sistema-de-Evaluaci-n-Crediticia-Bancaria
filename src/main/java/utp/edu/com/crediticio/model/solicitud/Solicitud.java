package utp.edu.com.crediticio.model.solicitud;

import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import utp.edu.com.crediticio.model.cliente.Cliente;

/** Representa una solicitud de crédito. */
@Entity
@Table(name = "solicitudes")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;
    private String codigo;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    @Transient
    private Long idCliente;
    @NotBlank(message = "Selecciona el tipo de crédito.")
    @Pattern(regexp = "^(Personal|Vehicular|Hipotecario|Emprendimiento|Educativo)$", message = "Selecciona un tipo de crédito válido.")
    private String tipoCredito;
    @NotNull(message = "Ingresa el monto solicitado.")
    @DecimalMin(value = "500.00", message = "El monto mínimo es S/ 500.")
    private BigDecimal monto;
    @NotNull(message = "Ingresa el plazo en meses.")
    @Min(value = 1, message = "El plazo debe ser de al menos un mes.")
    private Integer plazoMeses;
    @Pattern(regexp = "^$|[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "La fecha debe tener el formato año-mes-día.")
    private String fecha;
    @Pattern(regexp = "^(Pendiente|En evaluación|Revisión manual|Aprobada|Rechazada)?$", message = "Selecciona un estado válido.")
    private String estado;
    @Size(max = 250, message = "El motivo no puede superar 250 caracteres.")
    private String motivo;
    @DecimalMin(value = "0.0", message = "Los ingresos no pueden ser negativos.")
    private BigDecimal ingresosMensuales;
    @DecimalMin(value = "0.0", message = "Los gastos no pueden ser negativos.")
    private BigDecimal gastosMensuales;
    @DecimalMin(value = "0.0", message = "Las deudas no pueden ser negativas.")
    private BigDecimal deudasMensuales;
    @Size(max = 1000, message = "Las observaciones no pueden superar 1000 caracteres.")
    private String observaciones;

    public Solicitud() { }

    public Long getIdSolicitud() { return idSolicitud; }
    public void setIdSolicitud(Long idSolicitud) { this.idSolicitud = idSolicitud; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    /** Etiqueta legible para seleccionar la solicitud desde formularios MVC. */
    @Transient
    public String getEtiquetaParaEvaluacion() {
        String clienteNombre = cliente == null || cliente.getNombre() == null ? "Cliente sin nombre" : cliente.getNombre();
        String solicitudCodigo = codigo == null || codigo.isBlank() ? "Solicitud " + idSolicitud : codigo;
        String importe = monto == null ? "por definir" : monto.toPlainString();
        return solicitudCodigo + " · " + clienteNombre + " · S/ " + importe;
    }
    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; this.idCliente = cliente == null ? null : cliente.getIdCliente(); }
    public String getTipoCredito() { return tipoCredito; }
    public void setTipoCredito(String tipoCredito) { this.tipoCredito = tipoCredito; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public Integer getPlazoMeses() { return plazoMeses; }
    public void setPlazoMeses(Integer plazoMeses) { this.plazoMeses = plazoMeses; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public BigDecimal getIngresosMensuales() { return ingresosMensuales; }
    public void setIngresosMensuales(BigDecimal ingresosMensuales) { this.ingresosMensuales = ingresosMensuales; }
    public BigDecimal getGastosMensuales() { return gastosMensuales; }
    public void setGastosMensuales(BigDecimal gastosMensuales) { this.gastosMensuales = gastosMensuales; }
    public BigDecimal getDeudasMensuales() { return deudasMensuales; }
    public void setDeudasMensuales(BigDecimal deudasMensuales) { this.deudasMensuales = deudasMensuales; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}

