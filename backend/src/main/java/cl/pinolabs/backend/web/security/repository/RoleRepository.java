package cl.pinolabs.backend.web.security.repository;

import cl.pinolabs.backend.model.persistence.enitty.ERole;
import cl.pinolabs.backend.model.persistence.enitty.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}