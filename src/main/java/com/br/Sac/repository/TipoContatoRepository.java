package com.br.Sac.repository;

import com.br.Sac.model.TipoContato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoContatoRepository extends JpaRepository<TipoContato, Long> {

    public boolean existsByNome(String nome);

    public Optional<TipoContato> findByNome(String nome);
}
