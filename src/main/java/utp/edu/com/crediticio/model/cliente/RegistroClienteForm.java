package utp.edu.com.crediticio.model.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegistroClienteForm {
    @NotBlank @Size(min=2, max=120) private String nombre;
    @NotBlank @Pattern(regexp="[0-9]{8}", message="El DNI debe tener 8 dígitos.") private String dni;
    @NotBlank @Email private String correo;
    @NotBlank @Size(min=8, max=72, message="La contraseña debe tener al menos 8 caracteres.") private String clave;
    public String getNombre() { return nombre; } public void setNombre(String v) { nombre=v; }
    public String getDni() { return dni; } public void setDni(String v) { dni=v; }
    public String getCorreo() { return correo; } public void setCorreo(String v) { correo=v; }
    public String getClave() { return clave; } public void setClave(String v) { clave=v; }
}
