package com.br.Sac.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Sif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Column
    private Long numero;

    @NotBlank
    @Column
    private String nome;

    @Column
    private LocalDateTime dataCriacao;

    @Column
    private LocalDateTime ultimaAtualizacao;


    public Sif() {
    }

    public Sif(Long id) {
        this.id = id;
    }

    public Sif(Long id, Long numero, String nome) {
        this.id = id;
        this.numero = numero;
        this.nome = nome;
        this.dataCriacao = LocalDateTime.now();
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sif sif = (Sif) o;
        return Objects.equals(id, sif.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
