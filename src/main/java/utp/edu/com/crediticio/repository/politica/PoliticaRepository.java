package utp.edu.com.crediticio.repository.politica;

import utp.edu.com.crediticio.model.politica.Politica;

import org.springframework.data.jpa.repository.JpaRepository;

/** Consultas y operaciones CRUD de políticas. */
public interface PoliticaRepository extends JpaRepository<Politica, Long> {
    boolean existsByCodigo(String codigo);
    java.util.Optional<Politica> findFirstByProductoIgnoreCaseAndEstadoIgnoreCase(String producto, String estado);
}



