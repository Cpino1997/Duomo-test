package cl.pinolabs.backend.web.security.repository;

import cl.pinolabs.backend.model.persistence.enitty.RefreshToken;
import cl.pinolabs.backend.model.persistence.enitty.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUsuario(Usuario usuario);
}
