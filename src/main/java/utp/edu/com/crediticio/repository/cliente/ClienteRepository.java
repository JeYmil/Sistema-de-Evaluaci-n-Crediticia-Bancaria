package utp.edu.com.crediticio.repository.cliente;

import utp.edu.com.crediticio.model.cliente.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/** Consultas y operaciones CRUD de clientes. */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findFirstByDni(String dni);
}



