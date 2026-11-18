# Guía rápida: cómo recorrer el sistema

## Una página normal

1. El navegador abre una ruta, por ejemplo `GET /solicitudes`.
2. `SolicitudesController` pide los registros a `SolicitudService`.
3. El servicio utiliza `SolicitudRepository`, que es la interfaz de Spring Data JPA.
4. JPA obtiene las filas de PostgreSQL.
5. El controlador entrega la lista a `solicitudes/solicitudes.html` y Thymeleaf la muestra.

## El API

Los controladores web están bajo `controller/web` y devuelven vistas Thymeleaf. Las rutas REST están bajo `controller/api` en clases `@RestController`; por ejemplo, `GET /api/v1/solicitudes` devuelve JSON. La pantalla `/solicitudes` carga su marco HTML con Thymeleaf y luego `solicitudes-api.js` llama al endpoint JSON para llenar la tabla y aplicar filtros. Ambos recorridos usan los mismos servicios y repositorios. `ApiErrorHandler` convierte errores de controladores en JSON. `ApiSecurityErrorHandler` responde errores de token y permisos en JSON; ninguno es endpoint.

## Una solicitud nueva

```text
Formulario del cliente o del banco
        → SolicitudesController / API REST
        → SolicitudService
        → SolicitudRepository guarda la solicitud
        → EvaluacionAutomaticaService aplica la política
        → se guardan la evaluación y el estado
        → si hace falta, el cliente adjunta comprobantes
        → el analista consulta casos manuales en Evaluaciones
```

Las acciones de la tabla **Solicitudes** solo abren el detalle. Allí se muestra el resultado automático. Los casos que requieren criterio del personal quedan con estado **Revisión manual** y su detalle permite aprobar o rechazar, guardando un comentario.

## Para qué sirve cada grupo

- `controller`: conecta la petición HTTP con la aplicación y elige una vista o respuesta JSON.
- `controller/api`: publica rutas REST y respuestas para otros clientes.
- `model`: define las entidades de PostgreSQL y objetos de formulario.
- `service`: concentra las reglas y coordina el trabajo entre repositorios.
- `repository`: interfaces Spring Data que consultan y guardan registros.
- `templates`: vistas Thymeleaf; no contienen persistencia ni reglas de evaluación.
- `config`: configuración de Spring Security y JWT.
- `security`: identidad de sesión usada para mantener las lecturas GET de la interfaz.

## Decirlo en la exposición

“La aplicación es una sola aplicación Spring Boot organizada por capas. En la parte web usamos MVC con Thymeleaf y sesión. El API REST devuelve JSON y usa JWT Bearer: el login entrega el token y Spring Security valida el token y el rol en las demás rutas. Ambos recorridos reutilizan los servicios y repositorios para acceder a PostgreSQL.”

MVC describe el intercambio entre controlador, modelo y vista; capas explica cómo se separan las responsabilidades internas. La app sigue siendo monolítica porque se ejecuta como una sola aplicación.



