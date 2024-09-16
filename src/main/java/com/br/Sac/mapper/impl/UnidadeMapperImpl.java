package com.br.Sac.mapper.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.Unidade;
import com.br.Sac.model.dto.UnidadeDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UnidadeMapperImpl implements CustomObjectMapper<Unidade, UnidadeDTO> {

    @Override
    public UnidadeDTO converterParaDto(Unidade entity) {

        UnidadeDTO dto = new UnidadeDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());

        return dto;
    }

    @Override
    public Unidade converterParaEntidade(UnidadeDTO dto) {

        Unidade unidade = new Unidade();
        unidade.setId(dto.getId());
        unidade.setNome(dto.getNome());
        unidade.setDataCriacao(dto.getDataCriacao());
        unidade.setUltimaAtualizacao(dto.getUltimaAtualizacao());

        return unidade;
    }

    @Override
    public List<UnidadeDTO> converterParaListaDeDtos(List<Unidade> entityList) {

        List<UnidadeDTO> lista = new ArrayList<>();
        for (Unidade entity : entityList){
            lista.add(converterParaDto(entity));
        }
        return lista;
    }
}
