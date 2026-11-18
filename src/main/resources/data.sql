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

-- Diez perfiles adicionales para que la demostración tenga 20 clientes.
INSERT INTO clientes (codigo, nombre, dni, fecha_nacimiento, telefono, correo, direccion, estado, empresa, cargo, antiguedad_meses, ingresos_mensuales, gastos_mensuales, deudas_mensuales)
SELECT v.codigo, v.nombre, v.dni, v.nacimiento::date, v.telefono, v.correo, v.direccion, 'Activo', v.empresa, v.cargo, v.antiguedad, v.ingresos, v.gastos, v.deudas
FROM (VALUES
 ('CL-D011','Valeria Paredes','70000011','1992-03-18','900100011','valeria.paredes@example.com','Trujillo','Servicios del Norte','Contadora',48,5200,1850,450),
 ('CL-D012','Diego Alva','70000012','1989-11-02','900100012','diego.alva@example.com','Víctor Larco','Constructora Sol','Técnico',60,6100,2200,700),
 ('CL-D013','Camila Torres','70000013','1996-06-24','900100013','camila.torres@example.com','La Esperanza','Clínica Primavera','Enfermera',30,3900,1450,300),
 ('CL-D014','Renato Castillo','70000014','1984-09-09','900100014','renato.castillo@example.com','Huanchaco','Transporte Costero','Conductor',84,5700,2100,650),
 ('CL-D015','Lucía Mendoza','70000015','1991-01-27','900100015','lucia.mendoza@example.com','Moche','Colegio San Marcos','Docente',72,4800,1750,400),
 ('CL-D016','Andrés Ríos','70000016','1987-05-13','900100016','andres.rios@example.com','Laredo','Agro Valle','Supervisor',96,6900,2400,850),
 ('CL-D017','Paola Cárdenas','70000017','1994-12-05','900100017','paola.cardenas@example.com','Trujillo','Farmacia Central','Química farmacéutica',42,5500,1900,500),
 ('CL-D018','Jorge Villanueva','70000018','1982-02-16','900100018','jorge.villanueva@example.com','El Porvenir','Taller Villanueva','Mecánico',108,4300,1650,350),
 ('CL-D019','Mariana León','70000019','1997-08-30','900100019','mariana.leon@example.com','Trujillo','Instituto del Norte','Asistente administrativa',24,3600,1350,250),
 ('CL-D020','Óscar Salazar','70000020','1986-04-21','900100020','oscar.salazar@example.com','Salaverry','Pesquera Azul','Jefe de planta',90,7200,2600,900)
) AS v(codigo,nombre,dni,nacimiento,telefono,correo,direccion,empresa,cargo,antiguedad,ingresos,gastos,deudas)
WHERE NOT EXISTS (SELECT 1 FROM clientes c WHERE c.dni=v.dni OR c.codigo=v.codigo);

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
UPDATE solicitudes SET estado = 'Revisión manual' WHERE codigo = 'SOL-D007' AND estado = 'Pendiente';
UPDATE solicitudes SET estado = 'Aprobada' WHERE codigo = 'SOL-D008' AND estado = 'Pendiente';
UPDATE solicitudes SET estado = 'Revisión manual' WHERE codigo = 'SOL-D009' AND estado = 'Pendiente';
UPDATE solicitudes SET estado = 'Revisión manual' WHERE codigo = 'SOL-D010' AND estado = 'Pendiente';

-- Solicitudes relacionadas para los clientes CL-D011 ... CL-D020.
INSERT INTO solicitudes (codigo, cliente_id, tipo_credito, monto, plazo_meses, fecha, estado, motivo, ingresos_mensuales, gastos_mensuales, deudas_mensuales, observaciones)
SELECT v.codigo,c.id_cliente,v.tipo,v.monto,v.plazo,v.fecha,v.estado,v.motivo,v.ingresos,v.gastos,v.deudas,v.observaciones
FROM (VALUES
 ('SOL-D011','70000011','Personal',9000,24,'2026-10-11','Aprobada','Remodelación de vivienda',5200,1850,450,'Cliente con ingresos recurrentes.'),
 ('SOL-D012','70000012','Vehicular',28000,48,'2026-10-12','Revisión manual','Compra de vehículo para trabajo',6100,2200,700,'Verificar cotización y sustento de ingresos.'),
 ('SOL-D013','70000013','Educativo',6500,18,'2026-10-13','Aprobada','Especialización profesional',3900,1450,300,'Matrícula y programa identificados.'),
 ('SOL-D014','70000014','Hipotecario',120000,120,'2026-10-14','Rechazada','Ampliación de vivienda',5700,2100,650,'La cuota estimada excede el criterio de endeudamiento.'),
 ('SOL-D015','70000015','Personal',7500,18,'2026-10-15','Aprobada','Compra de equipo doméstico',4800,1750,400,'Capacidad de pago disponible.'),
 ('SOL-D016','70000016','Emprendimiento',35000,36,'2026-10-16','Revisión manual','Capital para ampliar negocio agrícola',6900,2400,850,'Revisar flujo de caja del negocio.'),
 ('SOL-D017','70000017','Vehicular',26000,36,'2026-10-17','Aprobada','Movilidad para actividad laboral',5500,1900,500,'Cuota estimada dentro del límite de referencia.'),
 ('SOL-D018','70000018','Educativo',4000,12,'2026-10-18','Revisión manual','Certificación técnica',4300,1650,350,'Confirmar documentos de matrícula.'),
 ('SOL-D019','70000019','Personal',5000,18,'2026-10-19','Rechazada','Consolidación de gastos',3600,1350,250,'La información debe revisarse por el nivel de endeudamiento.'),
 ('SOL-D020','70000020','Emprendimiento',42000,48,'2026-10-20','Aprobada','Equipamiento para negocio pesquero',7200,2600,900,'Ingresos y cuota estimada dentro de política demostrativa.')
) AS v(codigo,dni,tipo,monto,plazo,fecha,estado,motivo,ingresos,gastos,deudas,observaciones)
JOIN clientes c ON c.dni=v.dni
WHERE NOT EXISTS (SELECT 1 FROM solicitudes s WHERE s.codigo=v.codigo);

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
INSERT INTO politicas (codigo, producto, version_politica, vigencia, estado, descripcion)
SELECT 'POL-DEMO-004', 'Crédito para emprendimiento', '1.0', 'Vigente 2026', 'Activa', 'Criterio de demostración para pequeños negocios: revisar ingresos verificables, flujo de caja, antigüedad de actividad, cuota estimada y documentos de sustento.'
WHERE NOT EXISTS (SELECT 1 FROM politicas WHERE codigo = 'POL-DEMO-004');
INSERT INTO politicas (codigo, producto, version_politica, vigencia, estado, descripcion)
SELECT 'POL-DEMO-005', 'Crédito educativo', '1.0', 'Vigente 2026', 'Activa', 'Criterio de demostración para estudios: validar matrícula o programa, ingresos del solicitante o responsable, cuota estimada y capacidad mensual.'
WHERE NOT EXISTS (SELECT 1 FROM politicas WHERE codigo = 'POL-DEMO-005');

-- Completa límites solo si están vacíos; no reemplaza cambios guardados desde la pantalla Políticas.
UPDATE politicas SET puntaje_minimo_aprobar=COALESCE(puntaje_minimo_aprobar,650),puntaje_maximo_rechazo=COALESCE(puntaje_maximo_rechazo,499),dti_maximo_aprobacion=COALESCE(dti_maximo_aprobacion,35),dti_minimo_rechazo=COALESCE(dti_minimo_rechazo,50) WHERE codigo='POL-DEMO-001';
UPDATE politicas SET puntaje_minimo_aprobar=COALESCE(puntaje_minimo_aprobar,630),puntaje_maximo_rechazo=COALESCE(puntaje_maximo_rechazo,479),dti_maximo_aprobacion=COALESCE(dti_maximo_aprobacion,40),dti_minimo_rechazo=COALESCE(dti_minimo_rechazo,55) WHERE codigo='POL-DEMO-002';
UPDATE politicas SET puntaje_minimo_aprobar=COALESCE(puntaje_minimo_aprobar,680),puntaje_maximo_rechazo=COALESCE(puntaje_maximo_rechazo,519),dti_maximo_aprobacion=COALESCE(dti_maximo_aprobacion,30),dti_minimo_rechazo=COALESCE(dti_minimo_rechazo,45) WHERE codigo='POL-DEMO-003';
UPDATE politicas SET puntaje_minimo_aprobar=COALESCE(puntaje_minimo_aprobar,660),puntaje_maximo_rechazo=COALESCE(puntaje_maximo_rechazo,499),dti_maximo_aprobacion=COALESCE(dti_maximo_aprobacion,35),dti_minimo_rechazo=COALESCE(dti_minimo_rechazo,50) WHERE codigo='POL-DEMO-004';
UPDATE politicas SET puntaje_minimo_aprobar=COALESCE(puntaje_minimo_aprobar,620),puntaje_maximo_rechazo=COALESCE(puntaje_maximo_rechazo,479),dti_maximo_aprobacion=COALESCE(dti_maximo_aprobacion,40),dti_minimo_rechazo=COALESCE(dti_minimo_rechazo,55) WHERE codigo='POL-DEMO-005';

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
SELECT 'EVAL-D007', id_solicitud, 650, 'Medio', 'Revisión manual', 'POL-DEMO-005 · Educativo v1.0', 2100, 35.00, 750, 20000, 'La solicitud educativa requiere completar la revisión de matrícula.'
FROM solicitudes WHERE codigo = 'SOL-D007' AND NOT EXISTS (SELECT 1 FROM evaluaciones WHERE codigo = 'EVAL-D007');
INSERT INTO evaluaciones (codigo, solicitud_id, puntaje, nivel_riesgo, resultado, politica, ingreso_disponible, dti, capacidad_nueva_cuota, monto_maximo_estimado, comentario)
SELECT 'EVAL-D008', id_solicitud, 750, 'Bajo', 'Aprobado', 'POL-DEMO-001 · Personal v1.0', 3100, 27.60, 1150, 42000, 'Buen historial y margen mensual suficiente.'
FROM solicitudes WHERE codigo = 'SOL-D008' AND NOT EXISTS (SELECT 1 FROM evaluaciones WHERE codigo = 'EVAL-D008');
INSERT INTO evaluaciones (codigo, solicitud_id, puntaje, nivel_riesgo, resultado, politica, ingreso_disponible, dti, capacidad_nueva_cuota, monto_maximo_estimado, comentario)
SELECT 'EVAL-D009', id_solicitud, 635, 'Medio', 'Revisión manual', 'POL-DEMO-002 · Vehicular v1.0', 2700, 41.50, 680, 24000, 'Validar la estabilidad laboral y la documentacion presentada.'
FROM solicitudes WHERE codigo = 'SOL-D009' AND NOT EXISTS (SELECT 1 FROM evaluaciones WHERE codigo = 'EVAL-D009');
INSERT INTO evaluaciones (codigo, solicitud_id, puntaje, nivel_riesgo, resultado, politica, ingreso_disponible, dti, capacidad_nueva_cuota, monto_maximo_estimado, comentario)
SELECT 'EVAL-D010', id_solicitud, 690, 'Medio', 'Revisión manual', 'POL-DEMO-001 · Personal v1.0', 2850, 33.40, 860, 26000, 'Requiere confirmación final del analista.'
FROM solicitudes WHERE codigo = 'SOL-D010' AND NOT EXISTS (SELECT 1 FROM evaluaciones WHERE codigo = 'EVAL-D010');

UPDATE evaluaciones SET resultado='Revisión manual' WHERE resultado IS NULL OR resultado IN ('Pendiente','En evaluación');
UPDATE solicitudes SET estado='Revisión manual' WHERE estado IS NULL OR estado IN ('Pendiente','En evaluación');

-- Evaluaciones vinculadas con las diez solicitudes adicionales.
INSERT INTO evaluaciones (codigo,solicitud_id,puntaje,nivel_riesgo,resultado,politica,ingreso_disponible,dti,capacidad_nueva_cuota,monto_maximo_estimado,comentario)
SELECT v.codigo,s.id_solicitud,v.puntaje,v.riesgo,v.resultado,v.politica,v.disponible,v.dti,v.capacidad,v.maximo,v.comentario
FROM (VALUES
 ('EVAL-D011','SOL-D011',710,'Bajo','Aprobado','POL-DEMO-001 · 1.0',2900,25.00,900,21600,'Puntaje y capacidad dentro del umbral de demostración.'),
 ('EVAL-D012','SOL-D012',640,'Medio','Revisión manual','POL-DEMO-002 · 1.0',3200,40.00,900,43200,'Caso cercano al límite; analista verifica documentos.'),
 ('EVAL-D013','SOL-D013',700,'Bajo','Aprobado','POL-DEMO-005 · 1.0',2150,31.00,700,12600,'Capacidad de pago suficiente según el ejemplo educativo.'),
 ('EVAL-D014','SOL-D014',520,'Alto','Rechazado','POL-DEMO-003 · 1.0',2950,45.00,0,0,'DTI de referencia supera el máximo de rechazo.'),
 ('EVAL-D015','SOL-D015',690,'Bajo','Aprobado','POL-DEMO-001 · 1.0',2650,35.00,800,14400,'Ingresos y capacidad mensual compatibles.'),
 ('EVAL-D016','SOL-D016',650,'Medio','Revisión manual','POL-DEMO-004 · 1.0',3650,42.00,900,32400,'Se requiere revisar flujo de caja del emprendimiento.'),
 ('EVAL-D017','SOL-D017',675,'Bajo','Aprobado','POL-DEMO-002 · 1.0',3100,30.00,850,30600,'Solicitud dentro de los límites demostrativos del producto.'),
 ('EVAL-D018','SOL-D018',625,'Medio','Revisión manual','POL-DEMO-005 · 1.0',2300,40.00,650,7800,'Confirmar matrícula y documentos antes de decidir.'),
 ('EVAL-D019','SOL-D019',480,'Alto','Rechazado','POL-DEMO-001 · 1.0',2000,55.00,0,0,'Puntaje y relación de deuda requieren rechazo según los límites de ejemplo.'),
 ('EVAL-D020','SOL-D020',720,'Bajo','Aprobado','POL-DEMO-004 · 1.0',3700,32.00,1100,52800,'Capacidad y sustento comercial compatibles con la política de muestra.')
) AS v(codigo,solicitud_codigo,puntaje,riesgo,resultado,politica,disponible,dti,capacidad,maximo,comentario)
JOIN solicitudes s ON s.codigo=v.solicitud_codigo
WHERE NOT EXISTS (SELECT 1 FROM evaluaciones e WHERE e.codigo=v.codigo);

COMMIT;
