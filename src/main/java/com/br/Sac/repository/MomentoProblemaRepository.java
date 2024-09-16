package com.br.Sac.repository;

import com.br.Sac.model.MomentoProblema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MomentoProblemaRepository extends JpaRepository<MomentoProblema, Long> {

    public boolean existsByNome(String nome);

    public Optional<MomentoProblema> findByNome(String nome);
}
