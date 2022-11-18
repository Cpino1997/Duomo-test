package cl.pinolabs.backend.web.security.repository;

import cl.pinolabs.backend.model.persistence.enitty.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuario(String usuario);
    Boolean existsByUsuario(String usuario);
    Boolean existsByCorreo(String correo);
}