package com.br.Sac.mapper.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.SubMotivo;
import com.br.Sac.model.dto.SubMotivoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubMotivoMapperImpl implements CustomObjectMapper<SubMotivo, SubMotivoDTO> {
    @Override
    public SubMotivoDTO converterParaDto(SubMotivo entity) {

        SubMotivoDTO dto = new SubMotivoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setStatus(entity.isStatus());
        dto.setMotivo(entity.getMotivo());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());

        return dto;
    }

    @Override
    public SubMotivo converterParaEntidade(SubMotivoDTO dto) {

        SubMotivo subMotivo = new SubMotivo();
        subMotivo.setId(dto.getId());
        subMotivo.setNome(dto.getNome());
        subMotivo.setStatus(dto.isStatus());
        subMotivo.setMotivo(dto.getMotivo());
        subMotivo.setDataCriacao(dto.getDataCriacao());
        subMotivo.setDataCriacao(dto.getUltimaAtualizacao());

        return subMotivo;
    }

    @Override
    public List<SubMotivoDTO> converterParaListaDeDtos(List<SubMotivo> entityList) {

        List<SubMotivoDTO> lista = new ArrayList<>();
        for (SubMotivo entity : entityList){
            lista.add(converterParaDto(entity));
        }

        return  lista;
    }
}
