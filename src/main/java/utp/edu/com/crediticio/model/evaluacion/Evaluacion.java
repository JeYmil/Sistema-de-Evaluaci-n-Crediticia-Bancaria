package utp.edu.com.crediticio.model.evaluacion;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import utp.edu.com.crediticio.model.solicitud.Solicitud;

/** Representa el resultado de evaluar una solicitud crediticia. */
@Entity
@Table(name = "evaluaciones")
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvaluacion;
    private String codigo;
    @ManyToOne
    @JoinColumn(name = "solicitud_id", nullable = false)
    private Solicitud solicitud;
    @Transient
    private Long idSolicitud;
    @Min(value = 0, message = "El puntaje mínimo es 0.")
    @Max(value = 850, message = "El puntaje máximo es 850.")
    private Integer puntaje;
    @Pattern(regexp = "^(Por evaluar|Bajo|Medio|Alto)?$", message = "Selecciona un nivel de riesgo válido.")
    private String nivelRiesgo;
    @Pattern(regexp = "^(Pendiente|Aprobado|Rechazado|Revisión manual)?$", message = "Selecciona un resultado válido.")
    private String resultado;
    @Size(max = 100, message = "La política no puede superar 100 caracteres.")
    private String politica;
    @DecimalMin(value = "0.0", message = "El ingreso disponible no puede ser negativo.")
    private BigDecimal ingresoDisponible;
    @DecimalMin(value = "0.0", message = "El DTI no puede ser negativo.")
    @Max(value = 100, message = "El DTI no puede superar 100 %.")
    private BigDecimal dti;
    @DecimalMin(value = "0.0", message = "La capacidad de cuota no puede ser negativa.")
    private BigDecimal capacidadNuevaCuota;
    @DecimalMin(value = "0.0", message = "El monto máximo no puede ser negativo.")
    private BigDecimal montoMaximoEstimado;
    @Size(max = 1000, message = "El comentario no puede superar 1000 caracteres.")
    private String comentario;

    public Evaluacion() { }

    public Long getIdEvaluacion() { return idEvaluacion; }
    public void setIdEvaluacion(Long idEvaluacion) { this.idEvaluacion = idEvaluacion; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public Long getIdSolicitud() { return idSolicitud; }
    public void setIdSolicitud(Long idSolicitud) { this.idSolicitud = idSolicitud; }
    public Solicitud getSolicitud() { return solicitud; }
    public void setSolicitud(Solicitud solicitud) { this.solicitud = solicitud; this.idSolicitud = solicitud == null ? null : solicitud.getIdSolicitud(); }
    public Integer getPuntaje() { return puntaje; }
    public void setPuntaje(Integer puntaje) { this.puntaje = puntaje; }
    public String getNivelRiesgo() { return nivelRiesgo; }
    public void setNivelRiesgo(String nivelRiesgo) { this.nivelRiesgo = nivelRiesgo; }
    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }
    public String getPolitica() { return politica; }
    public void setPolitica(String politica) { this.politica = politica; }
    public BigDecimal getIngresoDisponible() { return ingresoDisponible; }
    public void setIngresoDisponible(BigDecimal ingresoDisponible) { this.ingresoDisponible = ingresoDisponible; }
    public BigDecimal getDti() { return dti; }
    public void setDti(BigDecimal dti) { this.dti = dti; }
    public BigDecimal getCapacidadNuevaCuota() { return capacidadNuevaCuota; }
    public void setCapacidadNuevaCuota(BigDecimal capacidadNuevaCuota) { this.capacidadNuevaCuota = capacidadNuevaCuota; }
    public BigDecimal getMontoMaximoEstimado() { return montoMaximoEstimado; }
    public void setMontoMaximoEstimado(BigDecimal montoMaximoEstimado) { this.montoMaximoEstimado = montoMaximoEstimado; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
}

