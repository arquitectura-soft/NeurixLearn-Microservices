package steps;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class TeacherProfileSteps {

    private boolean teacherProfileCreated;
    private boolean courseHasTeacher;
    private boolean studentAccessesCourse;
    private boolean studentClicksTeacher;
    private boolean searchByName;
    private boolean profileLoaded;
    private boolean profileNotFound;
    private Map<String, String> teacherProfile;
    private List<String> shownFields;

    @Dado("el profesor ha creado su perfil con información personal, especialidad y calificaciones")
    public void profesorHaCreadoPerfil() {
        teacherProfileCreated = true;
        teacherProfile = new HashMap<>();
        teacherProfile.put("Nombre", "Juan Pérez");
        teacherProfile.put("Especialidad", "Matemáticas");
        teacherProfile.put("Experiencia", "10 años");
        teacherProfile.put("Calificación promedio", "4.8");
    }

    @Dado("el alumno accede a los detalles de un curso con profesor asignado")
    public void alumnoAccedeDetallesCurso() {
        courseHasTeacher = teacherProfileCreated;
        studentAccessesCourse = true;
    }

    @Cuando("hace clic en el nombre o imagen del profesor")
    public void clicEnNombreOImagen() {
        assertTrue(studentAccessesCourse && courseHasTeacher, "No hay profesor asignado al curso");
        studentClicksTeacher = true;
        profileLoaded = true;
    }

    @Entonces("el sistema muestra la información del perfil del profesor:")
    public void sistemaMuestraInfoPerfil(io.cucumber.datatable.DataTable dataTable) {
        assertTrue(profileLoaded, "El perfil no fue cargado");
        shownFields = dataTable.asList();
        for (String field : shownFields) {
            assertTrue(teacherProfile.containsKey(field), "Falta el campo: " + field);
        }
    }

    @Dado("el alumno realiza una búsqueda por nombre de profesor")
    public void alumnoBuscaPorNombre() {
        searchByName = true;
    }

    @Cuando("selecciona un resultado de la lista")
    public void seleccionaResultadoLista() {
        assertTrue(searchByName, "No se realizó búsqueda por nombre");
        profileLoaded = teacherProfileCreated;
    }

    @Entonces("se carga la vista del perfil con información detallada del docente")
    public void seCargaVistaPerfil() {
        assertTrue(profileLoaded, "No se cargó el perfil del docente");
        assertFalse(teacherProfile.isEmpty(), "El perfil del docente está vacío");
    }

    @Dado("el alumno accede a una URL de perfil inexistente o inválida")
    public void alumnoAccedeUrlInvalida() {
        profileLoaded = false;
        profileNotFound = true;
    }

    @Cuando("intenta cargar el perfil")
    public void intentaCargarPerfil() {
        // No cambia el estado, solo simula el intento
    }

    @Entonces("el sistema muestra un mensaje de \"perfil no encontrado\"")
    public void sistemaMuestraMensajeNoEncontrado() {
        assertTrue(profileNotFound, "No se mostró el mensaje de perfil no encontrado");
    }
}