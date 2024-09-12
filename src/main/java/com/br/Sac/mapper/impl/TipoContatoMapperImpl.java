package com.br.Sac.mapper.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.TipoContato;
import com.br.Sac.model.dto.TipoContatoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TipoContatoMapperImpl implements CustomObjectMapper<TipoContato, TipoContatoDTO> {
    @Override
    public TipoContatoDTO converterParaDto(TipoContato entity) {

        TipoContatoDTO dto = new TipoContatoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setStatus(entity.isStatus());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());

        return dto;
    }

    @Override
    public TipoContato converterParaEntidade(TipoContatoDTO dto) {

        TipoContato tipoContato = new TipoContato();
        tipoContato.setId(dto.getId());
        tipoContato.setNome(dto.getNome());
        tipoContato.setStatus(dto.isStatus());
        tipoContato.setDataCriacao(dto.getDataCriacao());
        tipoContato.setUltimaAtualizacao(dto.getUltimaAtualizacao());

        return tipoContato;
    }

    @Override
    public List<TipoContatoDTO> converterParaListaDeDtos(List<TipoContato> entityList) {

        List<TipoContatoDTO> lista = new ArrayList<>();
        for (TipoContato entity : entityList){
            lista.add(converterParaDto(entity));
        }
        return lista;
    }
}
