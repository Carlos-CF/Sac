package com.br.Sac.repository;


import com.br.Sac.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    public boolean existsByNome(String nome);

    public Optional<Status> findByNome(String nome);
}
