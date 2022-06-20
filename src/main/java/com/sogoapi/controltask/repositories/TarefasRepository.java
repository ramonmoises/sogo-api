package com.sogoapi.controltask.repositories;

import com.sogoapi.controltask.models.TarefasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//extendendo JPA pq já tem vários recursos CRUD
@Repository

public interface TarefasRepository extends JpaRepository <TarefasModel, UUID>{

    @Query(value = "SELECT * FROM tb_tarefas WHERE status = 'PENDENTE'", nativeQuery = true)
    Optional<List<TarefasModel>> retornarPendentes();

    @Query(value = "SELECT * FROM tb_tarefas ORDER BY status DESC", nativeQuery = true)
    List<TarefasModel> retornarTodas();


}
