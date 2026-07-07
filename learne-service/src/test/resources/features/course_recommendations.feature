# User Story: HU18 - Recomendaciones de cursos

Feature: Recomendación de cursos personalizados

  Como alumno,
  Quiero ver cursos recomendados en la pantalla de inicio
  Para descubrir nuevas opciones de aprendizaje que se ajusten a mis intereses.

  Background:
    Given el alumno ha iniciado sesión en la plataforma

  Scenario: Visualización de cursos recomendados en el inicio
    Given el alumno accede a la pantalla de inicio
    When el sistema analiza su historial de cursos e intereses
    Then se muestra un carrusel de cursos recomendados
    And cada curso incluye: título, miniatura, y botón "Ver más"

  Scenario: Recomendación personalizada según intereses
    Given el alumno ha mostrado interés en programación
    When el sistema genera las recomendaciones
    Then aparecen cursos de esa categoría en las sugerencias

  Scenario: Sin recomendaciones disponibles
    Given el alumno es nuevo y aún no ha interactuado con cursos
    When accede a la pantalla de inicio
    Then el sistema muestra un mensaje de "Aún no hay recomendaciones disponibles"
