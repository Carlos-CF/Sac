package com.br.Sac.repository;

import com.br.Sac.model.GrupoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoProdutoRepository extends JpaRepository<GrupoProduto, Long> {

    public boolean existsByCodigo(Long codigo);

    public boolean existsByNome(String nome);

    public boolean existsByCodigoAndIdNot(Long codigo, Long id);

    public boolean existsByNomeAndIdNot(String nome, Long id);
}
