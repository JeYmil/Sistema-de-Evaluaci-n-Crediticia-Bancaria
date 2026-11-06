package utp.edu.com.crediticio.model.cliente;

import java.time.LocalDate;
import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/** Representa un cliente y sus datos financieros básicos. */
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String codigo;
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 2, max = 120, message = "El nombre debe tener entre 2 y 120 caracteres.")
    private String nombre;
    @NotBlank(message = "El DNI es obligatorio.")
    @Pattern(regexp = "[0-9]{8}", message = "El DNI debe tener exactamente 8 dígitos.")
    private String dni;
    @JsonIgnore
    private String passwordHash;
    private LocalDate fechaNacimiento;
    @Pattern(regexp = "^$|[0-9]{9}", message = "El teléfono debe tener 9 dígitos.")
    private String telefono;
    @Email(message = "Ingresa un correo electrónico válido.")
    @Size(max = 150, message = "El correo no puede superar 150 caracteres.")
    private String correo;
    @Size(max = 180, message = "La dirección no puede superar 180 caracteres.")
    private String direccion;
    private String estado;
    private String empresa;
    private String cargo;
    @Min(value = 0, message = "La antigüedad no puede ser negativa.")
    private Integer antiguedadMeses;
    @DecimalMin(value = "0.0", message = "Los ingresos no pueden ser negativos.")
    private BigDecimal ingresosMensuales;
    @DecimalMin(value = "0.0", message = "Los gastos no pueden ser negativos.")
    private BigDecimal gastosMensuales;
    @DecimalMin(value = "0.0", message = "Las deudas no pueden ser negativas.")
    private BigDecimal deudasMensuales;

    public Cliente() { }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public Integer getAntiguedadMeses() { return antiguedadMeses; }
    public void setAntiguedadMeses(Integer antiguedadMeses) { this.antiguedadMeses = antiguedadMeses; }
    public BigDecimal getIngresosMensuales() { return ingresosMensuales; }
    public void setIngresosMensuales(BigDecimal ingresosMensuales) { this.ingresosMensuales = ingresosMensuales; }
    public BigDecimal getGastosMensuales() { return gastosMensuales; }
    public void setGastosMensuales(BigDecimal gastosMensuales) { this.gastosMensuales = gastosMensuales; }
    public BigDecimal getDeudasMensuales() { return deudasMensuales; }
    public void setDeudasMensuales(BigDecimal deudasMensuales) { this.deudasMensuales = deudasMensuales; }
}

