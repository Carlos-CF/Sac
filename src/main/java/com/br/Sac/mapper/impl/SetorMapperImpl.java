package com.br.Sac.mapper.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.Setor;
import com.br.Sac.model.dto.SetorDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SetorMapperImpl implements CustomObjectMapper<Setor, SetorDTO> {
    @Override
    public SetorDTO converterParaDto(Setor entity) {

        SetorDTO dto = new SetorDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setStatus(entity.isStatus());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());

        return dto;
    }

    @Override
    public Setor converterParaEntidade(SetorDTO dto) {

        Setor setor = new Setor();
        setor.setId(dto.getId());
        setor.setNome(dto.getNome());
        setor.setStatus(dto.isStatus());
        setor.setDataCriacao(dto.getDataCriacao());
        setor.setUltimaAtualizacao(dto.getUltimaAtualizacao());

        return setor;
    }

    @Override
    public List<SetorDTO> converterParaListaDeDtos(List<Setor> entityList) {

        List<SetorDTO> lista = new ArrayList<>();
        for (Setor entity : entityList){
            lista.add(converterParaDto(entity));
        }
        return lista;
    }
}
