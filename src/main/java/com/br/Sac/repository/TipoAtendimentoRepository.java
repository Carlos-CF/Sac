package com.br.Sac.repository;

import com.br.Sac.model.TipoAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoAtendimentoRepository extends JpaRepository<TipoAtendimento, Long> {

    public boolean existsByNome(String nome);

    public Optional<TipoAtendimento> findByNome(String nome);
}
