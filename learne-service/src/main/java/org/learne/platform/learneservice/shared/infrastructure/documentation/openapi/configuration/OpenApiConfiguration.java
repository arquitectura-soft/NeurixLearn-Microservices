package org.learne.platform.learneservice.shared.infrastructure.documentation.openapi.configuration;

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
                        .title("NeurixLearn Platform - API de Gestión de Cursos")
                        .description("""
                                API REST para la gestión integral de la plataforma educativa NeurixLearn.
                                
                                Este servicio proporciona funcionalidades completas para la administración de cursos, contenido educativo, evaluaciones y seguimiento del progreso de los estudiantes.
                                
                                GESTIÓN DE CURSOS
                                Permite crear, actualizar, eliminar y consultar cursos. Incluye funcionalidades para listar cursos disponibles con filtros avanzados, obtener información detallada de cada curso y gestionar recomendaciones personalizadas basadas en el perfil del estudiante.
                                
                                CONTENIDO EDUCATIVO
                                Organiza el material didáctico en una estructura jerárquica de secciones y unidades. Soporta la carga y gestión de diferentes tipos de materiales: documentos PDF, videos, presentaciones y recursos descargables.
                                
                                SISTEMA DE EVALUACIÓN
                                Administra exámenes por curso con diferentes tipos de preguntas (opción múltiple, verdadero/falso, respuesta corta). Incluye corrección automática, banco de preguntas reutilizables y generación de reportes de desempeño.
                                
                                INSCRIPCIONES Y PROGRESO
                                Gestiona el proceso de inscripción de estudiantes a cursos. Realiza seguimiento del progreso individual, porcentaje de completitud por sección y tiempo dedicado al estudio.
                                
                                NOTAS Y APUNTES
                                Los estudiantes pueden crear notas personales asociadas a secciones específicas del curso, marcar contenido importante y organizar sus apuntes de estudio.
                                
                                PAGOS Y TRANSACCIONES
                                Procesa pagos para cursos premium mediante diferentes métodos de pago. Mantiene historial de transacciones y gestiona subscripciones activas.
                                
                                TUTORÍAS
                                Administra sesiones de tutoría grupales vinculadas a cursos específicos y tutorías individuales reservadas con instructores. Incluye gestión de horarios, disponibilidad y confirmaciones.
                                
                                AUTENTICACIÓN
                                Todos los endpoints requieren autenticación mediante token JWT. El token debe incluirse en el encabezado Authorization con el formato: Bearer {token}
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
