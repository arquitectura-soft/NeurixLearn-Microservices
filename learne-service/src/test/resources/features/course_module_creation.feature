# User Story: HU19 - Organización del contenido por módulos o temas

Feature: Organización de contenidos en módulos

  Como profesor,
  Quiero organizar los contenidos del curso por módulos o temas
  Para estructurar mejor el aprendizaje de los estudiantes.

  Background:
    Given el profesor ha iniciado sesión y tiene un curso existente creado

  Scenario: Creación de un nuevo módulo en un curso
    Given el profesor accede al panel de edición del curso "Fundamentos de Java"
    When hace clic en "Agregar módulo"
    And completa el formulario con:
      | Título del módulo    | Introducción a Java   |
      | Descripción          | Conceptos básicos     |
    Then el sistema agrega el módulo a la estructura del curso
    And lo muestra en la lista de contenidos

  Scenario: Organización de varios módulos
    Given el profesor ha creado 3 módulos en un curso
    When accede a la vista de organización
    Then puede arrastrar y reordenar los módulos
    And el sistema guarda el nuevo orden automáticamente

  Scenario: Error al crear módulo sin título
    Given el profesor intenta agregar un módulo sin ingresar título
    When hace clic en "Guardar"
    Then el sistema muestra un mensaje de error indicando que el título es obligatorio
