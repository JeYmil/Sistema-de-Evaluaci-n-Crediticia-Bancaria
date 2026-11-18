package utp.edu.com.crediticio.model.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RegistroClienteForm {
    @NotBlank @Size(min=2, max=120) private String nombre;
    @NotBlank @Pattern(regexp="[0-9]{8}", message="El DNI debe tener 8 dígitos.") private String dni;
    @NotBlank @Email @Size(max=150, message="El correo no puede superar 150 caracteres.") private String correo;
    @NotNull(message="Ingresa la fecha de nacimiento.") private LocalDate fechaNacimiento;
    @NotBlank @Pattern(regexp="[0-9]{9}", message="El teléfono debe tener 9 dígitos.") private String telefono;
    @NotBlank @Size(max=180, message="La dirección no puede superar 180 caracteres.") private String direccion;
    @NotBlank @Size(max=120) private String empresa;
    @NotBlank @Size(max=100) private String cargo;
    @NotNull(message="Ingresa la antigüedad laboral en meses.") @Min(value=0, message="La antigüedad no puede ser negativa.") private Integer antiguedadMeses;
    @NotNull(message="Ingresa los ingresos mensuales.") @DecimalMin(value="0.0", message="Los ingresos no pueden ser negativos.") private BigDecimal ingresosMensuales;
    @NotNull(message="Ingresa los gastos mensuales.") @DecimalMin(value="0.0", message="Los gastos no pueden ser negativos.") private BigDecimal gastosMensuales;
    @NotNull(message="Ingresa las deudas mensuales; usa 0 si no tienes.") @DecimalMin(value="0.0", message="Las deudas no pueden ser negativas.") private BigDecimal deudasMensuales;
    @NotBlank @Size(min=8, max=72, message="La contraseña debe tener al menos 8 caracteres.") private String clave;
    public String getNombre() { return nombre; } public void setNombre(String v) { nombre=v; }
    public String getDni() { return dni; } public void setDni(String v) { dni=v; }
    public String getCorreo() { return correo; } public void setCorreo(String v) { correo=v; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; } public void setFechaNacimiento(LocalDate v) { fechaNacimiento=v; }
    public String getTelefono() { return telefono; } public void setTelefono(String v) { telefono=v; }
    public String getDireccion() { return direccion; } public void setDireccion(String v) { direccion=v; }
    public String getEmpresa() { return empresa; } public void setEmpresa(String v) { empresa=v; }
    public String getCargo() { return cargo; } public void setCargo(String v) { cargo=v; }
    public Integer getAntiguedadMeses() { return antiguedadMeses; } public void setAntiguedadMeses(Integer v) { antiguedadMeses=v; }
    public BigDecimal getIngresosMensuales() { return ingresosMensuales; } public void setIngresosMensuales(BigDecimal v) { ingresosMensuales=v; }
    public BigDecimal getGastosMensuales() { return gastosMensuales; } public void setGastosMensuales(BigDecimal v) { gastosMensuales=v; }
    public BigDecimal getDeudasMensuales() { return deudasMensuales; } public void setDeudasMensuales(BigDecimal v) { deudasMensuales=v; }
    public String getClave() { return clave; } public void setClave(String v) { clave=v; }
}
