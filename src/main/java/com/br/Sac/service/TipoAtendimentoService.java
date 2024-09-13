package com.br.Sac.service;

import com.br.Sac.model.dto.TipoAtendimentoDTO;
import com.br.Sac.service.util.CrudService;
import org.springframework.http.ResponseEntity;

public interface TipoAtendimentoService extends CrudService<TipoAtendimentoDTO> {

    ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception;
}
