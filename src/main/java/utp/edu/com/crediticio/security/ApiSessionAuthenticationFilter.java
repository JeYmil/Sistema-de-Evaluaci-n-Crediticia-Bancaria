package utp.edu.com.crediticio.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

/** Mantiene operativa la interfaz web existente cuando sus páginas consultan el API con sesión. */
public class ApiSessionAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        var actual = SecurityContextHolder.getContext().getAuthentication();
        boolean usaBearer = request.getHeader("Authorization") != null
                && request.getHeader("Authorization").startsWith("Bearer ");

        // Las llamadas REST externas deben usar JWT para modificar información.
        // La sesión existente se conserva para las consultas GET de las páginas Thymeleaf.
        if (actual == null && !usaBearer && "GET".equalsIgnoreCase(request.getMethod())) {
            var session = request.getSession(false);
            if (session != null) {
                Object tipo = session.getAttribute("tipoCuenta");
                Object id = session.getAttribute("cuentaId");
                if (tipo != null && id instanceof Number numero) {
                    String tipoTexto = tipo.toString();
                    String rol = "CLIENTE".equals(tipoTexto) ? "ROLE_CLIENTE" : "ROLE_PERSONAL";
                    var principal = new ApiUserPrincipal(numero.longValue(), tipoTexto);
                    var autenticacion = new UsernamePasswordAuthenticationToken(
                            principal, null, List.of(new SimpleGrantedAuthority(rol)));
                    SecurityContextHolder.getContext().setAuthentication(autenticacion);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
