---

# 🏫 Trabajo Práctico: Inscripción a Cursos Optativos

## ✨ Contexto

La Universidad Nacional Tecnológica desea implementar una plataforma para la gestión de cursos optativos. Esta plataforma permitirá a los alumnos consultar la oferta de cursos disponibles y registrarse a los mismos. Los cursos tienen ciertos requisitos, cupos limitados y fechas de inicio. Los alumnos podrán inscribirse a un máximo de **dos** cursos y cancelar su inscripción si lo hacen **al menos una semana antes del inicio** del curso.

El sistema debe estar compuesto por:

* Un backend en **Java con Spring Boot**.
* Una base de datos en memoria **H2**.
* Un frontend desarrollado en **Angular**.

---

## ✅ Requerimientos Funcionales

### 🧑‍🎓 Gestión de alumnos

* Alta de alumnos con los siguientes datos:

  * Nombre completo
  * Año de cursado (1 a 5)
  * Legajo (único)
  * Email

### 📘 Gestión de cursos optativos

* Alta de cursos con los siguientes datos:

  * Nombre del curso
  * Descripción
  * Fecha de inicio
  * Cupo máximo de participantes
  * Año mínimo de cursado requerido para poder inscribirse

### 📌 Inscripción a cursos

* Los alumnos pueden inscribirse a cursos si:

  * No superan el límite de **2 inscripciones activas**.
  * Cumplen con el **año mínimo de cursado** del curso.
  * El curso tiene cupo disponible.
  * No están ya inscriptos en el curso.

### ❌ Cancelación de inscripción

* Los alumnos pueden cancelar su inscripción a un curso si:

  * Aún no ha pasado la **fecha límite de cancelación** (una semana antes del inicio del curso).
  * Estaban previamente inscriptos.
* La cancelación debe **liberar un cupo** en el curso.

### 📄 Consultas disponibles

* Listar todos los cursos disponibles con:

  * Nombre
  * Descripción
  * Fecha de inicio
  * Cupo disponible
* Ver las inscripciones activas de un alumno.

---

## 🚫 Requerimientos No Funcionales

* La aplicación debe tener una interfaz web responsive y amigable.
* Las reglas de negocio deben ser validadas tanto en frontend como en backend.
* La base de datos será en memoria (**H2**) y se poblará con datos de prueba al iniciar la aplicación.
* El backend debe exponer una API RESTful.
* El frontend y el backend deben correr en puertos separados y comunicarse por HTTP.

---

## 🧩 Backend - Java Spring Boot

### 📦 Entidades principales

1. `Alumno`

  * id (Long)
  * nombre
  * legajo (único)
  * email
  * anioCursado

2. `Curso`

  * id (Long)
  * nombre
  * descripcion
  * fechaInicio
  * cupoMaximo
  * anioMinimo

3. `Inscripcion`

  * id (Long)
  * alumno (ManyToOne)
  * curso (ManyToOne)
  * fechaInscripcion

### 📡 API REST

| Método | Endpoint                          | Descripción                          |
| ------ | --------------------------------- | ------------------------------------ |
| POST   | `/api/alumnos`                    | Crear un nuevo alumno                |
| GET    | `/api/alumnos/{id}/inscripciones` | Ver inscripciones activas del alumno |
| POST   | `/api/cursos`                     | Crear un nuevo curso                 |
| GET    | `/api/cursos`                     | Listar todos los cursos              |
| POST   | `/api/inscripciones`              | Inscribir alumno en un curso         |
| DELETE | `/api/inscripciones`              | Cancelar inscripción                 |

#### Ejemplo de request para inscribirse

```json
POST /api/inscripciones
{
  "alumnoId": 1,
  "cursoId": 2
}
```

#### Ejemplo de request para cancelar inscripción

```http
DELETE /api/inscripciones?alumnoId=1&cursoId=2
```

---

## 🖥️ Frontend - Angular

### 🧭 Funcionalidades requeridas

1. **Alta de alumnos**

  * Formulario para crear un nuevo alumno.

2. **Alta de cursos**

  * Formulario para crear un nuevo curso.

3. **Listado de cursos**

  * Tabla o cards con la información completa de los cursos.
  * Mostrar cupo disponible.
  * Botón de inscripción si cumple los requisitos.

4. **Inscripción a cursos**

  * Validar en el frontend que:

    * El alumno no tenga más de 2 inscripciones.
    * No esté ya inscripto al curso.
    * Cumpla con el año requerido.
    * Haya cupo disponible.

5. **Cancelar inscripción**

  * Mostrar inscripciones activas del alumno.
  * Botón de cancelar inscripción (si está dentro del plazo).

### 🧱 Componentes sugeridos

* `AlumnoFormComponent` (alta de alumno)
* `CursoFormComponent` (alta de curso)
* `CursoListComponent` (listar cursos e inscribirse)
* `InscripcionesComponent` (ver y cancelar inscripciones del alumno)

### 🧪 Validaciones en frontend

* Validaciones de formularios reactivos.
* Mensajes de error claros si se incumplen reglas.
* Confirmación de acciones importantes (inscripción, cancelación).

---

## 🧪 Reglas de Negocio a implementar

* Máximo **2 cursos por alumno**.
* No se permite **doble inscripción** al mismo curso.
* Solo alumnos con año de cursado >= `anioMinimo` del curso pueden inscribirse.
* La inscripción **solo se cancela** si faltan más de **7 días** para la fecha de inicio.
* Al cancelar, se **libera** un cupo.

---

## 📂 Extras (Opcional)

* Semillas de datos (`data.sql`) para poblar la base con cursos y alumnos de ejemplo.
* Paginación y filtrado de cursos en el frontend.
* Testing de endpoints (JUnit / Postman).

---
