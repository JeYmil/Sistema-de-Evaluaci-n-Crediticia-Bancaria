# Sistema de Evaluación Crediticia Bancaria — v1.24

Aplicación académica con vistas Thymeleaf para el personal del banco y un portal para clientes. Registra solicitudes en PostgreSQL, aplica reglas de evaluación de ejemplo, conserva documentos de sustento y ofrece rutas REST para integraciones.

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
├── config/                             Sesión, rutas protegidas y cuentas de muestra
├── controller/                        Controladores MVC que muestran Thymeleaf
│   └── api/                            Controladores REST que entregan JSON/archivos
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

Las páginas web siguen MVC: el controlador recibe una petición, llama al servicio y devuelve una vista. En el API, las rutas `@RestController` responden datos JSON. Ambos recorridos usan los mismos servicios y repositorios:

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

El prefijo versionado es `/api/v1`. Inicia sesión primero para usar las rutas de trabajo; el API utiliza la sesión HTTP y devuelve JSON. `POST /api/v1/auth/login` crea la sesión.

| Método y ruta | Uso |
|---|---|
| `POST /api/v1/auth/login` | Iniciar sesión enviando `usuario` y `password` en JSON |
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
| `GET /api/v1/reportes/excel` | Descargar el reporte Excel; admite `fechaInicio`, `fechaFin` y `persona` |

Los controladores REST reutilizan los mismos servicios que las páginas. Las contraseñas no se incluyen en respuestas. Las solicitudes JSON inválidas o los identificadores inexistentes devuelven un error JSON con estado HTTP adecuado.

## Reglas automáticas de demostración

Al registrar una solicitud, el sistema toma los ingresos, gastos y deudas del cliente (o los datos entregados en el formulario) y crea una evaluación relacionada:

1. Estima la cuota simple como monto solicitado dividido entre plazo, sin interés.
2. Calcula DTI como `(deudas mensuales + cuota estimada) / ingresos mensuales × 100`.
3. Estima un puntaje demostrativo entre 300 y 850 usando `850 − (DTI × 5)`.
4. Rechaza si el DTI llega al umbral de rechazo, el puntaje es muy bajo o no queda capacidad disponible.
5. Aprueba si alcanza el puntaje mínimo, está debajo del DTI de aprobación y la capacidad disponible cubre la cuota.
6. Los casos intermedios o con información financiera incompleta pasan a revisión manual.

Los umbrales se pueden modificar en **Políticas**: puntaje mínimo para aprobar (inicial 650), puntaje máximo de rechazo (499), DTI máximo para aprobar (35 %) y DTI de rechazo (50 %). Estas reglas son didácticas; no sustituyen una evaluación bancaria real. El personal atiende las revisiones manuales en **Evaluaciones**.

## Cuentas de demostración

Si las cuentas de ejemplo todavía no tienen contraseña, el arranque les asigna una clave protegida con PBKDF2:

- Personal del banco: `andrea.salazar@banco.com` / `Banco123!`
- Cliente: DNI `70000001` / `Cliente123!`

El cliente también puede crear una cuenta desde **Crear cuenta de cliente**. Si su DNI ya existe, el correo debe coincidir con el registrado. El personal nuevo debe tener contraseña inicial al registrarse.

La autenticación actual es una implementación académica basada en sesión HTTP y filtro de rutas propio. Spring Security queda como mejora futura; no se debe presentar esta versión como un sistema bancario listo para producción.

## Documentos y reportes

- El formulario de solicitud acepta PDF, JPG y PNG, con un límite de 5 MB por archivo y hasta 20 MB por envío. Los bytes se guardan en PostgreSQL y se pueden descargar desde el detalle de la evaluación.
- Los reportes conservan el resumen y los filtros por fechas; ahora también pueden filtrar por nombre o DNI.
- El archivo `.xlsx` contiene un resumen, agrupación mensual y filas con datos del cliente, finanzas, motivo, estado, puntaje, DTI, política aplicada, explicación de decisión y nombres de documentos.

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
