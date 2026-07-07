# User Story: HU04 - Inscribirse en un curso

Feature: Inscripción a un curso

  Como alumno,
  Quiero inscribirme en un curso de mi interés
  Para poder acceder a sus contenidos y participar en él.

  Background:
    Given el alumno ha iniciado sesión y navega por la lista de cursos disponibles

  Scenario: Inscripción exitosa a un curso
    Given el alumno selecciona el curso "Fundamentos de Java"
    When hace clic en el botón Inscribirse
    Then el sistema lo registra como estudiante del curso
    And le muestra un mensaje de confirmación
    And el curso aparece en su lista de cursos actuales

  Scenario: Intento de reinscripción a un curso ya inscrito
    Given el alumno ya está inscrito en "Fundamentos de Java"
    When accede nuevamente a la página del curso
    Then el sistema desactiva el botón de inscripción
    And muestra el mensaje "Ya estás inscrito en este curso"

  Scenario: Error al inscribirse sin conexión
    Given el alumno pierde conexión a internet al intentar inscribirse
    When hace clic en el botón Inscribirse
    Then el sistema muestra un mensaje de error de red
