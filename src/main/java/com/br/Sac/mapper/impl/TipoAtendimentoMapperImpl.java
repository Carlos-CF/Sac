package com.br.Sac.mapper.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.TipoAtendimento;
import com.br.Sac.model.dto.TipoAtendimentoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TipoAtendimentoMapperImpl implements CustomObjectMapper<TipoAtendimento, TipoAtendimentoDTO> {
    @Override
    public TipoAtendimentoDTO converterParaDto(TipoAtendimento entity) {

        TipoAtendimentoDTO dto = new TipoAtendimentoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setStatus(entity.isStatus());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());

        return dto;
    }

    @Override
    public TipoAtendimento converterParaEntidade(TipoAtendimentoDTO dto) {

        TipoAtendimento tipoAtendimento = new TipoAtendimento();
        tipoAtendimento.setId(dto.getId());
        tipoAtendimento.setNome(dto.getNome());
        tipoAtendimento.setStatus(dto.isStatus());
        tipoAtendimento.setDataCriacao(dto.getDataCriacao());
        tipoAtendimento.setUltimaAtualizacao(dto.getUltimaAtualizacao());

        return tipoAtendimento;
    }

    @Override
    public List<TipoAtendimentoDTO> converterParaListaDeDtos(List<TipoAtendimento> entityList) {

        List<TipoAtendimentoDTO> lista = new ArrayList<>();
        for (TipoAtendimento entity : entityList){
            lista.add(converterParaDto(entity));
        }
        return lista;
    }
}
