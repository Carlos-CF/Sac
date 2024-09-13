package com.br.Sac.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Motivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotBlank
    @Column
    private String nome;

    @Column
    private boolean status;

    @Column
    private LocalDateTime dataCriacao;

    @Column
    private LocalDateTime ultimaAtualizacao;

    public Motivo() {
    }

    public Motivo(Long id) {
        this.id = id;
    }

    public Motivo(Long id, String nome, boolean status) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.dataCriacao = LocalDateTime.now();
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    @PrePersist
    public void prePersist(){
        dataCriacao = LocalDateTime.now();
        status = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motivo motivo = (Motivo) o;
        return Objects.equals(id, motivo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
