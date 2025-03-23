CREATE VIEW `Vista_de_Prueba` AS SELECT 
    d.name AS Dispositivo,
    g.name AS Grupo,
    s.info AS Sensor
FROM `x4c`.`group` g
JOIN `x4c`.`device` d ON g.idGroup = d.Group_idGroup
JOIN `x4c`.`sensor` s ON d.idDevice = s.Device_idDevice;