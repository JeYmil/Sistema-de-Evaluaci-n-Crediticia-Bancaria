-- Datos de ejemplo del Sistema Crediticio Bancario.
-- Las comprobaciones NOT EXISTS hacen que pueda ejecutarse en cada inicio sin repetir la muestra.
BEGIN;

-- Clientes de demostración
INSERT INTO clientes (
    codigo,
    nombre,
    dni,
    fecha_nacimiento,
    telefono,
    correo,
    direccion,
    estado,
    empresa,
    cargo,
    antiguedad_meses,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales
)
SELECT
    'CL-D001', 'Ana Torres', '70000001', '1990-04-12', '900100001', 'ana.torres@example.com', 'Trujillo', 'Activo', 'Comercial Norte', 'Analista', 36, 4200, 1500, 400
WHERE NOT EXISTS (
    SELECT 1
    FROM clientes
    WHERE dni = '70000001' OR codigo = 'CL-D001'
);

INSERT INTO clientes (
    codigo,
    nombre,
    dni,
    fecha_nacimiento,
    telefono,
    correo,
    direccion,
    estado,
    empresa,
    cargo,
    antiguedad_meses,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales
)
SELECT
    'CL-D002', 'Luis Rojas', '70000002', '1987-08-20', '900100002', 'luis.rojas@example.com', 'La Esperanza', 'Activo', 'Servicios Andinos', 'Supervisor', 60, 5600, 1900, 600
WHERE NOT EXISTS (
    SELECT 1
    FROM clientes
    WHERE dni = '70000002' OR codigo = 'CL-D002'
);

INSERT INTO clientes (
    codigo,
    nombre,
    dni,
    fecha_nacimiento,
    telefono,
    correo,
    direccion,
    estado,
    empresa,
    cargo,
    antiguedad_meses,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales
)
SELECT
    'CL-D003', 'Marta Salas', '70000003', '1993-02-05', '900100003', 'marta.salas@example.com', 'Víctor Larco', 'Activo', 'Clínica del Norte', 'Enfermera', 24, 3800, 1400, 250
WHERE NOT EXISTS (
    SELECT 1
    FROM clientes
    WHERE dni = '70000003' OR codigo = 'CL-D003'
);

INSERT INTO clientes (
    codigo,
    nombre,
    dni,
    fecha_nacimiento,
    telefono,
    correo,
    direccion,
    estado,
    empresa,
    cargo,
    antiguedad_meses,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales
)
SELECT
    'CL-D004', 'Pedro Vega', '70000004', '1985-11-17', '900100004', 'pedro.vega@example.com', 'Huanchaco', 'Activo', 'Transportes Vega', 'Conductor', 72, 4900, 1800, 800
WHERE NOT EXISTS (
    SELECT 1
    FROM clientes
    WHERE dni = '70000004' OR codigo = 'CL-D004'
);

INSERT INTO clientes (
    codigo,
    nombre,
    dni,
    fecha_nacimiento,
    telefono,
    correo,
    direccion,
    estado,
    empresa,
    cargo,
    antiguedad_meses,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales
)
SELECT
    'CL-D005', 'Rosa Díaz', '70000005', '1991-06-28', '900100005', 'rosa.diaz@example.com', 'Moche', 'Activo', 'Educación Futuro', 'Docente', 48, 4500, 1600, 350
WHERE NOT EXISTS (
    SELECT 1
    FROM clientes
    WHERE dni = '70000005' OR codigo = 'CL-D005'
);

INSERT INTO clientes (
    codigo,
    nombre,
    dni,
    fecha_nacimiento,
    telefono,
    correo,
    direccion,
    estado,
    empresa,
    cargo,
    antiguedad_meses,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales
)
SELECT
    'CL-D006', 'Carlos León', '70000006', '1989-01-09', '900100006', 'carlos.leon@example.com', 'Trujillo', 'Activo', 'Agropecuaria Valle', 'Técnico', 30, 4100, 1450, 500
WHERE NOT EXISTS (
    SELECT 1
    FROM clientes
    WHERE dni = '70000006' OR codigo = 'CL-D006'
);

INSERT INTO clientes (
    codigo,
    nombre,
    dni,
    fecha_nacimiento,
    telefono,
    correo,
    direccion,
    estado,
    empresa,
    cargo,
    antiguedad_meses,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales
)
SELECT
    'CL-D007', 'Elena Cruz', '70000007', '1995-09-14', '900100007', 'elena.cruz@example.com', 'Laredo', 'Activo', 'Banco Regional', 'Asistente', 18, 3500, 1200, 200
WHERE NOT EXISTS (
    SELECT 1
    FROM clientes
    WHERE dni = '70000007' OR codigo = 'CL-D007'
);

INSERT INTO clientes (
    codigo,
    nombre,
    dni,
    fecha_nacimiento,
    telefono,
    correo,
    direccion,
    estado,
    empresa,
    cargo,
    antiguedad_meses,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales
)
SELECT
    'CL-D008', 'Jorge Paredes', '70000008', '1983-03-22', '900100008', 'jorge.paredes@example.com', 'Florencia de Mora', 'Activo', 'Constructora Sol', 'Técnico', 84, 6200, 2200, 900
WHERE NOT EXISTS (
    SELECT 1
    FROM clientes
    WHERE dni = '70000008' OR codigo = 'CL-D008'
);

INSERT INTO clientes (
    codigo,
    nombre,
    dni,
    fecha_nacimiento,
    telefono,
    correo,
    direccion,
    estado,
    empresa,
    cargo,
    antiguedad_meses,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales
)
SELECT
    'CL-D009', 'Patricia Gil', '70000009', '1992-12-01', '900100009', 'patricia.gil@example.com', 'Trujillo', 'Activo', 'Mercado Central', 'Administradora', 42, 4700, 1700, 300
WHERE NOT EXISTS (
    SELECT 1
    FROM clientes
    WHERE dni = '70000009' OR codigo = 'CL-D009'
);

INSERT INTO clientes (
    codigo,
    nombre,
    dni,
    fecha_nacimiento,
    telefono,
    correo,
    direccion,
    estado,
    empresa,
    cargo,
    antiguedad_meses,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales
)
SELECT
    'CL-D010', 'Miguel Soto', '70000010', '1988-07-07', '900100010', 'miguel.soto@example.com', 'Salaverry', 'Activo', 'Pesquera Marina', 'Operario', 54, 5200, 1850, 700
WHERE NOT EXISTS (
    SELECT 1
    FROM clientes
    WHERE dni = '70000010' OR codigo = 'CL-D010'
);

INSERT INTO solicitudes (
    codigo,
    cliente_id,
    tipo_credito,
    monto,
    plazo_meses,
    fecha,
    estado,
    motivo,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales,
    observaciones
)
SELECT
    'SOL-D001', id_cliente, 'Personal', 8000, 18, '2026-10-01', 'Pendiente', 'Gastos familiares', 4200, 1500, 400, 'Registro demostrativo'
FROM clientes
WHERE dni = '70000001'
  AND NOT EXISTS (
      SELECT 1
      FROM solicitudes
      WHERE codigo = 'SOL-D001'
  );

INSERT INTO solicitudes (
    codigo,
    cliente_id,
    tipo_credito,
    monto,
    plazo_meses,
    fecha,
    estado,
    motivo,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales,
    observaciones
)
SELECT
    'SOL-D002', id_cliente, 'Vehicular', 24000, 36, '2026-10-02', 'Pendiente', 'Compra de vehículo', 5600, 1900, 600, 'Registro demostrativo'
FROM clientes
WHERE dni = '70000002'
  AND NOT EXISTS (
      SELECT 1
      FROM solicitudes
      WHERE codigo = 'SOL-D002'
  );

INSERT INTO solicitudes (
    codigo,
    cliente_id,
    tipo_credito,
    monto,
    plazo_meses,
    fecha,
    estado,
    motivo,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales,
    observaciones
)
SELECT
    'SOL-D003', id_cliente, 'Personal', 6000, 12, '2026-10-03', 'Pendiente', 'Estudios', 3800, 1400, 250, 'Registro demostrativo'
FROM clientes
WHERE dni = '70000003'
  AND NOT EXISTS (
      SELECT 1
      FROM solicitudes
      WHERE codigo = 'SOL-D003'
  );

INSERT INTO solicitudes (
    codigo,
    cliente_id,
    tipo_credito,
    monto,
    plazo_meses,
    fecha,
    estado,
    motivo,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales,
    observaciones
)
SELECT
    'SOL-D004', id_cliente, 'Emprendimiento', 18000, 24, '2026-10-04', 'Pendiente', 'Capital de trabajo', 4900, 1800, 800, 'Registro demostrativo'
FROM clientes
WHERE dni = '70000004'
  AND NOT EXISTS (
      SELECT 1
      FROM solicitudes
      WHERE codigo = 'SOL-D004'
  );

INSERT INTO solicitudes (
    codigo,
    cliente_id,
    tipo_credito,
    monto,
    plazo_meses,
    fecha,
    estado,
    motivo,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales,
    observaciones
)
SELECT
    'SOL-D005', id_cliente, 'Hipotecario', 95000, 120, '2026-10-05', 'Pendiente', 'Mejora de vivienda', 4500, 1600, 350, 'Registro demostrativo'
FROM clientes
WHERE dni = '70000005'
  AND NOT EXISTS (
      SELECT 1
      FROM solicitudes
      WHERE codigo = 'SOL-D005'
  );

INSERT INTO solicitudes (
    codigo,
    cliente_id,
    tipo_credito,
    monto,
    plazo_meses,
    fecha,
    estado,
    motivo,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales,
    observaciones
)
SELECT
    'SOL-D006', id_cliente, 'Personal', 12000, 24, '2026-10-06', 'Pendiente', 'Consolidación de deudas', 4100, 1450, 500, 'Registro demostrativo'
FROM clientes
WHERE dni = '70000006'
  AND NOT EXISTS (
      SELECT 1
      FROM solicitudes
      WHERE codigo = 'SOL-D006'
  );

INSERT INTO solicitudes (
    codigo,
    cliente_id,
    tipo_credito,
    monto,
    plazo_meses,
    fecha,
    estado,
    motivo,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales,
    observaciones
)
SELECT
    'SOL-D007', id_cliente, 'Educativo', 5000, 12, '2026-10-07', 'Pendiente', 'Compra de equipo', 3500, 1200, 200, 'Registro demostrativo'
FROM clientes
WHERE dni = '70000007'
  AND NOT EXISTS (
      SELECT 1
      FROM solicitudes
      WHERE codigo = 'SOL-D007'
  );

INSERT INTO solicitudes (
    codigo,
    cliente_id,
    tipo_credito,
    monto,
    plazo_meses,
    fecha,
    estado,
    motivo,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales,
    observaciones
)
SELECT
    'SOL-D008', id_cliente, 'Emprendimiento', 30000, 36, '2026-10-08', 'Pendiente', 'Ampliación de negocio', 6200, 2200, 900, 'Registro demostrativo'
FROM clientes
WHERE dni = '70000008'
  AND NOT EXISTS (
      SELECT 1
      FROM solicitudes
      WHERE codigo = 'SOL-D008'
  );

INSERT INTO solicitudes (
    codigo,
    cliente_id,
    tipo_credito,
    monto,
    plazo_meses,
    fecha,
    estado,
    motivo,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales,
    observaciones
)
SELECT
    'SOL-D009', id_cliente, 'Vehicular', 22000, 30, '2026-10-09', 'Pendiente', 'Compra de motocicleta', 4700, 1700, 300, 'Registro demostrativo'
FROM clientes
WHERE dni = '70000009'
  AND NOT EXISTS (
      SELECT 1
      FROM solicitudes
      WHERE codigo = 'SOL-D009'
  );

INSERT INTO solicitudes (
    codigo,
    cliente_id,
    tipo_credito,
    monto,
    plazo_meses,
    fecha,
    estado,
    motivo,
    ingresos_mensuales,
    gastos_mensuales,
    deudas_mensuales,
    observaciones
)
SELECT
    'SOL-D010', id_cliente, 'Personal', 9000, 18, '2026-10-10', 'Pendiente', 'Reparación de vivienda', 5200, 1850, 700, 'Registro demostrativo'
FROM clientes
WHERE dni = '70000010'
  AND NOT EXISTS (
      SELECT 1
      FROM solicitudes
      WHERE codigo = 'SOL-D010'
  );

-- Decisiones coherentes con las evaluaciones de muestra.
-- Solo se actualizan solicitudes que sigan pendientes para respetar cambios posteriores del usuario.
UPDATE solicitudes SET estado = 'Aprobada' WHERE codigo = 'SOL-D001' AND estado = 'Pendiente';
UPDATE solicitudes SET estado = 'Aprobada' WHERE codigo = 'SOL-D002' AND estado = 'Pendiente';
UPDATE solicitudes SET estado = 'Rechazada' WHERE codigo = 'SOL-D003' AND estado = 'Pendiente';
UPDATE solicitudes SET estado = 'Revisión manual' WHERE codigo = 'SOL-D004' AND estado = 'Pendiente';
UPDATE solicitudes SET estado = 'Aprobada' WHERE codigo = 'SOL-D005' AND estado = 'Pendiente';
UPDATE solicitudes SET estado = 'Rechazada' WHERE codigo = 'SOL-D006' AND estado = 'Pendiente';
UPDATE solicitudes SET estado = 'En evaluación' WHERE codigo = 'SOL-D007' AND estado = 'Pendiente';
UPDATE solicitudes SET estado = 'Aprobada' WHERE codigo = 'SOL-D008' AND estado = 'Pendiente';
UPDATE solicitudes SET estado = 'Revisión manual' WHERE codigo = 'SOL-D009' AND estado = 'Pendiente';
UPDATE solicitudes SET estado = 'En evaluación' WHERE codigo = 'SOL-D010' AND estado = 'Pendiente';

-- Politicas que se mencionan en el campo de politica de cada evaluacion.
INSERT INTO politicas (codigo, producto, version_politica, vigencia, estado, descripcion)
SELECT 'POL-DEMO-001', 'Crédito personal', '1.0', 'Vigente 2026', 'Activa', 'Evaluacion de credito personal: ingresos verificables, historial favorable y capacidad de pago.'
WHERE NOT EXISTS (SELECT 1 FROM politicas WHERE codigo = 'POL-DEMO-001');
INSERT INTO politicas (codigo, producto, version_politica, vigencia, estado, descripcion)
SELECT 'POL-DEMO-002', 'Crédito vehicular', '1.0', 'Vigente 2026', 'Activa', 'Evaluacion de credito vehicular: cuota compatible con el ingreso y documentacion completa.'
WHERE NOT EXISTS (SELECT 1 FROM politicas WHERE codigo = 'POL-DEMO-002');
INSERT INTO politicas (codigo, producto, version_politica, vigencia, estado, descripcion)
SELECT 'POL-DEMO-003', 'Crédito hipotecario', '1.0', 'Vigente 2026', 'Activa', 'Evaluacion hipotecaria: capacidad de pago, estabilidad de ingresos y revision documental.'
WHERE NOT EXISTS (SELECT 1 FROM politicas WHERE codigo = 'POL-DEMO-003');

-- Define umbrales de muestra solo en políticas que aún no los tengan.
UPDATE politicas SET puntaje_minimo_aprobar=COALESCE(puntaje_minimo_aprobar,650),
    puntaje_maximo_rechazo=COALESCE(puntaje_maximo_rechazo,499),
    dti_maximo_aprobacion=COALESCE(dti_maximo_aprobacion,35.0),
    dti_minimo_rechazo=COALESCE(dti_minimo_rechazo,50.0);

-- Personal interno de ejemplo. Si no tienen clave protegida, el inicio de la app asigna una clave de demostración.
INSERT INTO usuarios (nombre_completo, correo, cargo, rol, estado)
SELECT 'Andrea Salazar', 'andrea.salazar@banco.com', 'Analista de créditos', 'Analista', 'Activo'
WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE correo = 'andrea.salazar@banco.com');
INSERT INTO usuarios (nombre_completo, correo, cargo, rol, estado)
SELECT 'Roberto Mendoza', 'roberto.mendoza@banco.com', 'Supervisor de créditos', 'Supervisor', 'Activo'
WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE correo = 'roberto.mendoza@banco.com');

-- Evaluaciones enlazadas con SOL-D001 ... SOL-D010 por solicitud_id.
INSERT INTO evaluaciones (codigo, solicitud_id, puntaje, nivel_riesgo, resultado, politica, ingreso_disponible, dti, capacidad_nueva_cuota, monto_maximo_estimado, comentario)
SELECT 'EVAL-D001', id_solicitud, 742, 'Bajo', 'Aprobado', 'POL-DEMO-001 · Personal v1.0', 2300, 24.50, 920, 28000, 'Ingresos estables y capacidad de pago suficiente.'
FROM solicitudes WHERE codigo = 'SOL-D001' AND NOT EXISTS (SELECT 1 FROM evaluaciones WHERE codigo = 'EVAL-D001');
INSERT INTO evaluaciones (codigo, solicitud_id, puntaje, nivel_riesgo, resultado, politica, ingreso_disponible, dti, capacidad_nueva_cuota, monto_maximo_estimado, comentario)
SELECT 'EVAL-D002', id_solicitud, 685, 'Medio', 'Aprobado', 'POL-DEMO-002 · Vehicular v1.0', 3100, 31.20, 1050, 36000, 'Capacidad de pago adecuada para el monto solicitado.'
FROM solicitudes WHERE codigo = 'SOL-D002' AND NOT EXISTS (SELECT 1 FROM evaluaciones WHERE codigo = 'EVAL-D002');
INSERT INTO evaluaciones (codigo, solicitud_id, puntaje, nivel_riesgo, resultado, politica, ingreso_disponible, dti, capacidad_nueva_cuota, monto_maximo_estimado, comentario)
SELECT 'EVAL-D003', id_solicitud, 580, 'Alto', 'Rechazado', 'POL-DEMO-001 · Personal v1.0', 2150, 52.30, 420, 9000, 'El nivel de deuda supera el limite definido para este ejemplo.'
FROM solicitudes WHERE codigo = 'SOL-D003' AND NOT EXISTS (SELECT 1 FROM evaluaciones WHERE codigo = 'EVAL-D003');
INSERT INTO evaluaciones (codigo, solicitud_id, puntaje, nivel_riesgo, resultado, politica, ingreso_disponible, dti, capacidad_nueva_cuota, monto_maximo_estimado, comentario)
SELECT 'EVAL-D004', id_solicitud, 640, 'Medio', 'Revisión manual', 'POL-DEMO-001 · Personal v1.0', 2300, 39.80, 700, 18000, 'Requiere revisar documentos de ingresos antes de decidir.'
FROM solicitudes WHERE codigo = 'SOL-D004' AND NOT EXISTS (SELECT 1 FROM evaluaciones WHERE codigo = 'EVAL-D004');
INSERT INTO evaluaciones (codigo, solicitud_id, puntaje, nivel_riesgo, resultado, politica, ingreso_disponible, dti, capacidad_nueva_cuota, monto_maximo_estimado, comentario)
SELECT 'EVAL-D005', id_solicitud, 710, 'Bajo', 'Aprobado', 'POL-DEMO-003 · Hipotecario v1.0', 2550, 28.40, 1000, 110000, 'Perfil estable; monto sujeto a validacion de documentos.'
FROM solicitudes WHERE codigo = 'SOL-D005' AND NOT EXISTS (SELECT 1 FROM evaluaciones WHERE codigo = 'EVAL-D005');
INSERT INTO evaluaciones (codigo, solicitud_id, puntaje, nivel_riesgo, resultado, politica, ingreso_disponible, dti, capacidad_nueva_cuota, monto_maximo_estimado, comentario)
SELECT 'EVAL-D006', id_solicitud, 610, 'Medio', 'Rechazado', 'POL-DEMO-001 · Personal v1.0', 2150, 48.10, 500, 10000, 'La cuota propuesta no cumple el criterio de capacidad de pago.'
FROM solicitudes WHERE codigo = 'SOL-D006' AND NOT EXISTS (SELECT 1 FROM evaluaciones WHERE codigo = 'EVAL-D006');
INSERT INTO evaluaciones (codigo, solicitud_id, puntaje, nivel_riesgo, resultado, politica, ingreso_disponible, dti, capacidad_nueva_cuota, monto_maximo_estimado, comentario)
SELECT 'EVAL-D007', id_solicitud, 650, 'Medio', 'Pendiente', 'POL-DEMO-001 · Personal v1.0', 2100, 35.00, 750, 20000, 'Evaluacion iniciada; pendiente de completar la revision.'
FROM solicitudes WHERE codigo = 'SOL-D007' AND NOT EXISTS (SELECT 1 FROM evaluaciones WHERE codigo = 'EVAL-D007');
INSERT INTO evaluaciones (codigo, solicitud_id, puntaje, nivel_riesgo, resultado, politica, ingreso_disponible, dti, capacidad_nueva_cuota, monto_maximo_estimado, comentario)
SELECT 'EVAL-D008', id_solicitud, 750, 'Bajo', 'Aprobado', 'POL-DEMO-001 · Personal v1.0', 3100, 27.60, 1150, 42000, 'Buen historial y margen mensual suficiente.'
FROM solicitudes WHERE codigo = 'SOL-D008' AND NOT EXISTS (SELECT 1 FROM evaluaciones WHERE codigo = 'EVAL-D008');
INSERT INTO evaluaciones (codigo, solicitud_id, puntaje, nivel_riesgo, resultado, politica, ingreso_disponible, dti, capacidad_nueva_cuota, monto_maximo_estimado, comentario)
SELECT 'EVAL-D009', id_solicitud, 635, 'Medio', 'Revisión manual', 'POL-DEMO-002 · Vehicular v1.0', 2700, 41.50, 680, 24000, 'Validar la estabilidad laboral y la documentacion presentada.'
FROM solicitudes WHERE codigo = 'SOL-D009' AND NOT EXISTS (SELECT 1 FROM evaluaciones WHERE codigo = 'EVAL-D009');
INSERT INTO evaluaciones (codigo, solicitud_id, puntaje, nivel_riesgo, resultado, politica, ingreso_disponible, dti, capacidad_nueva_cuota, monto_maximo_estimado, comentario)
SELECT 'EVAL-D010', id_solicitud, 690, 'Medio', 'Pendiente', 'POL-DEMO-001 · Personal v1.0', 2850, 33.40, 860, 26000, 'Pendiente de confirmacion final del analista.'
FROM solicitudes WHERE codigo = 'SOL-D010' AND NOT EXISTS (SELECT 1 FROM evaluaciones WHERE codigo = 'EVAL-D010');

COMMIT;
