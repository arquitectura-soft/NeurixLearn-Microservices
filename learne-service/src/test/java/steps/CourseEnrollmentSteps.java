package steps;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

import static org.junit.jupiter.api.Assertions.*;

public class CourseEnrollmentSteps {

    private boolean alumnoLogueado;
    private boolean listaCursosVisible;
    private boolean cursoSeleccionado;
    private boolean inscrito;
    private boolean yaInscrito;
    private boolean errorRed;
    private String mensaje;
    private boolean cursoEnListaActual;

    @Dado("el alumno ha iniciado sesión y navega por la lista de cursos disponibles")
    public void alumnoHaIniciadoSesionYVeLista() {
        alumnoLogueado = true;
        listaCursosVisible = true;
    }

    @Dado("el alumno selecciona el curso {string}")
    public void alumnoSeleccionaCurso(String nombreCurso) {
        assertTrue(alumnoLogueado && listaCursosVisible, "El alumno debe estar logueado y ver la lista");
        cursoSeleccionado = "Fundamentos de Java".equals(nombreCurso);
    }


    @Cuando("hace clic en el botón Inscribirse")
    public void hace_clic_en_el_boton_inscribirse() {
        if (errorRed) {
            mensaje = "error de red";
            inscrito = false;
            cursoEnListaActual = false;
        } else if (yaInscrito) {
            mensaje = "Ya estás inscrito en este curso";
            inscrito = false;
            cursoEnListaActual = true;
        } else if (cursoSeleccionado) {
            inscrito = true;
            mensaje = "confirmación";
            cursoEnListaActual = true;
        }
    }

    @Entonces("el sistema lo registra como estudiante del curso")
    public void sistemaRegistraEstudiante() {
        assertTrue(inscrito, "El alumno no fue inscrito");
    }

    @Entonces("le muestra un mensaje de confirmación")
    public void muestraMensajeConfirmacion() {
        assertEquals("confirmación", mensaje, "No se mostró el mensaje de confirmación");
    }

    @Entonces("el curso aparece en su lista de cursos actuales")
    public void cursoEnListaActual() {
        assertTrue(cursoEnListaActual, "El curso no aparece en la lista actual");
    }

    @Dado("el alumno ya está inscrito en {string}")
    public void alumnoYaInscrito(String nombreCurso) {
        yaInscrito = "Fundamentos de Java".equals(nombreCurso);
        cursoSeleccionado = true;
        inscrito = false;
        cursoEnListaActual = true;
    }

    @Cuando("accede nuevamente a la página del curso")
    public void accedeNuevamentePaginaCurso() {
        if (yaInscrito) {
            mensaje = "Ya estás inscrito en este curso";
        }
    }

    @Entonces("el sistema desactiva el botón de inscripción")
    public void sistemaDesactivaBoton() {
        assertTrue(yaInscrito, "El botón de inscripción no está desactivado");
    }

    @Entonces("muestra el mensaje {string}")
    public void muestraMensajeYaInscrito(String esperado) {
        assertEquals(esperado, mensaje, "El mensaje no coincide");
    }

    @Dado("el alumno pierde conexión a internet al intentar inscribirse")
    public void alumnoPierdeConexion() {
        errorRed = true;
        cursoSeleccionado = true;
    }

    @Entonces("el sistema muestra un mensaje de error de red")
    public void sistemaMuestraErrorRed() {
        assertEquals("error de red", mensaje, "No se mostró el mensaje de error de red");
    }
}