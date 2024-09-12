package com.br.Sac.service;

import com.br.Sac.model.dto.TipoContatoDTO;
import com.br.Sac.service.util.CrudService;
import org.springframework.http.ResponseEntity;

public interface TipoContatoService extends CrudService<TipoContatoDTO> {

    ResponseEntity <Object> mudarStatus(Long idObjeto) throws Exception;

}
