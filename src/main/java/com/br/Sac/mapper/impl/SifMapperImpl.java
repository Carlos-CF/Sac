package com.br.Sac.mapper.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.Sif;
import com.br.Sac.model.dto.SifDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SifMapperImpl implements CustomObjectMapper<Sif, SifDTO> {
    @Override
    public SifDTO converterParaDto(Sif entity) {

        SifDTO dto = new SifDTO();
        dto.setId(entity.getId());
        dto.setNumero(entity.getNumero());
        dto.setNome(entity.getNome());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());

        return dto;
    }

    @Override
    public Sif converterParaEntidade(SifDTO dto) {

        Sif sif = new Sif();
        sif.setId(dto.getId());
        sif.setNumero(dto.getNumero());
        sif.setNome(dto.getNome());
        sif.setDataCriacao(dto.getDataCriacao());
        sif.setUltimaAtualizacao(dto.getUltimaAtualizacao());

        return sif;
    }

    @Override
    public List<SifDTO> converterParaListaDeDtos(List<Sif> entityList) {
       List<SifDTO> lista = new ArrayList<>();
       for (Sif entity : entityList){
           lista.add(converterParaDto(entity));
       }
       return lista;
    }
}
