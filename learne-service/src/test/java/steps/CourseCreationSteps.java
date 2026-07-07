package steps;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

import static org.junit.jupiter.api.Assertions.*;

public class CourseCreationSteps {

    private boolean profesorLogueado;
    private boolean formularioLlenado;
    private boolean cursoGuardado;
    private boolean cursoPublicado;
    private boolean erroresValidacion;
    private String tituloCurso;
    private String descripcionCurso;
    private String categoriaCurso;
    private String nivelCurso;

    @Dado("el profesor ha iniciado sesión y accede a la sección de gestión de cursos")
    public void profesorHaIniciadoSesion() {
        profesorLogueado = true;
    }

    @Dado("el profesor llena el formulario con los siguientes datos:")
    public void profesorLlenaFormulario(io.cucumber.datatable.DataTable dataTable) {
        assertTrue(profesorLogueado, "El profesor debe estar logueado");
        var datos = dataTable.asMap(String.class, String.class);
        tituloCurso = datos.get("Título del curso");
        descripcionCurso = datos.get("Descripción");
        categoriaCurso = datos.get("Categoría");
        nivelCurso = datos.get("Nivel");
        formularioLlenado = true;
    }

    @Cuando("hace clic en \"Crear curso\"")
    public void haceClicCrearCurso() {
        if (formularioLlenado && tituloCurso != null && !tituloCurso.isEmpty()
                && descripcionCurso != null && !descripcionCurso.isEmpty()
                && categoriaCurso != null && !categoriaCurso.isEmpty()
                && nivelCurso != null && !nivelCurso.isEmpty()) {
            cursoGuardado = true;
            cursoPublicado = true;
            erroresValidacion = false;
        } else {
            cursoGuardado = false;
            cursoPublicado = false;
            erroresValidacion = true;
        }
    }

    @Entonces("el sistema guarda el curso")
    public void sistemaGuardaCurso() {
        assertTrue(cursoGuardado, "El curso no fue guardado");
    }

    @Entonces("lo muestra en su panel de gestión con estado \"Publicado\"")
    public void cursoPublicadoEnPanel() {
        assertTrue(cursoPublicado, "El curso no está publicado");
    }

    @Dado("el profesor accede al formulario sin llenar ningún campo")
    public void profesorFormularioVacio() {
        formularioLlenado = false;
        tituloCurso = "";
        descripcionCurso = "";
        categoriaCurso = "";
        nivelCurso = "";
    }

    @Cuando("intenta hacer clic en \"Crear curso\"")
    public void intentaCrearCursoCamposVacios() {
        haceClicCrearCurso();
    }

    @Entonces("el sistema muestra errores de validación para todos los campos requeridos")
    public void sistemaMuestraErroresValidacion() {
        assertTrue(erroresValidacion, "No se mostraron errores de validación");
    }

    @Dado("el curso fue creado con éxito")
    public void cursoCreadoConExito() {
        cursoGuardado = true;
        cursoPublicado = true;
        tituloCurso = "Fundamentos de Java";
        descripcionCurso = "Curso introductorio";
    }

    @Cuando("el profesor accede a la lista de sus cursos")
    public void profesorAccedeListaCursos() {
        // Simulación: no se requiere acción extra
    }

    @Entonces("el curso aparece con su título, descripción y botón para editar")
    public void cursoApareceEnListado() {
        assertEquals("Fundamentos de Java", tituloCurso, "El título del curso no coincide");
        assertEquals("Curso introductorio", descripcionCurso, "La descripción del curso no coincide");
        assertTrue(cursoGuardado && cursoPublicado, "El curso no está disponible en el listado");
    }
}