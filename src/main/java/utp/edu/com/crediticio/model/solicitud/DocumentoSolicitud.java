package utp.edu.com.crediticio.model.solicitud;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/** Archivo de sustento asociado a una solicitud. Se guarda como bytea en PostgreSQL. */
@Entity
@Table(name = "documentos_solicitud")
public class DocumentoSolicitud {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "solicitud_id", nullable = false)
    @JsonIgnore
    private Solicitud solicitud;
    @Column(nullable = false, length = 180)
    private String nombreArchivo;
    @Column(nullable = false, length = 100)
    private String tipoContenido;
    @Column(nullable = false)
    private long tamanoBytes;
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "bytea") @JsonIgnore
    private byte[] contenido;
    @Column(nullable = false)
    private LocalDateTime fechaSubida = LocalDateTime.now();

    public Long getId() { return id; }
    public Solicitud getSolicitud() { return solicitud; }
    public void setSolicitud(Solicitud solicitud) { this.solicitud = solicitud; }
    public String getNombreArchivo() { return nombreArchivo; }
    public void setNombreArchivo(String nombreArchivo) { this.nombreArchivo = nombreArchivo; }
    public String getTipoContenido() { return tipoContenido; }
    public void setTipoContenido(String tipoContenido) { this.tipoContenido = tipoContenido; }
    public long getTamanoBytes() { return tamanoBytes; }
    public void setTamanoBytes(long tamanoBytes) { this.tamanoBytes = tamanoBytes; }
    public byte[] getContenido() { return contenido; }
    public void setContenido(byte[] contenido) { this.contenido = contenido; }
    public LocalDateTime getFechaSubida() { return fechaSubida; }
}
