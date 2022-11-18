package cl.pinolabs.backend.web.security.service;

import cl.pinolabs.backend.model.persistence.enitty.Usuario;
import cl.pinolabs.backend.web.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.*;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario: " + username + " no ha sido encontrado en la base de datos"));

        return UserDetailsImpl.build(user);
    }

}