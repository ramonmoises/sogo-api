package com.sogoapi.controltask.services;

import com.sogoapi.controltask.data.DetalheUsuariosData;
import com.sogoapi.controltask.models.UsuariosModel;
import com.sogoapi.controltask.repositories.UsuariosRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUsuariosServiceImpl implements UserDetailsService {

    private final UsuariosRepository repository;

    public DetalheUsuariosServiceImpl(UsuariosRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuariosModel> usuario = repository.findByLogin(username);
        if (usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado.");
        }

        return new DetalheUsuariosData(usuario);
    }
}
