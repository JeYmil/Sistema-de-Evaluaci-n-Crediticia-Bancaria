package utp.edu.com.crediticio.service.cliente;

import utp.edu.com.crediticio.model.cliente.Cliente;
import utp.edu.com.crediticio.repository.cliente.ClienteRepository;

import java.util.List;
import org.springframework.stereotype.Service;

/** Reglas y operaciones de negocio sencillas para clientes. */
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listar() { return clienteRepository.findAll(); }
    public Cliente buscar(Long id) { return clienteRepository.findById(id).orElse(null); }
    public Cliente buscarPorDni(String dni) { return clienteRepository.findFirstByDni(dni).orElse(null); }

    public Cliente guardar(Cliente cliente) {
        Cliente clienteConDni = buscarPorDni(cliente.getDni());
        if (clienteConDni != null && !clienteConDni.getIdCliente().equals(cliente.getIdCliente())) {
            throw new IllegalArgumentException("Ya existe un cliente con ese DNI.");
        }
        if (cliente.getIdCliente() == null && (cliente.getCodigo() == null || cliente.getCodigo().isBlank())) {
            cliente.setCodigo("CL-%03d".formatted(clienteRepository.count() + 1));
        } else if (cliente.getIdCliente() != null && (cliente.getCodigo() == null || cliente.getCodigo().isBlank())) {
            Cliente existente = buscar(cliente.getIdCliente());
            if (existente != null) cliente.setCodigo(existente.getCodigo());
        }
        return clienteRepository.save(cliente);
    }

    public void eliminar(Long id) { clienteRepository.deleteById(id); }
}


