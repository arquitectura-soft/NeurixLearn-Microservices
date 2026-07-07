package steps;

        import io.cucumber.java.es.Dado;
        import io.cucumber.java.es.Cuando;
        import io.cucumber.java.es.Entonces;

        import static org.junit.jupiter.api.Assertions.*;

        public class AccountVerificationSteps {

            private boolean emailServiceConfigured;
            private boolean userRegistered;
            private boolean userVerified;
            private boolean verificationEmailSent;
            private boolean verificationTokenGenerated;
            private boolean loginAttempted;
            private boolean canLogin;
            private String verificationToken;
            private String receivedToken;

            @Dado("el sistema tiene configurado un servicio de correo electrónico")
            public void sistemaConServicioCorreo() {
                emailServiceConfigured = true;
            }

            @Dado("un usuario se registra con un correo válido y contraseña segura")
            public void usuarioSeRegistra() {
                assertTrue(emailServiceConfigured, "El servicio de correo debe estar configurado");
                userRegistered = true;
                userVerified = false;
            }

            @Cuando("el usuario completa el formulario de registro")
            public void usuarioCompletaRegistro() {
                if (userRegistered && emailServiceConfigured) {
                    verificationToken = "token123";
                    verificationTokenGenerated = true;
                    verificationEmailSent = true;
                }
            }

            @Entonces("el sistema genera un token de verificación")
            public void sistemaGeneraToken() {
                assertTrue(verificationTokenGenerated, "No se generó el token de verificación");
            }

            @Entonces("envía un correo con el enlace de activación al correo ingresado")
            public void sistemaEnviaCorreo() {
                assertTrue(verificationEmailSent, "No se envió el correo de verificación");
            }

            @Dado("un usuario se registró pero no verificó su cuenta")
            public void usuarioNoVerificado() {
                userRegistered = true;
                userVerified = false;
                verificationTokenGenerated = true;
                verificationEmailSent = true;
            }

            @Cuando("intenta iniciar sesión con sus credenciales")
            public void usuarioIntentaLogin() {
                loginAttempted = true;
                canLogin = userVerified;
            }

            @Entonces("el sistema muestra un mensaje indicando que debe verificar su cuenta")
            public void sistemaMuestraMensajeNoVerificado() {
                assertFalse(canLogin, "El usuario no debería poder iniciar sesión sin verificar su cuenta");
            }

            @Dado("el usuario recibió un correo con un enlace de verificación válido")
            public void usuarioRecibioCorreoConEnlace() {
                verificationToken = "token123";
                receivedToken = "token123";
                userRegistered = true;
                userVerified = false;
            }

            @Cuando("accede al enlace proporcionado")
            public void usuarioAccedeEnlace() {
                if (verificationToken != null && verificationToken.equals(receivedToken)) {
                    userVerified = true;
                }
            }

            @Entonces("el sistema marca su cuenta como verificada")
            public void sistemaMarcaCuentaVerificada() {
                assertTrue(userVerified, "La cuenta no fue marcada como verificada");
            }

            @Entonces("le permite iniciar sesión normalmente")
            public void sistemaPermiteLogin() {
                canLogin = userVerified;
                assertTrue(canLogin, "El usuario verificado debería poder iniciar sesión");
            }
        }