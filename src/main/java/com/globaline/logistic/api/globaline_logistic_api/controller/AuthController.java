package com.globaline.logistic.api.globaline_logistic_api.controller;

import com.globaline.logistic.api.globaline_logistic_api.model.Usuario;
import com.globaline.logistic.api.globaline_logistic_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());
    private static final String ADMIN_EMAIL = "admin@globaline.com";
    private static final String ADMIN_PASSWORD = "Xr9$Lk!27p#QzWd3@Fb6";

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            logger.info("Intento de login para: " + loginRequest.getEmail());

            // Determinar el rol basado en las credenciales
            String role = "CLIENT";
            if (ADMIN_EMAIL.equals(loginRequest.getEmail()) && ADMIN_PASSWORD.equals(loginRequest.getPassword())) {
                role = "ADMIN";
                logger.info("Usuario identificado como ADMIN");
            } else {
                logger.info("Usuario identificado como CLIENT");
            }

            // Verificar si ya existe un usuario con exactamente las mismas credenciales
            boolean existeExacto = usuarioService.existeUsuarioExacto(
                    loginRequest.getEmail(),
                    loginRequest.getPassword(),
                    loginRequest.getNombre()
            );

            // Si no existe exactamente igual, lo guardamos
            if (!existeExacto) {
                logger.info("Guardando nuevo usuario o actualización: " + loginRequest.getEmail());

                Usuario usuario = new Usuario(
                        loginRequest.getEmail(),
                        loginRequest.getPassword(),
                        loginRequest.getNombre()
                );

                usuarioService.saveUsuario(usuario);
            } else {
                logger.info("Usuario con credenciales exactamente iguales ya existe, no se guarda: " + loginRequest.getEmail());
            }

            // Preparar respuesta - siempre 200 OK
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("role", role);
            response.put("nombre", loginRequest.getNombre());
            response.put("email", loginRequest.getEmail());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.severe("Error en login: " + e.getMessage());
            e.printStackTrace();

            // Incluso en caso de error, devolvemos 200 con un mensaje
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error en el servidor: " + e.getMessage());
            response.put("role", "CLIENT"); // Por defecto, enviamos como cliente

            return ResponseEntity.ok(response);
        }
    }

    // Clase interna para manejar la solicitud de login
    public static class LoginRequest {
        private String email;
        private String password;
        private String nombre;

        // Getters y setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
    }
}