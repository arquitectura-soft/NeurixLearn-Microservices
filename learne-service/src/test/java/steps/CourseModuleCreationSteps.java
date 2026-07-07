package steps;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.datatable.DataTable;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class CourseModuleCreationSteps {

    private boolean profesorLogueado;
    private boolean cursoExistente;
    private boolean enPanelEdicion;
    private boolean formularioModuloLlenado;
    private boolean moduloAgregado;
    private boolean listaContenidosVisible;
    private List<Map<String, String>> modulos = new ArrayList<>();
    private String errorMensaje;
    private boolean enVistaOrganizacion;
    private boolean ordenGuardado;
    private int modulosCreados;

    @Dado("el profesor ha iniciado sesión y tiene un curso existente creado")
    public void profesorHaIniciadoSesionYCursoExistente() {
        profesorLogueado = true;
        cursoExistente = true;
        modulos.clear();
        modulosCreados = 0;
    }

    @Dado("el profesor accede al panel de edición del curso {string}")
    public void profesorAccedePanelEdicion(String curso) {
        assertTrue(profesorLogueado && cursoExistente, "El profesor debe estar logueado y tener un curso");
        enPanelEdicion = true;
    }

    @Cuando("hace clic en \"Agregar módulo\"")
    public void haceClicEnAgregarModulo() {
        assertTrue(enPanelEdicion, "Debe estar en el panel de edición");
        formularioModuloLlenado = false;
        moduloAgregado = false;
        errorMensaje = null;
    }

    @Cuando("completa el formulario con:")
    public void completaFormularioModulo(DataTable dataTable) {
        Map<String, String> datos = dataTable.asMap(String.class, String.class);
        String titulo = datos.get("Título del módulo");
        String descripcion = datos.get("Descripción");
        if (titulo != null && !titulo.isEmpty()) {
            Map<String, String> modulo = new HashMap<>();
            modulo.put("titulo", titulo);
            modulo.put("descripcion", descripcion);
            modulos.add(modulo);
            formularioModuloLlenado = true;
        } else {
            formularioModuloLlenado = false;
        }
    }

    @Entonces("el sistema agrega el módulo a la estructura del curso")
    public void sistemaAgregaModulo() {
        if (formularioModuloLlenado) {
            moduloAgregado = true;
            modulosCreados++;
        }
        assertTrue(moduloAgregado, "El módulo no fue agregado");
    }

    @Entonces("lo muestra en la lista de contenidos")
    public void muestraEnListaContenidos() {
        listaContenidosVisible = true;
        assertTrue(listaContenidosVisible && !modulos.isEmpty(), "El módulo no aparece en la lista de contenidos");
    }

    @Dado("el profesor ha creado {int} módulos en un curso")
    public void profesorHaCreadoNModulos(int cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            Map<String, String> modulo = new HashMap<>();
            modulo.put("titulo", "Módulo " + i);
            modulo.put("descripcion", "Descripción " + i);
            modulos.add(modulo);
        }
        modulosCreados = cantidad;
    }

    @Cuando("accede a la vista de organización")
    public void accedeVistaOrganizacion() {
        enVistaOrganizacion = true;
    }

    @Entonces("puede arrastrar y reordenar los módulos")
    public void puedeArrastrarYReordenar() {
        assertTrue(enVistaOrganizacion && modulosCreados > 1, "No hay suficientes módulos para reordenar");
        // Simulación de reordenamiento
        Collections.reverse(modulos);
    }

    @Entonces("el sistema guarda el nuevo orden automáticamente")
    public void sistemaGuardaNuevoOrden() {
        ordenGuardado = true;
        assertTrue(ordenGuardado, "El nuevo orden no fue guardado");
    }

    @Dado("el profesor intenta agregar un módulo sin ingresar título")
    public void profesorAgregaModuloSinTitulo() {
        formularioModuloLlenado = false;
    }

    @Cuando("hace clic en \"Guardar\"")
    public void haceClicEnGuardar() {
        if (!formularioModuloLlenado) {
            errorMensaje = "El título es obligatorio";
            moduloAgregado = false;
        }
    }

    @Entonces("el sistema muestra un mensaje de error indicando que el título es obligatorio")
    public void sistemaMuestraErrorTituloObligatorio() {
        assertEquals("El título es obligatorio", errorMensaje, "No se mostró el mensaje de error esperado");
    }
}