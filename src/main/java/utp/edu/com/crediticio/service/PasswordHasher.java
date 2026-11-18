package utp.edu.com.crediticio.service;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.springframework.stereotype.Component;

/** Guarda claves derivadas con PBKDF2; nunca conserva contraseñas en texto abierto. */
@Component
public class PasswordHasher {
    private static final int ITERACIONES = 120_000;
    private static final int BITS = 256;
    private final SecureRandom random = new SecureRandom();

    public String codificar(String clave) {
        byte[] salt = new byte[16]; random.nextBytes(salt);
        byte[] hash = derivar(clave.toCharArray(), salt, ITERACIONES);
        return ITERACIONES + ":" + Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
    }
    public boolean verificar(String clave, String almacenada) {
        if (clave == null || almacenada == null) return false;
        try {
            String[] partes = almacenada.split(":");
            int vueltas = Integer.parseInt(partes[0]);
            byte[] salt = Base64.getDecoder().decode(partes[1]);
            byte[] esperado = Base64.getDecoder().decode(partes[2]);
            return MessageDigest.isEqual(esperado, derivar(clave.toCharArray(), salt, vueltas));
        } catch (RuntimeException ex) { return false; }
    }
    private static byte[] derivar(char[] clave, byte[] salt, int vueltas) {
        try {
            PBEKeySpec spec = new PBEKeySpec(clave, salt, vueltas, BITS);
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
            spec.clearPassword(); return result;
        } catch (Exception ex) { throw new IllegalStateException("No se pudo proteger la contraseña.", ex); }
    }
}
