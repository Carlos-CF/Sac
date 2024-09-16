package com.br.Sac.service;

import com.br.Sac.model.dto.SubMotivoDTO;
import com.br.Sac.service.util.CrudService;
import org.springframework.http.ResponseEntity;

public interface SubMotivoService extends CrudService<SubMotivoDTO> {

     ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception;

     ResponseEntity<Object> listarPorMotivo(Long idObjeto) throws Exception;
}
