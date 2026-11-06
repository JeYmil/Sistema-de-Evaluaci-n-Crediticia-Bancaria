package utp.edu.com.crediticio.service.solicitud;

import utp.edu.com.crediticio.model.solicitud.Solicitud;
import utp.edu.com.crediticio.model.cliente.Cliente;
import utp.edu.com.crediticio.repository.solicitud.SolicitudRepository;
import utp.edu.com.crediticio.repository.cliente.ClienteRepository;
import utp.edu.com.crediticio.service.evaluacion.EvaluacionAutomaticaService;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Reglas y operaciones de negocio sencillas para solicitudes. */
@Service
public class SolicitudService {
    private final SolicitudRepository solicitudRepository;
    private final ClienteRepository clienteRepository;
    private final EvaluacionAutomaticaService evaluacionAutomaticaService;

    public SolicitudService(SolicitudRepository solicitudRepository, ClienteRepository clienteRepository,
            EvaluacionAutomaticaService evaluacionAutomaticaService) {
        this.solicitudRepository = solicitudRepository;
        this.clienteRepository = clienteRepository;
        this.evaluacionAutomaticaService = evaluacionAutomaticaService;
    }

    public List<Solicitud> listar() { return solicitudRepository.findAll(); }
    public List<Solicitud> listarConCliente() { return solicitudRepository.findByClienteIsNotNull(); }
    public List<Solicitud> listarPorCliente(Long idCliente) { return solicitudRepository.findByCliente_IdCliente(idCliente); }
    public Solicitud buscar(Long id) { return solicitudRepository.findById(id).orElse(null); }

    @Transactional
    public Solicitud guardar(Solicitud solicitud) {
        boolean nueva = solicitud.getIdSolicitud() == null;
        Solicitud existente = nueva ? null : solicitudRepository.findById(solicitud.getIdSolicitud()).orElse(null);
        if (!nueva && existente == null) throw new IllegalArgumentException("No se encontró la solicitud para actualizar.");
        if (solicitud.getIdSolicitud() != null && (solicitud.getCodigo() == null || solicitud.getCodigo().isBlank())) {
            if (existente != null) solicitud.setCodigo(existente.getCodigo());
        }
        if (solicitud.getIdCliente() == null || solicitud.getIdCliente() <= 0) {
            throw new IllegalArgumentException("Seleccione un cliente válido.");
        }
        Cliente cliente = clienteRepository.findById(solicitud.getIdCliente())
                .orElseThrow(() -> new IllegalArgumentException("El cliente seleccionado no existe."));
        solicitud.setCliente(cliente);
        if (solicitud.getIngresosMensuales() == null) solicitud.setIngresosMensuales(cliente.getIngresosMensuales());
        if (solicitud.getGastosMensuales() == null) solicitud.setGastosMensuales(cliente.getGastosMensuales());
        if (solicitud.getDeudasMensuales() == null) solicitud.setDeudasMensuales(cliente.getDeudasMensuales());
        if (solicitud.getCodigo() == null || solicitud.getCodigo().isBlank()) {
            solicitud.setCodigo("SOL-%03d".formatted(solicitudRepository.count() + 1));
        }
        if (solicitud.getFecha() == null || solicitud.getFecha().isBlank()) {
            solicitud.setFecha(LocalDate.now().toString());
        }
        solicitud.setEstado(nueva ? "Pendiente" : existente.getEstado());
        Solicitud guardada = solicitudRepository.saveAndFlush(solicitud);
        if (nueva) evaluacionAutomaticaService.evaluarNuevaSolicitud(guardada);
        return guardada;
    }

    public void eliminar(Long id) { solicitudRepository.deleteById(id); }
}


