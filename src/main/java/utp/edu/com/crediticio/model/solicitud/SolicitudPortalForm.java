package utp.edu.com.crediticio.model.solicitud;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

/** Datos recibidos desde el formulario público de solicitud. */
public class SolicitudPortalForm {
    @NotBlank(message = "Selecciona el tipo de crédito.")
    @Pattern(regexp = "^(Personal|Vehicular|Emprendimiento|Educativo|Hipotecario)$", message = "Selecciona un tipo de crédito válido.")
    private String tipoCredito;

    @NotNull(message = "Ingresa el monto solicitado.")
    @DecimalMin(value = "500.00", message = "El monto mínimo es S/ 500.")
    @DecimalMax(value = "200000.00", message = "El monto máximo es S/ 200,000.")
    @Digits(integer = 6, fraction = 2, message = "El monto debe tener hasta 6 enteros y 2 decimales.")
    private BigDecimal montoSolicitado;

    @NotNull(message = "Selecciona el plazo.")
    @Min(value = 6, message = "El plazo mínimo es 6 meses.")
    @Max(value = 120, message = "El plazo máximo es 120 meses.")
    private Integer plazo;

    @NotBlank(message = "Describe el motivo de la solicitud.")
    @Size(max = 250, message = "El motivo no puede superar 250 caracteres.")
    private String motivoCredito;
    @DecimalMin(value = "0.0", message = "Los ingresos no pueden ser negativos.")
    @DecimalMax(value = "1000000.0", message = "Revisa el monto de ingresos.")
    private BigDecimal ingresosMensuales;
    @DecimalMin(value = "0.0", message = "Los gastos no pueden ser negativos.")
    private BigDecimal gastosMensuales;
    @DecimalMin(value = "0.0", message = "Las deudas no pueden ser negativas.")
    private BigDecimal deudasMensuales;

    public String getTipoCredito() { return tipoCredito; }
    public void setTipoCredito(String tipoCredito) { this.tipoCredito = tipoCredito; }
    public BigDecimal getMontoSolicitado() { return montoSolicitado; }
    public void setMontoSolicitado(BigDecimal montoSolicitado) { this.montoSolicitado = montoSolicitado; }
    public Integer getPlazo() { return plazo; }
    public void setPlazo(Integer plazo) { this.plazo = plazo; }
    public String getMotivoCredito() { return motivoCredito; }
    public void setMotivoCredito(String motivoCredito) { this.motivoCredito = motivoCredito; }
    public BigDecimal getIngresosMensuales() { return ingresosMensuales; }
    public void setIngresosMensuales(BigDecimal valor) { ingresosMensuales = valor; }
    public BigDecimal getGastosMensuales() { return gastosMensuales; }
    public void setGastosMensuales(BigDecimal valor) { gastosMensuales = valor; }
    public BigDecimal getDeudasMensuales() { return deudasMensuales; }
    public void setDeudasMensuales(BigDecimal valor) { deudasMensuales = valor; }
}
