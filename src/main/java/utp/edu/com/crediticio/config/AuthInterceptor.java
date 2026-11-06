package utp.edu.com.crediticio.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String path=request.getRequestURI();
        if (path.equals("/") || path.equals("/login") || path.equals("/logout") || path.equals("/registro-cliente") || path.startsWith("/api/v1/auth/") || path.startsWith("/css/")
                || path.startsWith("/js/") || path.startsWith("/img/") || path.equals("/favicon.ico") || path.startsWith("/error")) return true;
        Object role=request.getSession(false)==null ? null : request.getSession(false).getAttribute("tipoCuenta");
        if (role==null) {
            if (path.startsWith("/api/")) { response.setStatus(401); response.setContentType("application/json"); response.getWriter().write("{\"error\":\"Debe iniciar sesión.\"}"); }
            else response.sendRedirect("/login");
            return false;
        }
        boolean portal=path.startsWith("/portal-cliente");
        if (portal && !"CLIENTE".equals(role) || !portal && "CLIENTE".equals(role) && !path.startsWith("/api/v1/clientes/mi-cuenta")) {
            if (path.startsWith("/api/")) { response.setStatus(403); response.setContentType("application/json"); response.getWriter().write("{\"error\":\"No tiene permiso para esta operación.\"}"); }
            else response.sendRedirect("CLIENTE".equals(role) ? "/portal-cliente" : "/dashboard");
            return false;
        }
        return true;
    }
}
