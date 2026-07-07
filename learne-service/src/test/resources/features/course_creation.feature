# User Story: HU10 - Creación de un nuevo curso asincrónico

Feature: Creación de curso asincrónico

  Como profesor,
  Quiero poder crear un nuevo curso ingresando información básica
  Para publicarlo en la plataforma y compartirlo con los estudiantes.

  Background:
    Given el profesor ha iniciado sesión y accede a la sección de gestión de cursos

  Scenario: Creación exitosa de un nuevo curso
    Given el profesor llena el formulario con los siguientes datos:
      | Título del curso        | Fundamentos de Java     |
      | Descripción             | Curso introductorio      |
      | Categoría               | Programación             |
      | Nivel                   | Básico                   |
    When hace clic en "Crear curso"
    Then el sistema guarda el curso
    And lo muestra en su panel de gestión con estado "Publicado"

  Scenario: Intento de crear curso con campos vacíos
    Given el profesor accede al formulario sin llenar ningún campo
    When intenta hacer clic en "Crear curso"
    Then el sistema muestra errores de validación para todos los campos requeridos

  Scenario: Verificación del curso creado desde listado
    Given el curso fue creado con éxito
    When el profesor accede a la lista de sus cursos
    Then el curso aparece con su título, descripción y botón para editar
