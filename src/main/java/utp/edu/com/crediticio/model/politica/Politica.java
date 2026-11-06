package utp.edu.com.crediticio.model.politica;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;

/** Entidad JPA de una política crediticia. */
@Entity
@Table(name = "politicas")
public class Politica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String codigo;
    @NotBlank(message = "Selecciona un producto crediticio.")
    @Pattern(regexp = "^(Crédito personal|Crédito vehicular|Crédito hipotecario|Crédito para emprendimiento|Crédito educativo)$", message = "Selecciona un producto válido.")
    private String producto;
    @Column(name = "version_politica")
    @NotBlank(message = "La versión es obligatoria.")
    @Size(max = 12, message = "La versión no puede superar 12 caracteres.")
    private String version;
    @NotBlank(message = "La vigencia es obligatoria.")
    @Size(max = 30, message = "La vigencia no puede superar 30 caracteres.")
    private String vigencia;
    @Pattern(regexp = "^(Activa|Borrador|Inactiva)?$", message = "Selecciona un estado válido.")
    private String estado;
    @NotBlank(message = "La descripción es obligatoria.")
    @Size(max = 400, message = "La descripción no puede superar 400 caracteres.")
    private String descripcion;
    @Min(value = 300, message = "El puntaje mínimo para aprobar debe ser al menos 300.")
    private Integer puntajeMinimoAprobar = 650;
    @Min(value = 0, message = "El puntaje máximo para rechazar no puede ser negativo.")
    private Integer puntajeMaximoRechazo = 499;
    @DecimalMin(value = "1.0", message = "El DTI de aprobación debe ser mayor a 0.")
    @DecimalMax(value = "100.0", message = "El DTI no puede superar 100 %.")
    private BigDecimal dtiMaximoAprobacion = new BigDecimal("35.0");
    @DecimalMin(value = "1.0", message = "El DTI de rechazo debe ser mayor a 0.")
    @DecimalMax(value = "100.0", message = "El DTI no puede superar 100 %.")
    private BigDecimal dtiMinimoRechazo = new BigDecimal("50.0");

    public Politica() { }

    public Politica(String codigo, String producto, String version, String vigencia,
                    String estado, String descripcion) {
        this.codigo = codigo;
        this.producto = producto;
        this.version = version;
        this.vigencia = vigencia;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public String getVigencia() { return vigencia; }
    public void setVigencia(String vigencia) { this.vigencia = vigencia; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Integer getPuntajeMinimoAprobar() { return puntajeMinimoAprobar; }
    public void setPuntajeMinimoAprobar(Integer valor) { this.puntajeMinimoAprobar = valor; }
    public Integer getPuntajeMaximoRechazo() { return puntajeMaximoRechazo; }
    public void setPuntajeMaximoRechazo(Integer valor) { this.puntajeMaximoRechazo = valor; }
    public BigDecimal getDtiMaximoAprobacion() { return dtiMaximoAprobacion; }
    public void setDtiMaximoAprobacion(BigDecimal valor) { this.dtiMaximoAprobacion = valor; }
    public BigDecimal getDtiMinimoRechazo() { return dtiMinimoRechazo; }
    public void setDtiMinimoRechazo(BigDecimal valor) { this.dtiMinimoRechazo = valor; }
}

