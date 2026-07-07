# User Story: HU23 - Cursos actuales en la pantalla de inicio

Feature: Visualización de cursos en progreso en la pantalla de inicio

  Como alumno,
  Quiero ver mis cursos actuales directamente en la pantalla de inicio
  Para acceder rápidamente a los que ya estoy inscrito.

  Background:
    Given el alumno ha iniciado sesión
    And está inscrito en al menos un curso en progreso

  Scenario: Visualización de cursos actuales en carrusel
    Given el alumno accede a la pantalla de inicio
    When el sistema carga su lista de cursos en progreso
    Then se muestra un carrusel con tarjetas de curso incluyendo:
      | Título             |
      | Progreso actual    |
      | Botón "Continuar"  |

Scenario: Continuar curso desde la tarjeta
  Given el alumno ha iniciado sesión
  And está inscrito en al menos un curso en progreso
  When el sistema carga su lista de cursos en progreso
  And el curso "Fundamentos de Java" aparece en el carrusel
  When el alumno hace clic en "Continuar"
  Then es redirigido directamente al contenido del curso


Scenario: Sin cursos en progreso
  Given el alumno no está inscrito en ningún curso
  When el alumno accede a la pantalla de inicio
  And el sistema carga su lista de cursos en progreso
  Then el sistema muestra un mensaje: "Aún no tienes cursos en progreso"