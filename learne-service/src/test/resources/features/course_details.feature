# User Story: HU03 - Ver detalles de un curso

Feature: Visualización de información detallada de un curso

  Como alumno,
  Quiero ver más detalles de un curso antes de inscribirme
  Para asegurarme de que se ajusta a mis intereses y nivel.

  Background:
    Given el alumno ha iniciado sesión y visualiza la lista de cursos disponibles

  Scenario: Acceder a la vista de detalles desde un curso listado
    Given el alumno ve el curso "Fundamentos de Java" en la lista
    When hace clic en el botón "Ver más"
    Then el sistema muestra la vista de detalles del curso con:
      | Descripción completa      |
      | Objetivos del curso       |
      | Contenidos temáticos      |
      | Duración estimada         |
      | Requisitos previos        |

  Scenario: Cierre de la vista de detalles
    Given el alumno se encuentra viendo los detalles del curso
    When hace clic en el botón "Cerrar" o fuera del panel
    Then el sistema cierra la vista de detalles y regresa a la lista

  Scenario: Detalles de curso inexistente
    Given el alumno intenta acceder a un curso con ID inválido
    When carga la vista de detalles
    Then el sistema muestra un mensaje de "curso no encontrado"
