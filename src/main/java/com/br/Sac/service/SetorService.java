package com.br.Sac.service;

import com.br.Sac.model.dto.SetorDTO;
import com.br.Sac.service.util.CrudService;
import org.springframework.http.ResponseEntity;

public interface SetorService extends CrudService<SetorDTO> {

    ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception;
}
