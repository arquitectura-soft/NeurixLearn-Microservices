# User Story: HU22 - Perfil del profesor

Feature: Visualización del perfil del profesor

  Como alumno,
  Quiero poder ver el perfil del profesor de un curso
  Para conocer su experiencia y calificaciones antes de inscribirme.

  Background:
    Given el profesor ha creado su perfil con información personal, especialidad y calificaciones

  Scenario: Visualización básica del perfil desde un curso
    Given el alumno accede a los detalles de un curso con profesor asignado
    When hace clic en el nombre o imagen del profesor
    Then el sistema muestra la información del perfil del profesor:
      | Nombre           |
      | Especialidad     |
      | Experiencia      |
      | Calificación promedio |

  Scenario: Visualización del perfil desde búsqueda directa
    Given el alumno realiza una búsqueda por nombre de profesor
    When selecciona un resultado de la lista
    Then se carga la vista del perfil con información detallada del docente

  Scenario: Perfil de profesor no encontrado
    Given el alumno accede a una URL de perfil inexistente o inválida
    When intenta cargar el perfil
    Then el sistema muestra un mensaje de "perfil no encontrado"
