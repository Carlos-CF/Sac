package com.br.Sac.mapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomObjectMapper<T, R> {

    R converterParaDto(T entity);

    T converterParaEntidade(R dto);

    List<R> converterParaListaDeDtos(List<T> entityList);
}
