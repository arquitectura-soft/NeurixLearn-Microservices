package org.learne.platform.profileservice.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI learnePlatformOpenApi() {
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("NeurixLearn Platform - API de Gestión de Usuarios")
                        .description("""
                                API REST para la gestión de usuarios, perfiles y autenticación en la plataforma NeurixLearn.
                                
                                Este servicio centraliza toda la funcionalidad relacionada con el registro, autenticación y administración de perfiles de usuarios, tanto estudiantes como instructores.
                                
                                AUTENTICACIÓN Y REGISTRO
                                Maneja el proceso completo de registro de nuevos usuarios con validación de datos. Implementa autenticación segura mediante credenciales (email o nombre de usuario con contraseña). Genera tokens JWT para autorizar acceso a los recursos protegidos del sistema.
                                
                                GESTIÓN DE PERFILES
                                Permite a los usuarios consultar y actualizar su información personal: nombre completo, dirección de correo electrónico, foto de perfil y biografía. Distingue entre dos tipos de usuario: Estudiante (type_user = 1) para alumnos que consumen contenido, y Profesor (type_user = 2) para instructores que crean y administran cursos.
                                
                                SISTEMA DE PLANES
                                Administra dos niveles de membresía. El Plan Gratuito (type_plan = 1) ofrece acceso a cursos básicos con funcionalidades limitadas. El Plan Premium (type_plan = 2) proporciona acceso ilimitado a todos los cursos, certificados verificados, soporte prioritario y sesiones de tutoría individual.
                                
                                VERIFICACIÓN DE CUENTA
                                Al completar el registro, el sistema envía automáticamente un correo de bienvenida al usuario. Este proceso se realiza de forma asíncrona mediante integración con Apache Kafka, publicando un evento que el servicio de notificaciones procesa para enviar el email correspondiente.
                                
                                BÚSQUEDA Y CONSULTAS
                                Ofrece endpoints para buscar usuarios por nombre o dirección de correo electrónico. Permite filtrar resultados por tipo de usuario y obtener listados de profesores disponibles con sus perfiles públicos.
                                
                                SEGURIDAD
                                Implementa encriptación de contraseñas mediante el algoritmo BCrypt. Valida todos los datos de entrada para prevenir inyecciones SQL y otros vectores de ataque. Aplica rate limiting en endpoints sensibles para proteger contra ataques de fuerza bruta.
                                
                                INTEGRACIÓN CON SERVICIOS
                                Al registrarse un nuevo usuario, el sistema publica un evento en el topic de Kafka 'user.created' que el servicio de notificaciones consume para enviar emails de bienvenida personalizados.
                                
                                AUTENTICACIÓN
                                Los endpoints requieren autenticación mediante token JWT. El token debe incluirse en el encabezado Authorization con el formato: Bearer {token}. Para obtener un token, utilice el endpoint de login con credenciales válidas.
                                """)
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo NeurixLearn")
                                .email("soporte@neurixlearn.com")
                                .url("https://www.neurixlearn.com"))
                        .license(new License()
                                .name("Apache License 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación completa de NeurixLearn Platform")
                        .url("https://docs.neurixlearn.com"));
        return openApi;
    }
}
