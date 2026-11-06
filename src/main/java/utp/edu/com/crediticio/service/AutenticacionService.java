package utp.edu.com.crediticio.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.com.crediticio.model.cliente.Cliente;
import utp.edu.com.crediticio.model.cliente.RegistroClienteForm;
import utp.edu.com.crediticio.model.usuario.Usuario;
import utp.edu.com.crediticio.repository.cliente.ClienteRepository;
import utp.edu.com.crediticio.repository.usuario.UsuarioRepository;

@Service
public class AutenticacionService {
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordHasher passwordHasher;
    public AutenticacionService(ClienteRepository cr, UsuarioRepository ur, PasswordHasher ph) {
        clienteRepository=cr; usuarioRepository=ur; passwordHasher=ph;
    }
    public Cuenta autenticar(String usuario, String clave) {
        if (usuario == null || clave == null) return null;
        var persona = usuarioRepository.findFirstByCorreoIgnoreCase(usuario.trim());
        if (persona.isPresent() && "Activo".equalsIgnoreCase(persona.get().getEstado())
                && passwordHasher.verificar(clave, persona.get().getPasswordHash())) {
            Usuario u=persona.get(); return new Cuenta("PERSONAL", u.getIdUsuario(), u.getNombreCompleto());
        }
        var cliente = clienteRepository.findFirstByDni(usuario.trim());
        if (cliente.isPresent() && "Activo".equalsIgnoreCase(cliente.get().getEstado())
                && passwordHasher.verificar(clave, cliente.get().getPasswordHash())) {
            Cliente c=cliente.get(); return new Cuenta("CLIENTE", c.getIdCliente(), c.getNombre());
        }
        return null;
    }
    @Transactional
    public void registrarCliente(RegistroClienteForm form) {
        String dni=form.getDni().trim();
        Cliente cliente=clienteRepository.findFirstByDni(dni).orElse(null);
        if (cliente != null && cliente.getCorreo()!=null && !cliente.getCorreo().equalsIgnoreCase(form.getCorreo().trim())) {
            throw new IllegalArgumentException("El correo no coincide con el cliente registrado para ese DNI.");
        }
        if (cliente == null) {
            cliente=new Cliente(); cliente.setCodigo("CL-%03d".formatted(clienteRepository.count()+1));
            cliente.setNombre(form.getNombre().trim()); cliente.setDni(dni); cliente.setCorreo(form.getCorreo().trim().toLowerCase()); cliente.setEstado("Activo");
        } else if (cliente.getPasswordHash()!=null && !cliente.getPasswordHash().isBlank()) {
            throw new IllegalArgumentException("Ese cliente ya tiene una cuenta. Inicia sesión.");
        }
        if (cliente.getCorreo()==null || cliente.getCorreo().isBlank()) cliente.setCorreo(form.getCorreo().trim().toLowerCase());
        cliente.setPasswordHash(passwordHasher.codificar(form.getClave()));
        clienteRepository.save(cliente);
    }
    public record Cuenta(String tipo, Long id, String nombre) { }
}
