# Sistema de Evaluación Crediticia Bancaria — v1.33

Aplicación académica con vistas Thymeleaf para el personal del banco y un portal para clientes. Registra solicitudes en PostgreSQL, aplica reglas de evaluación de ejemplo, conserva documentos de sustento y ofrece rutas REST para integraciones.

## Mejoras visuales de esta versión

- Interfaz empresarial con navegación azul y azul marino, mejor jerarquía visual y espacios más consistentes.
- Tarjetas, tablas, formularios y botones con estilos unificados y adaptación a pantallas pequeñas.
- Los cambios visuales se concentran en `src/main/resources/static/css/style.css`; no cambian las rutas ni el flujo funcional.

## Mejora del acceso y seguridad JWT

- Pantalla de inicio en dos paneles: contexto del sistema a la izquierda y acceso a la derecha.
- Registro de clientes aparece como una opción secundaria explicada para clientes nuevos.
- Se añadieron endpoints JWT para el API. El acceso web Thymeleaf conserva su sesión HTTP actual.

La ruta `POST /api/v1/auth/login` autentica credenciales y devuelve un JWT firmado con HS256. El token vence en 60 minutos. Los endpoints de gestión requieren rol `PERSONAL`; el rol `CLIENTE` puede consultar su propio perfil y sus propias solicitudes. Las páginas Thymeleaf continúan usando la sesión existente. Las lecturas GET usadas por la interfaz aceptan esa sesión; las operaciones REST que modifican datos requieren Bearer JWT.

La clave predeterminada de `application.properties` es solo para demostración local. En otra instalación, define `CREDITICIO_JWT_SECRET` con una clave privada de al menos 32 bytes. JWT está firmado, no cifrado. El endpoint de logout pide al cliente descartar el token; no hay revocación anticipada.

## Tecnologías

- Java 21 y Spring Boot 3.3.
- Spring Web MVC para las páginas y Spring Web REST para el API.
- Thymeleaf, HTML, CSS y Bootstrap para la interfaz.
- Spring Boot Validation para validaciones en el servidor.
- Spring Data JPA e Hibernate para guardar y consultar PostgreSQL.
- Apache POI para generar reportes Excel `.xlsx`.
- PostgreSQL para los datos y los archivos adjuntos.
- Maven Wrapper para compilar y ejecutar.

## Estructura y flujo

```text
src/main/java/utp/edu/com/crediticio/
├── CrediticioBancarioApplication.java  Inicio de Spring Boot
├── config/                             Configuración de JWT, MVC y cuentas de muestra
├── security/                           Sesión usada en lecturas GET del API
├── controller/
│   ├── web/                            @Controller: páginas Thymeleaf y formularios
│   └── api/                            @RestController: rutas REST del API
├── exception/api/                      Errores JSON y excepciones del API
├── model/                              Entidades y formularios
├── repository/                         Interfaces Spring Data JPA para PostgreSQL
└── service/                            Reglas del negocio y operaciones

src/main/resources/
├── templates/                          Vistas HTML procesadas por Thymeleaf
├── static/css/                         Estilos
├── static/img/                         Logotipo
├── application.properties              Puerto y configuración PostgreSQL
└── data.sql                            Datos relacionados de demostración
```

Las páginas web siguen MVC: el controlador recibe una petición, llama al servicio y devuelve una vista. En la pantalla **Solicitudes**, Thymeleaf entrega el marco visual y JavaScript consulta `/api/v1/solicitudes`; las filas llegan en JSON y se dibujan en la tabla. Es un módulo concreto que usa el API dentro de la aplicación, no solo un endpoint para terceros. Otras páginas como clientes, evaluaciones y políticas conservan sus formularios Thymeleaf. Ambos recorridos usan los mismos servicios y repositorios:

Los controladores web están en `controller/web`; las rutas REST están en `controller/api` y usan `@RestController`. Spring Security valida JWT en las APIs. `ApiErrorHandler` y `ApiSecurityErrorHandler` están en `exception/api`: responden errores JSON, pero no son endpoints ni se cuentan como APIs. Las páginas Thymeleaf conservan el control de sesión existente.

```text
Página Thymeleaf o cliente REST
             ↓ petición HTTP
      Controller MVC / REST
             ↓
           Service  ← reglas de negocio
             ↓
        Repository  ← Spring Data JPA
             ↓
         PostgreSQL
```

## API REST

El prefijo versionado es `/api/v1`. El API entrega tokens JWT Bearer. Envía credenciales a `POST /api/v1/auth/login`; usa el `accessToken` devuelto en el encabezado `Authorization: Bearer <token>`. Los cambios REST requieren JWT. Las páginas Thymeleaf mantienen su sesión; las consultas GET que la interfaz ya usa también aceptan esa sesión.

| Método y ruta | Uso |
|---|---|
| `POST /api/v1/auth/login` | Autenticar y recibir un JWT Bearer |
| `POST /api/v1/auth/logout` | Indicar que el cliente descarte el token; el token vence en 60 minutos |
| `GET /api/v1/clientes/mi-cuenta` | Consultar el perfil propio del cliente autenticado |
| `GET /api/v1/clientes/mi-cuenta/solicitudes` | Consultar las solicitudes propias del cliente autenticado |
| `POST /api/v1/auth/registro-cliente` | Crear cuenta de cliente |
| `GET /api/v1/clientes` / `GET /api/v1/clientes/{id}` | Listar y consultar clientes |
| `POST /api/v1/clientes` / `PUT /api/v1/clientes/{id}` / `DELETE /api/v1/clientes/{id}` | CRUD de clientes |
| `GET /api/v1/solicitudes` / `GET /api/v1/solicitudes/{id}` | Listar y consultar solicitudes |
| `POST /api/v1/solicitudes` / `PUT /api/v1/solicitudes/{id}` / `DELETE /api/v1/solicitudes/{id}` | CRUD de solicitudes; una solicitud nueva recibe evaluación automática |
| `GET /api/v1/evaluaciones` / `GET /api/v1/evaluaciones/{id}` | Listar y consultar evaluaciones |
| `POST /api/v1/evaluaciones` / `PUT /api/v1/evaluaciones/{id}` / `DELETE /api/v1/evaluaciones/{id}` | CRUD de evaluaciones |
| `POST /api/v1/evaluaciones/{id}/decision` | Guardar decisión manual (`Aprobado` o `Rechazado`) y comentario |
| `GET /api/v1/politicas` / `POST /api/v1/politicas` / `PUT /api/v1/politicas/{id}` / `DELETE /api/v1/politicas/{id}` | Consultar y mantener políticas |
| `GET /api/v1/personal` / `POST /api/v1/personal` / `PUT /api/v1/personal/{id}` / `DELETE /api/v1/personal/{id}` | CRUD de personal del banco |
| `/api/v1/solicitudes/{id}/documentos` | Listar o subir documentos multipart; `GET .../{documento}/descargar` descarga uno |
| `GET /api/v1/reportes` | Consultar resumen y filas de reporte como JSON; admite `fechaInicio`, `fechaFin` y `persona` |

El Excel se descarga desde la ruta de la interfaz `/reportes/descargar`, que devuelve el archivo `.xlsx`. Las respuestas de consulta y CRUD bajo `/api/v1` son JSON; la descarga de un documento adjunto entrega el archivo binario porque su objetivo es descargar el documento.

Los controladores REST reutilizan los mismos servicios que las páginas. Las contraseñas no se incluyen en respuestas. Los JWT usan HS256, incluyen el rol y el ID de cuenta y vencen en 60 minutos. El token es firmado, no cifrado; no debe incluirse información secreta. La clave de demostración está en `app.jwt.secret`; en otro entorno define `CREDITICIO_JWT_SECRET` con una clave privada de al menos 32 bytes. Al cerrar sesión en una API, el cliente debe descartar el token; no existe lista de revocación. Los errores JWT, permisos, validaciones y registros inexistentes devuelven JSON con estado HTTP adecuado.

## Reglas automáticas de demostración

Al registrar una solicitud, el sistema toma los ingresos, gastos y deudas del cliente (o los datos entregados en el formulario) y crea una evaluación relacionada:

1. Estima la cuota simple como monto solicitado dividido entre plazo, sin interés.
2. Calcula DTI como `(deudas mensuales + cuota estimada) / ingresos mensuales × 100`.
3. Estima un puntaje demostrativo entre 300 y 850 usando `850 − (DTI × 5)`.
4. Rechaza si el DTI llega al umbral de rechazo, el puntaje es muy bajo o no queda capacidad disponible.
5. Aprueba si alcanza el puntaje mínimo, está debajo del DTI de aprobación y la capacidad disponible cubre la cuota.
6. Los casos intermedios o con información financiera incompleta pasan a revisión manual.

Hay cinco políticas de muestra, una por producto, con umbrales editables en **Políticas**. Por ejemplo, la personal inicia con puntaje mínimo 650, puntaje de rechazo hasta 499, DTI máximo de aprobación 35 % y DTI de rechazo 50 %; las otras categorías usan límites distintos. Se pueden agregar más políticas: para cada solicitud se elige la política activa más reciente que corresponda al tipo de crédito. Los umbrales del `data.sql` solo llenan valores vacíos, por lo que no pisan los cambios guardados desde la pantalla. Son criterios académicos plausibles para practicar el flujo, no estándares oficiales de una entidad financiera. El personal atiende las revisiones manuales en **Evaluaciones**. Los únicos resultados de evaluación son **Aprobado**, **Rechazado** y **Revisión manual**.

## Cuentas de demostración

Si las cuentas de ejemplo todavía no tienen contraseña, el arranque les asigna una clave protegida con PBKDF2:

- Personal del banco: `andrea.salazar@banco.com` / `Banco123!`
- Supervisor de prueba: `roberto.mendoza@banco.com` / `Banco123!`
- Cliente: DNI `70000001` / `Cliente123!`

El cliente también puede crear una cuenta desde **Crear cuenta de cliente**. Si su DNI ya existe, el correo debe coincidir con el registrado. El personal nuevo debe tener contraseña inicial al registrarse.

El alta de cliente (banco y portal) solicita los mismos datos personales y financieros. La antigüedad laboral se conserva como dato de perfil; en esta versión no cambia por sí sola el puntaje ni la decisión automática. Para las solicitudes nuevas, el estado no se elige en el formulario: el sistema lo calcula con la política vigente; solo el personal puede resolver las que quedan en revisión manual.

La autenticación actual es una implementación académica basada en sesión HTTP y filtro de rutas propio. Spring Security queda como mejora futura; no se debe presentar esta versión como un sistema bancario listo para producción.

## Documentos y reportes

- El formulario de solicitud acepta PDF, JPG y PNG, con un límite de 5 MB por archivo y hasta 20 MB por envío. Los bytes se guardan en PostgreSQL y se pueden descargar desde el detalle de la evaluación.
- El `data.sql` carga veinte clientes, veinte solicitudes y veinte evaluaciones relacionadas por sus claves; se puede ejecutar de nuevo sin duplicar esos registros de demostración.
- Los reportes conservan el resumen y los filtros por fechas; ahora también pueden filtrar por nombre o DNI.
- El archivo `.xlsx` contiene un resumen, agrupación mensual y filas con datos del cliente, finanzas, motivo, estado, puntaje, DTI, política aplicada, explicación de decisión y nombres de documentos.

Para ensayar resultados desde el formulario bancario o el portal, con crédito personal y plazo de 24 meses prueba: ingresos S/ 6,000, gastos S/ 1,400, deudas S/ 300 y monto S/ 12,000 (aprobación); ingresos S/ 5,000, gastos S/ 2,000, deudas S/ 500 y monto S/ 30,000 a 18 meses (revisión manual); ingresos S/ 3,000, gastos S/ 1,300, deudas S/ 1,200 y monto S/ 24,000 a 12 meses (rechazo). Son perfiles ficticios para demostrar las reglas configuradas.

## Base de datos y ejecución

1. En PostgreSQL, crea una base llamada `crediticio_db` si todavía no existe.
2. Revisa usuario, contraseña, URL y puerto en `src/main/resources/application.properties`.
3. `spring.jpa.hibernate.ddl-auto=update` crea o actualiza las tablas mapeadas, pero no crea la base de datos.
4. `data.sql` inserta datos relacionados de muestra sin duplicar los registros conocidos al reiniciar.
5. Con Java 21 y PostgreSQL activo, ejecuta en PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Abre `http://localhost:8080`. Para ejecutar el API desde Postman, llama primero al endpoint de login y conserva la cookie de sesión.

## Alcance

Es una aplicación académica para practicar MVC, capas, formularios, API REST, validación, persistencia y flujos de evaluación. Las reglas, el puntaje y la autenticación son para demostración, no una decisión real de crédito.








## Archivos ignorados por Git

El archivo .gitignore evita versionar los resultados de compilación y archivos locales de IDE. Spring Security aún no está integrado: se debe incorporar junto con su configuración para preservar el acceso actual.


> Preparación de Spring Security: se añadió únicamente la dependencia. Esta versión no está lista para ejecutar hasta completar la configuración de acceso y adaptar el interceptor, controladores y formularios.




