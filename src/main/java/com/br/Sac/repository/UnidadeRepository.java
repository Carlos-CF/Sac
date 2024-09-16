package com.br.Sac.repository;

import com.br.Sac.model.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    public boolean existsByNome(String nome);

    public Optional<Unidade> findByNome(String nome);
}
