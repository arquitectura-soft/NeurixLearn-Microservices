# User Story: TS02 - Verificación de cuenta por correo electrónico

Feature: Verificación de cuenta por correo electrónico tras el registro

  Como nuevo usuario,
  Quiero recibir un correo con un enlace de verificación
  Para poder activar mi cuenta antes de iniciar sesión.

  Background:
    Given el sistema tiene configurado un servicio de correo electrónico

  Scenario: Registro exitoso y envío de correo de verificación
    Given un usuario se registra con un correo válido y contraseña segura
    When el usuario completa el formulario de registro
    Then el sistema genera un token de verificación
    And envía un correo con el enlace de activación al correo ingresado

  Scenario: Acceso con cuenta no verificada
    Given un usuario se registró pero no verificó su cuenta
    When intenta iniciar sesión con sus credenciales
    Then el sistema muestra un mensaje indicando que debe verificar su cuenta

  Scenario: Verificación exitosa del correo
    Given el usuario recibió un correo con un enlace de verificación válido
    When accede al enlace proporcionado
    Then el sistema marca su cuenta como verificada
    And le permite iniciar sesión normalmente
