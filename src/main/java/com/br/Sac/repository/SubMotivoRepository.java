package com.br.Sac.repository;

import com.br.Sac.model.SubMotivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubMotivoRepository extends JpaRepository<SubMotivo, Long> {

    public boolean existsByNome(String nome);

    public Optional<SubMotivo> findByNome(String nome);

    public List<SubMotivo> findByMotivoId(Long motivoId);
}
