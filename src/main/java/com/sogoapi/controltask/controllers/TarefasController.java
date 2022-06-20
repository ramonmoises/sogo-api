package com.sogoapi.controltask.controllers;

import com.sogoapi.controltask.dtos.TarefasDto;
import com.sogoapi.controltask.models.TarefasModel;
import com.sogoapi.controltask.services.TarefasService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/tarefas-sogo")
public class TarefasController {

    final TarefasService tarefasService;

    public TarefasController(TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }


    @PostMapping //Post - salvando dados da tarefa
    public ResponseEntity<Object> saveTarefas(@RequestBody @Valid TarefasDto tarefasDto){
        var tarefasModel = new TarefasModel();
        BeanUtils.copyProperties(tarefasDto, tarefasModel); //convertendo o dto em model para salvar
        //tarefasModel.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefasService.save(tarefasModel));
    }

    @GetMapping // Get - retornando todas as tarefas existentes no banco
    public ResponseEntity<List<TarefasModel>> getAllTarefasModel(){
        return ResponseEntity.status(HttpStatus.OK).body(tarefasService.retornarTodas());
    }

    @GetMapping("/{codigo}") // Retornando uma tarefa específica através do código(gerado automaticamente no insert)
    public ResponseEntity<Object> getOneTarefas(@PathVariable(value = "codigo")UUID codigo){
        Optional<TarefasModel> tarefasModelOptional = tarefasService.findByCodigo(codigo);
        if (!tarefasModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tarefasModelOptional.get());
    }

    @DeleteMapping("/{codigo}") // Deletando uma tarefa específica através do código(gerado automaticamente no insert)
    public ResponseEntity<Object> deleteTarefas(@PathVariable(value = "codigo")UUID codigo){
        Optional<TarefasModel> tarefasModelOptional = tarefasService.findByCodigo(codigo);
        if (!tarefasModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada.");
        }
        tarefasService.delete(tarefasModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tarefa deletada com sucesso.");
    }

    @PutMapping("/{codigo}") //Put - método para atualizar dados
    public ResponseEntity<Object> updateTarefas(@PathVariable(value = "codigo") UUID codigo,
                                                @RequestBody @Valid TarefasDto tarefasDto){
        Optional<TarefasModel> tarefasModelOptional = tarefasService.findByCodigo(codigo);
        if (!tarefasModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada.");
        }
        var tarefasModel = tarefasModelOptional.get();
        tarefasModel.setTitulo(tarefasDto.getTitulo());
        tarefasModel.setFrequencia(tarefasDto.getFrequencia());
        tarefasModel.setPrioridade(tarefasDto.getPrioridade());
        tarefasModel.setStatus(tarefasDto.getStatus());
        tarefasModel.setDuracao(tarefasDto.getDuracao());
        return ResponseEntity.status(HttpStatus.OK).body(tarefasService.save(tarefasModel));
    }

    @GetMapping("/pendentes") // Retornando lista das tarefas com status PENDENTE
    public ResponseEntity<Object> getRetornarPendentes(){
        Optional<List<TarefasModel>> tarefasPendentes = tarefasService.retornarPendentes();
        if (!tarefasPendentes.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma tarefa com status 'PENDENTE'.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tarefasPendentes.get());
    }





















}
