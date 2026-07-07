package steps;

        import io.cucumber.java.es.Dado;
        import io.cucumber.java.es.Cuando;
        import io.cucumber.java.es.Entonces;
        import java.util.*;
        import static org.junit.jupiter.api.Assertions.*;

        public class CourseRecommendationsSteps {

            private final TestContext context;
            private boolean alumnoLogueado;
            private boolean enInicio;
            private boolean historialAnalizado;
            private boolean interesesAnalizados;
            private boolean recomendacionesGeneradas;
            private boolean mostrarCarrusel;
            private List<Map<String, String>> cursosRecomendados = new ArrayList<>();
            private boolean interesesProgramacion;
            private boolean alumnoNuevo;

            // Constructor sin argumentos requerido por Cucumber
            public CourseRecommendationsSteps(TestContext context) {
                this.context = context;
            }

            @Dado("el alumno ha iniciado sesión en la plataforma")
            public void alumnoHaIniciadoSesion() {
                alumnoLogueado = true;
                alumnoNuevo = false;
                interesesProgramacion = false;
                cursosRecomendados.clear();
                context.setMensajeSistema(null);
            }

            @Dado("el alumno accede a la pantalla de inicio")
            public void alumnoAccedeInicio() {
                enInicio = true;
            }

            @Cuando("el sistema analiza su historial de cursos e intereses")
            public void sistemaAnalizaHistorialEIntereses() {
                historialAnalizado = true;
                interesesAnalizados = true;
                if (!alumnoNuevo) {
                    recomendacionesGeneradas = true;
                    mostrarCarrusel = true;
                    Map<String, String> curso = new HashMap<>();
                    curso.put("titulo", "Curso de Java");
                    curso.put("miniatura", "java.png");
                    curso.put("boton", "Ver más");
                    cursosRecomendados.add(curso);
                }
            }

            @Entonces("se muestra un carrusel de cursos recomendados")
            public void se_muestra_un_carrusel_de_cursos_recomendados() {
                assertTrue(mostrarCarrusel, "No se muestra el carrusel de cursos recomendados");
                assertFalse(cursosRecomendados.isEmpty(), "No hay cursos recomendados en el carrusel");
            }

            @Entonces("cada curso incluye: título, miniatura, y botón {string}")
            public void cada_curso_incluye_título_miniatura_y_botón(String boton) {
                for (var curso : cursosRecomendados) {
                    assertTrue(curso.containsKey("titulo"), "El curso no tiene título");
                    assertTrue(curso.containsKey("miniatura"), "El curso no tiene miniatura");
                    assertEquals(boton, curso.get("boton"), "El botón no coincide");
                }
            }


            @Dado("el alumno ha mostrado interés en programación")
            public void alumnoInteresProgramacion() {
                interesesProgramacion = true;
            }

            @Cuando("el sistema genera las recomendaciones")
            public void sistemaGeneraRecomendaciones() {
                recomendacionesGeneradas = true;
                cursosRecomendados.clear();
                if (interesesProgramacion) {
                    Map<String, String> curso = new HashMap<>();
                    curso.put("titulo", "Introducción a la Programación");
                    curso.put("miniatura", "prog.png");
                    curso.put("boton", "Ver más");
                    cursosRecomendados.add(curso);
                }
            }

            @Entonces("aparecen cursos de esa categoría en las sugerencias")
            public void aparecen_cursos_de_esa_categoría_en_las_sugerencias() {
                assertFalse(cursosRecomendados.isEmpty(), "No hay cursos recomendados");
                boolean encontrado = cursosRecomendados.stream()
                        .anyMatch(curso -> curso.get("titulo").toLowerCase().contains("programación"));
                assertTrue(encontrado, "No aparecen cursos de la categoría en las sugerencias");
            }

            @Dado("el alumno es nuevo y aún no ha interactuado con cursos")
            public void alumnoEsNuevo() {
                alumnoNuevo = true;
                cursosRecomendados.clear();
            }

            @Cuando("accede a la pantalla de inicio")
            public void accedePantallaInicio() {
                enInicio = true;
                if (alumnoNuevo) {
                    recomendacionesGeneradas = false;
                    mostrarCarrusel = false;
                    context.setMensajeSistema("Aún no hay recomendaciones disponibles");
                }
            }


            @Entonces("el sistema muestra un mensaje de error {string}")
            public void sistemaMuestraMensajeError(String mensajeEsperado) {
                assertFalse(mostrarCarrusel, "El carrusel no debería mostrarse para un alumno nuevo");
                assertEquals(mensajeEsperado, context.getMensajeSistema(),
                        "El mensaje del sistema no coincide con lo esperado");
            }

        }