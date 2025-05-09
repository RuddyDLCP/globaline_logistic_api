package com.globaline.logistic.api.globalie_logistic_api.service;

import com.globaline.logistic.api.globalie_logistic_api.model.Usuario;
import com.globaline.logistic.api.globalie_logistic_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UsuarioService {

    private static final Logger logger = Logger.getLogger(UsuarioService.class.getName());

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Verifica si existe un usuario con exactamente las mismas credenciales
     */
    public boolean existeUsuarioExacto(String email, String password, String nombre) {
        List<Usuario> usuarios = usuarioRepository.findAllByEmail(email);

        for (Usuario usuario : usuarios) {
            // Verificar si coinciden exactamente email, password y nombre
            if (usuario.getEmail().equals(email) &&
                    usuario.getPasswordHash().equals(password) &&
                    usuario.getNombre().equals(nombre)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Guarda un usuario en la base de datos
     */
    public Usuario saveUsuario(Usuario usuario) {
        try {
            logger.info("Guardando usuario: " + usuario.getEmail());
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            logger.severe("Error al guardar usuario: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Obtiene un usuario por su email
     */
    public Optional<Usuario> getUserByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}