package com.br.Sac.repository;

import com.br.Sac.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SetorRepository  extends JpaRepository<Setor, Long> {

    public boolean existsByNome(String nome);

    public Optional<Setor> findByNome(String nome);
}
