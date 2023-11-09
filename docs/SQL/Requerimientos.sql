--REQUERIMINETO 1:
CREATE INDEX idx_habitacion_alojamiento ON habitaciones (idalojamiento);
CREATE INDEX idx_reserva_cuenta ON reservas (idcuenta);
CREATE INDEX idx_servicio_reserva_fechas ON servicios (idreserva, horarioinicial, horariofinal);

SELECT h.numhabitacion, SUM(s.costo) AS totalRecolectado
FROM habitaciones h
JOIN alojamientos a ON h.idalojamiento = a.idalojamiento
JOIN cuentas c ON a.idalojamiento = c.idalojamiento
JOIN reservas r ON c.idcuenta = r.idcuenta
JOIN servicios s ON r.idreserva = s.idreserva
WHERE s.horarioinicial >= CURRENT_DATE - INTERVAL '1' YEAR
AND s.horariofinal <= CURRENT_DATE
AND s.existe = 'SI'
GROUP BY h.numhabitacion;


--REQUERIMIENTO 2:
CREATE INDEX idx_bares_idservicio ON bares(idservicio);
CREATE INDEX idx_conferencias_idservicio ON conferencias(idservicio);
CREATE INDEX idx_gimnasios_idservicio ON gimnasios(idservicio);
CREATE INDEX idx_piscinas_idservicio ON piscinas(idservicio);
CREATE INDEX idx_restaurantes_idservicio ON restaurantes(idservicio);
CREATE INDEX idx_reuniones_idservicio ON reuniones(idservicio);
CREATE INDEX idx_spas_idservicio ON spas(idservicio);
CREATE INDEX idx_tiendas_idservicio ON tiendas(idservicio);
CREATE INDEX idx_utensilios_idservicio ON utensilios(idservicio);
CREATE INDEX idx_wifi_idservicio ON wifi(idservicio);
CREATE INDEX idx_lavados_idservicio ON lavados(idservicio);

SELECT s.idservicio,
       s.horarioinicial,
       s.horariofinal,
       s.costo,
       b.estilo AS bar_estilo,
       c.capacidad AS conferencia_capacidad,
       c.fecha AS conferencia_fecha,
       g.capacidad AS gimnasio_capacidad,
       p.capacidad AS piscina_capacidad,
       r.estilo AS restaurante_estilo,
       re.capacidad AS reunion_capacidad,
       spa.capacidad AS spa_capacidad,
       t.tipo AS tienda_tipo,
       u.estado AS utensilio_estado,
       w.anchobanda AS wifi_anchobanda,
       la.prendas AS lavado_prendas,
       COUNT(res.idreserva) AS cantidad_reservas
FROM servicios s
LEFT JOIN bares b ON s.idservicio = b.idservicio
LEFT JOIN conferencias c ON s.idservicio = c.idservicio
LEFT JOIN gimnasios g ON s.idservicio = g.idservicio
LEFT JOIN piscinas p ON s.idservicio = p.idservicio
LEFT JOIN restaurantes r ON s.idservicio = r.idservicio
LEFT JOIN reuniones re ON s.idservicio = re.idservicio
LEFT JOIN spas spa ON s.idservicio = spa.idservicio
LEFT JOIN tiendas t ON s.idservicio = t.idservicio
LEFT JOIN utensilios u ON s.idservicio = u.idservicio
LEFT JOIN wifi w ON s.idservicio = w.idservicio
LEFT JOIN lavados la ON s.idservicio = la.idservicio
JOIN reservas res ON s.idreserva = res.idreserva
GROUP BY s.idservicio,
         s.horarioinicial,
         s.horariofinal,
         s.costo,
         b.estilo,
         c.capacidad,
         c.fecha,
         g.capacidad,
         p.capacidad,
         r.estilo,
         re.capacidad,
         spa.capacidad,
         t.tipo,
         u.estado,
         w.anchobanda,
         la.prendas
ORDER BY cantidad_reservas DESC
FETCH FIRST 20 ROWS ONLY;

--REQUERIMIENTO 3:
CREATE INDEX idx_alojamiento_checkin_idalojamiento ON alojamientos(checkin, idalojamiento);

SELECT 
    h.numhabitacion,
    SUM(a.checkout - a.checkin) AS dias_ocupados,
    (SUM(a.checkout - a.checkin) / 365) * 100 AS indice_ocupacion
FROM 
    habitaciones h
JOIN 
    alojamientos a ON h.idalojamiento = a.idalojamiento
WHERE 
    a.checkin BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
GROUP BY 
    h.numhabitacion;

--REQUERIMIENTO 4:
SELECT s.*, t.tipo
FROM servicios s
INNER JOIN tiendas t ON s.idservicio = t.idservicio;

--REQUERIMIENTO 5:
CREATE INDEX idx_alojamientos_checkin ON alojamientos(checkin);
CREATE INDEX idx_alojamientos_checkout ON alojamientos(checkout);
CREATE INDEX idx_alojamientos_user_date ON alojamientos(iduser, checkin, checkout);

SELECT 
  u.nombreuser,
  h.numhabitacion,
  h.precionoche,
  a.checkin,
  a.checkout,
  s.costo AS costo_servicio,
  p.nombre AS nombre_producto,
  p.precio AS precio_producto
FROM 
  usuarios u
  JOIN alojamientos a ON u.idalojamiento = a.idalojamiento
  JOIN habitaciones h ON a.idalojamiento = h.idalojamiento
  LEFT JOIN reservas r ON u.iduser = r.idreserva
  LEFT JOIN servicios s ON r.idreserva = s.idreserva
  LEFT JOIN productos p ON s.idservicio = p.idproducto
WHERE 
  u.iduser = 1
  AND a.checkin BETWEEN TO_DATE('2023-01-01', 'YYYY-MM-DD') AND TO_DATE('2023-01-10', 'YYYY-MM-DD')
  AND a.checkout BETWEEN TO_DATE('2023-01-01', 'YYYY-MM-DD') AND TO_DATE('2023-01-10', 'YYYY-MM-DD')
  AND a.activa = 'SI';

--REQUERIMIENTO 6:
CREATE INDEX idx_cuentas_compuesto ON cuentas (idalojamiento, netocuenta);

WITH Ocupacion AS (
    SELECT
        a.checkin,
        COUNT(*) AS habitaciones_ocupadas
    FROM alojamientos a
    JOIN habitaciones h ON a.idalojamiento = h.idalojamiento
    WHERE a.activa = 'SI'
    GROUP BY a.checkin
),
Ingresos AS (
    SELECT
        a.checkin,
        SUM(c.netocuenta) AS ingresos_totales
    FROM alojamientos a
    JOIN cuentas c ON a.idalojamiento = c.idalojamiento
    WHERE a.activa = 'SI'
    GROUP BY a.checkin
)
SELECT
    (SELECT checkin FROM Ocupacion ORDER BY habitaciones_ocupadas DESC FETCH FIRST ROW ONLY) AS dia_mayor_ocupacion,
    (SELECT checkin FROM Ingresos ORDER BY ingresos_totales DESC FETCH FIRST ROW ONLY) AS dia_mayores_ingresos,
    (SELECT checkin FROM Ocupacion ORDER BY habitaciones_ocupadas ASC FETCH FIRST ROW ONLY) AS dia_menor_demanda
FROM dual;

--REQUERIMIENTO 7:
SELECT
  u.nombreuser,
  u.tipodocuser,
  u.numdocuser,
  u.correouser,
  SUM(NVL(a.checkout - a.checkin, 0)) AS total_dias_estadia,
  SUM(NVL(c.netocuenta, 0)) AS total_consumos
FROM 
  usuarios u
JOIN alojamientos a ON u.idalojamiento = a.idalojamiento
JOIN cuentas c ON a.idalojamiento = c.idalojamiento
WHERE 
  -- Filtramos para el último año de operaciones
  (a.checkin BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
   OR a.checkout BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE)
GROUP BY 
  u.nombreuser,
  u.tipodocuser,
  u.numdocuser,
  u.correouser
HAVING 
  -- El cliente ha estado al menos 14 días (dos semanas) o ha consumido más de 15 millones
  SUM(NVL(a.checkout - a.checkin, 0)) >= 14
  OR SUM(NVL(c.netocuenta, 0)) > 15000000;

  --REQUERIMIENTO 8:
  CREATE INDEX idx_horareserva ON reservas (horareserva) INDEXTYPE IS btree;
  CREATE INDEX idx_idservicio_reservas ON reservas (idservicio) INDEXTYPE IS btree;

  SELECT s.idservicio,
       COUNT(r.idreserva) AS cantidad_solicitudes,
       TO_CHAR(r.horareserva, 'IW') AS semana_iso,
       TO_CHAR(r.horareserva, 'YYYY') AS ano
FROM servicios s
JOIN reservas r ON s.idservicio = r.idreserva
WHERE r.horareserva BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
GROUP BY s.idservicio, TO_CHAR(r.horareserva, 'IW'), TO_CHAR(r.horareserva, 'YYYY')
HAVING COUNT(r.idreserva) < 3
ORDER BY ano, semana_iso;
