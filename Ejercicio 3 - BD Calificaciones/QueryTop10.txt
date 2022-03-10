SELECT TOP 10 T0.idAlumno, T0.Nombre, T0.aPaterno, T0.Grado, avg(T1.Calificacion) as 'Promedio'
FROM Alumnos T0 LEFT JOIN Calificaciones T1 ON
	T0.idAlumno = T1.idAlumno
GROUP BY T0.idAlumno, T0.Nombre, T0.aPaterno, T0.Grado
ORDER BY Promedio DESC