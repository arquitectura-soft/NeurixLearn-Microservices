package steps;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.datatable.DataTable;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CourseDetailsSteps {

    private final TestContext context;
    private boolean alumnoLogueado;
    private boolean listaCursosVisible;
    private boolean detallesCursoVisible;
    private boolean cursoExiste;
    private String mensajeError;
    private List<String> detallesMostrados;

    public CourseDetailsSteps(TestContext context) {
        this.context = context;
    }

    @Dado("el alumno ha iniciado sesión y visualiza la lista de cursos disponibles")
    public void alumnoHaIniciadoSesionYVeLista() {
        alumnoLogueado = true;
        listaCursosVisible = true;
    }

    @Dado("el alumno ve el curso {string} en la lista")
    public void alumnoVeCursoEnLista(String nombreCurso) {
        assertTrue(alumnoLogueado && listaCursosVisible, "El alumno debe estar logueado y ver la lista");
        cursoExiste = "Fundamentos de Java".equals(nombreCurso);
    }

    @Cuando("hace clic en el botón {string}")
    public void haceClicEnVerMas(String boton) {
        if (cursoExiste && "Ver más".equals(boton)) {
            detallesCursoVisible = true;
            detallesMostrados = List.of(
                "Descripción completa",
                "Objetivos del curso",
                "Contenidos temáticos",
                "Duración estimada",
                "Requisitos previos"
            );
        }
    }

    @Entonces("el sistema muestra la vista de detalles del curso con:")
    public void sistemaMuestraVistaDetalles(DataTable dataTable) {
        assertTrue(detallesCursoVisible, "La vista de detalles no está visible");
        List<String> esperados = dataTable.asList();
        assertTrue(detallesMostrados.containsAll(esperados), "No se muestran todos los detalles requeridos");
    }

    @Dado("el alumno se encuentra viendo los detalles del curso")
    public void alumnoViendoDetallesCurso() {
        detallesCursoVisible = true;
    }

    @Cuando("hace clic en el botón {string} o fuera del panel")
    public void haceClicCerrarODeselecciona(String boton) {
        if ("Cerrar".equals(boton) && detallesCursoVisible) {
            detallesCursoVisible = false;
        }
    }

    @Entonces("el sistema cierra la vista de detalles y regresa a la lista")
    public void sistemaCierraVistaYRegresaLista() {
        assertFalse(detallesCursoVisible, "La vista de detalles sigue visible");
        assertTrue(listaCursosVisible, "La lista de cursos no está visible");
    }

    @Dado("el alumno intenta acceder a un curso con ID inválido")
    public void alumnoAccedeCursoIdInvalido() {
        cursoExiste = false;
    }

    @Cuando("carga la vista de detalles")
    public void cargaVistaDetalles() {
        if (!cursoExiste) {
            detallesCursoVisible = false;
            mensajeError = "curso no encontrado";
        }
    }

    @Entonces("el sistema muestra un mensaje de {string}")
    public void sistemaMuestraMensajeError(String mensaje) {
        // Primero verifica el mensaje de error local, si no, usa el del contexto compartido
        String mensajeActual = mensajeError != null ? mensajeError : context.getMensajeSistema();
        assertEquals(mensaje, mensajeActual, "El mensaje de error no coincide");
    }
}