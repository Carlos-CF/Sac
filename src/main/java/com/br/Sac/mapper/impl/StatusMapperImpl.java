package com.br.Sac.mapper.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.Status;
import com.br.Sac.model.dto.StatusDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatusMapperImpl implements CustomObjectMapper<Status, StatusDTO> {
    @Override
    public StatusDTO converterParaDto(Status entity) {

        StatusDTO dto = new StatusDTO();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());

        return dto;
    }

    @Override
    public Status converterParaEntidade(StatusDTO dto) {

        Status status = new Status();

        status.setId(dto.getId());
        status.setNome(dto.getNome());
        status.setDataCriacao(dto.getDataCriacao());
        status.setUltimaAtualizacao(dto.getUltimaAtualizacao());

        return status;
    }

    @Override
    public List<StatusDTO> converterParaListaDeDtos(List<Status> entityList) {

        List<StatusDTO> lista = new ArrayList<>();
        for (Status entity : entityList){
            lista.add(converterParaDto(entity));
        }
        return lista;
    }
}
