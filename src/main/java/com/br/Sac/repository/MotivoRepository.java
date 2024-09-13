package com.br.Sac.repository;

import com.br.Sac.model.Motivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotivoRepository extends JpaRepository<Motivo, Long > {

    public boolean existsByNome(String nome);

    public Optional<Motivo> findByNome(String nome);
}
