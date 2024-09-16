package com.br.Sac.repository;

import com.br.Sac.model.Sif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SifRepository extends JpaRepository<Sif, Long> {

    public boolean existsByNumero(Long numero);

    public boolean existsByNome(String nome);
}
