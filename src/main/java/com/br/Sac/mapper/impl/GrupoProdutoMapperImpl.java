package com.br.Sac.mapper.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.GrupoProduto;
import com.br.Sac.model.dto.GrupoProdutoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GrupoProdutoMapperImpl implements CustomObjectMapper<GrupoProduto, GrupoProdutoDTO> {

    @Override
    public GrupoProdutoDTO converterParaDto(GrupoProduto entity) {

        GrupoProdutoDTO dto = new GrupoProdutoDTO();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setNome(entity.getNome());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());

        return dto;
    }

    @Override
    public GrupoProduto converterParaEntidade(GrupoProdutoDTO dto) {

        GrupoProduto grupoProduto = new GrupoProduto();
        grupoProduto.setId(dto.getId());
        grupoProduto.setCodigo(dto.getCodigo());
        grupoProduto.setNome(dto.getNome());
        grupoProduto.setDataCriacao(dto.getDataCriacao());
        grupoProduto.setUltimaAtualizacao(dto.getUltimaAtualizacao());

        return grupoProduto;
    }

    @Override
    public List<GrupoProdutoDTO> converterParaListaDeDtos(List<GrupoProduto> entityList) {

        List<GrupoProdutoDTO> lista = new ArrayList<>();
        for (GrupoProduto entity : entityList){
            lista.add(converterParaDto(entity));
        }

        return lista;
    }
}
