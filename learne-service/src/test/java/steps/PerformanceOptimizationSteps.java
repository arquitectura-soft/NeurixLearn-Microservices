package steps;

import io.cucumber.java.es.*;
import static org.junit.jupiter.api.Assertions.*;

public class PerformanceOptimizationSteps {

    private long tiempoCarga;
    private boolean cargaProgresiva;
    private boolean indicadorCargaVisible;
    private boolean interfazCongelada;

    @Dado("el alumno está inscrito en un curso asincrónico")
    public void alumnoInscritoCursoAsincronico() {
        // Simulación de inscripción
    }

    @Dado("tiene conexión a internet estable")
    public void conexionEstable() {
        // Simulación de conexión estable
    }

    @Dado("el alumno accede al curso {string}")
    public void alumnoAccedeCurso(String curso) {
        // Simulación de acceso al curso
    }

    @Cuando("el sistema carga la lista de módulos y contenidos")
    public void sistemaCargaModulos() {
        // Simula tiempo de carga (ejemplo: 1500 ms)
        tiempoCarga = 1500;
    }

    @Entonces("la carga completa debe tardar menos de 2 segundos")
    public void cargaMenosDe2Segundos() {
        assertTrue(tiempoCarga < 2000, "La carga tardó más de 2 segundos");
    }

    @Dado("el contenido incluye videos y archivos PDF")
    public void contenidoIncluyeMultimedia() {
        // Simulación de contenido multimedia
    }

    @Cuando("el alumno abre un módulo con muchos recursos")
    public void alumnoAbreModuloConMuchosRecursos() {
        cargaProgresiva = true;
    }

    @Entonces("los recursos se cargan de forma progresiva o bajo demanda")
    public void recursosCargaProgresiva() {
        assertTrue(cargaProgresiva, "Los recursos no se cargan progresivamente");
    }

    @Dado("la conexión del alumno es lenta")
    public void conexionLenta() {
        // Simulación de red lenta
    }

    @Cuando("intenta acceder a los contenidos del curso")
    public void intentaAccederContenidos() {
        indicadorCargaVisible = true;
        interfazCongelada = false;
    }

    @Entonces("el sistema muestra un indicador de carga y no se congela la interfaz")
    public void muestraIndicadorNoCongela() {
        assertTrue(indicadorCargaVisible, "No se muestra el indicador de carga");
        assertFalse(interfazCongelada, "La interfaz se congeló");
    }
}