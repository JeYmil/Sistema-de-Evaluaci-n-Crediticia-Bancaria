package utp.edu.com.crediticio.repository.usuario;

import utp.edu.com.crediticio.model.usuario.Usuario;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findFirstByCorreoIgnoreCase(String correo);
}



