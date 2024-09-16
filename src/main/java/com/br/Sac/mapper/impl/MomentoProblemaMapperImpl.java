package com.br.Sac.mapper.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.MomentoProblema;
import com.br.Sac.model.dto.MomentoProblemaDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MomentoProblemaMapperImpl implements CustomObjectMapper<MomentoProblema, MomentoProblemaDTO> {
    @Override
    public MomentoProblemaDTO converterParaDto(MomentoProblema entity) {

        MomentoProblemaDTO dto = new MomentoProblemaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());

        return dto;
    }

    @Override
    public MomentoProblema converterParaEntidade(MomentoProblemaDTO dto) {

        MomentoProblema momentoProblema = new MomentoProblema();
        momentoProblema.setId(dto.getId());
        momentoProblema.setNome(dto.getNome());
        momentoProblema.setDataCriacao(dto.getDataCriacao());
        momentoProblema.setUltimaAtualizacao(dto.getUltimaAtualizacao());

        return momentoProblema;
    }

    @Override
    public List<MomentoProblemaDTO> converterParaListaDeDtos(List<MomentoProblema> entityList) {

        List<MomentoProblemaDTO> lista = new ArrayList<>();
        for(MomentoProblema entity : entityList){
            lista.add(converterParaDto(entity));
        }

        return lista;
    }
}
