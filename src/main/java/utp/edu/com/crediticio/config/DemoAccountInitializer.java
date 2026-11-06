package utp.edu.com.crediticio.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import utp.edu.com.crediticio.repository.cliente.ClienteRepository;
import utp.edu.com.crediticio.repository.usuario.UsuarioRepository;
import utp.edu.com.crediticio.service.PasswordHasher;

/** Habilita dos accesos de muestra únicamente si las cuentas aún no tienen contraseña. */
@Component
public class DemoAccountInitializer implements ApplicationRunner {
    private final ClienteRepository clientes; private final UsuarioRepository usuarios; private final PasswordHasher hasher;
    public DemoAccountInitializer(ClienteRepository c, UsuarioRepository u, PasswordHasher h) { clientes=c; usuarios=u; hasher=h; }
    @Override @Transactional public void run(ApplicationArguments args) {
        usuarios.findFirstByCorreoIgnoreCase("andrea.salazar@banco.com").ifPresent(u -> {
            if (u.getPasswordHash()==null || u.getPasswordHash().isBlank()) { u.setPasswordHash(hasher.codificar("Banco123!")); usuarios.save(u); }
        });
        clientes.findFirstByDni("70000001").ifPresent(c -> {
            if (c.getPasswordHash()==null || c.getPasswordHash().isBlank()) { c.setPasswordHash(hasher.codificar("Cliente123!")); clientes.save(c); }
        });
    }
}
