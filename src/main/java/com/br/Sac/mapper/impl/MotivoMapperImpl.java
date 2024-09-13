package com.br.Sac.mapper.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.Motivo;
import com.br.Sac.model.dto.MotivoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MotivoMapperImpl implements CustomObjectMapper<Motivo, MotivoDTO> {
    @Override
    public MotivoDTO converterParaDto(Motivo entity) {

        MotivoDTO dto = new MotivoDTO();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setStatus(entity.isStatus());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());

        return dto;
    }

    @Override
    public Motivo converterParaEntidade(MotivoDTO dto) {

        Motivo motivo = new Motivo();

        motivo.setId(dto.getId());
        motivo.setNome(dto.getNome());
        motivo.setStatus(dto.isStatus());
        motivo.setDataCriacao(dto.getDataCriacao());
        motivo.setUltimaAtualizacao(dto.getUltimaAtualizacao());

        return motivo;
    }

    @Override
    public List<MotivoDTO> converterParaListaDeDtos(List<Motivo> entityList) {

        List<MotivoDTO> lista = new ArrayList<>();
        for (Motivo entity : entityList){
            lista.add(converterParaDto(entity));
        }
        return lista;
    }
}
