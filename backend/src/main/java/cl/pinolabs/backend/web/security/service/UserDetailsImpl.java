package cl.pinolabs.backend.web.security.service;


import cl.pinolabs.backend.model.persistence.enitty.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String usuario;
    private String correo;
    @JsonIgnore
    private String contraseña;
    private Collection<? extends GrantedAuthority> authorities;
    public UserDetailsImpl(Long id, String usuario, String correo, String contraseña, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.usuario = usuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.authorities = authorities;
    }
    public static UserDetailsImpl build(Usuario user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                user.getId(),
                user.getUsuario(),
                user.getCorreo(),
                user.getPassword(),
                authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    public Long getId() {
        return id;
    }
    public String getEmail() {
        return correo;
    }
    @Override
    public String getPassword() {
        return contraseña;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}