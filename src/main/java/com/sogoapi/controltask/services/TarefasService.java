package com.sogoapi.controltask.services;

import com.sogoapi.controltask.models.TarefasModel;
import com.sogoapi.controltask.repositories.TarefasRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TarefasService {

    final TarefasRepository tarefasRepository;

    public TarefasService(TarefasRepository tarefasRepository) {
        this.tarefasRepository = tarefasRepository;
    }

    @Transactional //usando transaction por boas práticas, garante hallback.
    public TarefasModel save(TarefasModel tarefasModel) {
        return tarefasRepository.save(tarefasModel);
    }

    public List<TarefasModel> retornarTodas() {
        return tarefasRepository.retornarTodas();
    }

    public Optional<List<TarefasModel>> retornarPendentes(){
        return tarefasRepository.retornarPendentes();
    }

    public Optional<TarefasModel> findByCodigo(UUID codigo) {
        return tarefasRepository.findById(codigo);
    }

    @Transactional
    public void delete(TarefasModel tarefasModel) {
        tarefasRepository.delete(tarefasModel);
    }
}
