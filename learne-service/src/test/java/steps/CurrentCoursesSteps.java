package steps;

                import io.cucumber.java.es.*;
                import io.cucumber.datatable.DataTable;
                import static org.junit.jupiter.api.Assertions.*;
                import java.util.List;

                public class CurrentCoursesSteps {

                    private final TestContext context;
                    private boolean alumnoLogueado;
                    private boolean tieneCursosEnProgreso;
                    private boolean carruselVisible;
                    private List<String> detallesTarjeta;

                    public CurrentCoursesSteps(TestContext context) {
                        this.context = context;
                    }

                    @Dado("el alumno ha iniciado sesión")
                    public void alumnoHaIniciadoSesion() {
                        alumnoLogueado = true;
                    }

                    @Dado("está inscrito en al menos un curso en progreso")
                    public void alumnoInscritoEnCurso() {
                        tieneCursosEnProgreso = true;
                    }

                    @Dado("el alumno no está inscrito en ningún curso")
                    public void alumnoSinCursos() {
                        tieneCursosEnProgreso = false;
                    }

                    @Cuando("el alumno accede a la pantalla de inicio de cursos actuales")
                    public void accedePantallaInicio() {
                        // Simula la navegación a la pantalla de inicio de cursos actuales
                        if (!alumnoLogueado) {
                            fail("El alumno no ha iniciado sesión");
                        }
                        context.setPantallaActual("inicio_cursos_actuales");
                    }

                    @Cuando("el sistema carga su lista de cursos en progreso")
                    public void sistemaCargaCursos() {
                        if (tieneCursosEnProgreso) {
                            carruselVisible = true;
                            detallesTarjeta = List.of("Título", "Progreso actual", "Botón \"Continuar\"");
                        } else {
                            carruselVisible = false;
                            context.setMensajeSistema("Aún no tienes cursos en progreso");
                        }
                    }

                    @Entonces("se muestra un carrusel con tarjetas de curso incluyendo:")
                    public void seMuestraCarruselConTarjetas(DataTable dataTable) {
                        assertTrue(carruselVisible, "El carrusel no está visible");
                        List<String> esperados = dataTable.asList();
                        assertTrue(detallesTarjeta.containsAll(esperados), "Faltan detalles en la tarjeta");
                    }

                    @Dado("el curso {string} aparece en el carrusel")
                    public void cursoApareceEnCarrusel(String curso) {
                        assertTrue(carruselVisible, "El carrusel no está visible");
                        // Simula que el curso está presente en la lista
                        context.setCursoEnCarrusel(curso, true);
                    }

                    @Cuando("el alumno hace clic en {string}")
                    public void alumnoHaceClicEnContinuar(String boton) {
                        assertEquals("Continuar", boton, "El botón no es 'Continuar'");
                        // Simula la acción de continuar
                        context.setRedirigidoAlContenido(true);
                    }

                    @Entonces("es redirigido directamente al contenido del curso")
                    public void esRedirigidoAlContenido() {
                        assertTrue(context.isRedirigidoAlContenido(), "No se redirigió al contenido del curso");
                    }

                    @Entonces("el sistema muestra un mensaje: {string}")
                    public void sistemaMuestraMensajeSinCursos(String mensaje) {
                        assertEquals(mensaje, context.getMensajeSistema(), "El mensaje del sistema no coincide");
                    }
                }