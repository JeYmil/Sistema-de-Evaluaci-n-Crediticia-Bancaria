package utp.edu.com.crediticio.service.politica;

import utp.edu.com.crediticio.model.politica.Politica;
import utp.edu.com.crediticio.repository.politica.PoliticaRepository;

import java.util.List;
import org.springframework.stereotype.Service;

/** Reglas y operaciones de negocio sencillas para políticas. */
@Service
public class PoliticaService {
    private final PoliticaRepository politicaRepository;

    public PoliticaService(PoliticaRepository politicaRepository) {
        this.politicaRepository = politicaRepository;
    }

    public List<Politica> listar() { return politicaRepository.findAll(); }
    public Politica buscar(Long id) { return politicaRepository.findById(id).orElse(null); }

    public Politica guardar(Politica politica) {
        if (politica.getPuntajeMinimoAprobar() == null) politica.setPuntajeMinimoAprobar(650);
        if (politica.getPuntajeMaximoRechazo() == null) politica.setPuntajeMaximoRechazo(499);
        if (politica.getDtiMaximoAprobacion() == null) politica.setDtiMaximoAprobacion(new java.math.BigDecimal("35.0"));
        if (politica.getDtiMinimoRechazo() == null) politica.setDtiMinimoRechazo(new java.math.BigDecimal("50.0"));
        if (politica.getPuntajeMaximoRechazo() >= politica.getPuntajeMinimoAprobar()) {
            throw new IllegalArgumentException("El puntaje de rechazo debe ser menor al puntaje mínimo de aprobación.");
        }
        if (politica.getDtiMaximoAprobacion().compareTo(politica.getDtiMinimoRechazo()) >= 0) {
            throw new IllegalArgumentException("El DTI de aprobación debe ser menor al DTI de rechazo.");
        }
        if (politica.getId() == null && (politica.getCodigo() == null || politica.getCodigo().isBlank())) {
            long consecutivo = politicaRepository.count() + 1;
            String codigo;
            do {
                codigo = "POL-%03d".formatted(consecutivo++);
            } while (politicaRepository.existsByCodigo(codigo));
            politica.setCodigo(codigo);
        }
        if (politica.getEstado() == null || politica.getEstado().isBlank()) politica.setEstado("Borrador");
        return politicaRepository.save(politica);
    }

    public void eliminar(Long id) { politicaRepository.deleteById(id); }
}


