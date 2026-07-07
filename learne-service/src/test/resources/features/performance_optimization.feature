# User Story: HU27 - Tiempo de carga optimizado para contenidos

Feature: Optimización del tiempo de carga del contenido del curso

  Como alumno,
  Quiero que los contenidos del curso carguen rápidamente
  Para tener una experiencia fluida al estudiar.

  Background:
    Given el alumno está inscrito en un curso asincrónico
    And tiene conexión a internet estable

  Scenario: Tiempo de carga aceptable para módulos del curso
    Given el alumno accede al curso "Fundamentos de Java"
    When el sistema carga la lista de módulos y contenidos
    Then la carga completa debe tardar menos de 2 segundos

  Scenario: Carga progresiva del contenido multimedia
    Given el contenido incluye videos y archivos PDF
    When el alumno abre un módulo con muchos recursos
    Then los recursos se cargan de forma progresiva o bajo demanda

  Scenario: Manejo de carga lenta por red deficiente
    Given la conexión del alumno es lenta
    When intenta acceder a los contenidos del curso
    Then el sistema muestra un indicador de carga y no se congela la interfaz
