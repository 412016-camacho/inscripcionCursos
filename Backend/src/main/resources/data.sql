-- CURSOS
INSERT INTO cursos (id, nombre, descripcion, fecha_inicio, cupo_maximo, anio_minimo) VALUES
(10, 'Matemática Avanzada', 'Curso intensivo de álgebra y cálculo', '2025-03-01', 30, 2),
(20, 'Programación I', 'Introducción a la programación en Java', '2025-03-15', 40, 1),
(30, 'Historia Universal', 'Desde la antigüedad hasta el siglo XXI', '2025-04-01', 25, 1),
(40, 'Biología Molecular', 'Estudio avanzado de genética', '2025-03-10', 20, 3),
(50, 'Física Teórica', 'Fundamentos de física moderna', '2025-03-20', 15, 3);


-- ALUMNOS
INSERT INTO alumnos (id, nombre, legajo, email, anio_cursado) VALUES
(10, 'Juan Pérez', 'LEG001', 'juan.perez@email.com', 1),
(20, 'María Gómez', 'LEG002', 'maria.gomez@email.com', 2),
(30, 'Carlos Ruiz', 'LEG003', 'carlos.ruiz@email.com', 3),
(40, 'Lucía Fernández', 'LEG004', 'lucia.fernandez@email.com', 2),
(50, 'Diego Torres', 'LEG005', 'diego.torres@email.com', 3);


-- INSCRIPCIONES
INSERT INTO inscripciones (id, alumno_id, curso_id, fecha_inscripcion) VALUES
(10, 10, 20, '2025-02-15'),
(20, 20, 10, '2025-02-16'),
(30, 30, 40, '2025-02-17'),
(40, 40, 30, '2025-02-18'),
(50, 50, 50, '2025-02-19');


