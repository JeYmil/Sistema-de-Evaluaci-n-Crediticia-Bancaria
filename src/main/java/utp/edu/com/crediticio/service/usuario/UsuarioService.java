package utp.edu.com.crediticio.service.usuario;

import utp.edu.com.crediticio.model.usuario.Usuario;
import utp.edu.com.crediticio.repository.usuario.UsuarioRepository;

import java.util.List;
import org.springframework.stereotype.Service;
import utp.edu.com.crediticio.service.PasswordHasher;

/** Reglas de gestión de las cuentas internas del banco. */
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordHasher passwordHasher;
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordHasher passwordHasher) { this.usuarioRepository = usuarioRepository; this.passwordHasher=passwordHasher; }

    public List<Usuario> listar() { return usuarioRepository.findAll(); }
    public Usuario buscar(Long id) { return usuarioRepository.findById(id).orElse(null); }
    public Usuario guardar(Usuario usuario) {
        Usuario anterior = usuario.getIdUsuario() == null ? null : usuarioRepository.findById(usuario.getIdUsuario()).orElse(null);
        if (usuario.getClaveNueva() != null && !usuario.getClaveNueva().isBlank()) {
            if (usuario.getClaveNueva().length() < 8) throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
            usuario.setPasswordHash(passwordHasher.codificar(usuario.getClaveNueva()));
        } else if (anterior != null) {
            usuario.setPasswordHash(anterior.getPasswordHash());
        } else {
            throw new IllegalArgumentException("Define una contraseña inicial de al menos 8 caracteres.");
        }
        if (usuario.getCorreo() == null || usuario.getCorreo().isBlank()) {
            throw new IllegalArgumentException("El correo institucional es obligatorio.");
        }
        usuario.setCorreo(usuario.getCorreo().trim().toLowerCase());
        var existente = usuarioRepository.findFirstByCorreoIgnoreCase(usuario.getCorreo());
        if (existente.isPresent() && !existente.get().getIdUsuario().equals(usuario.getIdUsuario())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo.");
        }
        if (usuario.getRol() == null || usuario.getRol().isBlank()) usuario.setRol("Analista");
        if (usuario.getEstado() == null || usuario.getEstado().isBlank()) usuario.setEstado("Activo");
        usuario.setClaveNueva(null);
        return usuarioRepository.save(usuario);
    }
    public void eliminar(Long id) { usuarioRepository.deleteById(id); }
}


