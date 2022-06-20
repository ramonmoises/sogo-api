package com.sogoapi.controltask.repositories;

import com.sogoapi.controltask.models.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<UsuariosModel, Integer> {

    public Optional<UsuariosModel> findByLogin(String login);

}
