package steps;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private String mensajeSistema;
    private String pantallaActual;
    private boolean redirigidoAlContenido;
    private final Map<String, Boolean> cursosEnCarrusel = new HashMap<>();

    public String getMensajeSistema() {
        return mensajeSistema;
    }

    public void setMensajeSistema(String mensajeSistema) {
        this.mensajeSistema = mensajeSistema;
    }

    public String getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(String pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    public void setCursoEnCarrusel(String curso, boolean presente) {
        cursosEnCarrusel.put(curso, presente);
    }

    public boolean isCursoEnCarrusel(String curso) {
        return cursosEnCarrusel.getOrDefault(curso, false);
    }

    public void setRedirigidoAlContenido(boolean redirigido) {
        this.redirigidoAlContenido = redirigido;
    }

    public boolean isRedirigidoAlContenido() {
        return redirigidoAlContenido;
    }
}