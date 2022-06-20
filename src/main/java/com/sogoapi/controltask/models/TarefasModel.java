package com.sogoapi.controltask.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_TAREFAS")
public class TarefasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID codigo;

    @Column(nullable = false, unique = true, length = 30)
    private String titulo;

    @Column(nullable = false, length = 15)
    private String frequencia;

    @Column(nullable = false, length = 10)
    private String prioridade;

    @Column(nullable = false, length = 15)
    private String status;

    @Column(nullable = false)
    private int duracao;

//    @Column(nullable = false)
//    private LocalDateTime dataRegistro;


    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

//    public LocalDateTime getDataRegistro() {
//        return dataRegistro;
//    }
//
//    public void setDataRegistro(LocalDateTime dataRegistro) {
//        this.dataRegistro = dataRegistro;
//    }
}
