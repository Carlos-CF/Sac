package com.br.Sac.service;

import com.br.Sac.model.dto.MotivoDTO;
import com.br.Sac.service.util.CrudService;
import org.springframework.http.ResponseEntity;

public interface MotivoService extends CrudService<MotivoDTO> {

    ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception;
}
