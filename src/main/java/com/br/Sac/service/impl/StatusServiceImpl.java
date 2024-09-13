package com.br.Sac.service.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.Status;
import com.br.Sac.model.dto.StatusDTO;
import com.br.Sac.repository.StatusRepository;
import com.br.Sac.service.StatusService;
import com.br.Sac.service.util.ApiResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CustomObjectMapper<Status, StatusDTO> statusMapper;

    @Override
    public ResponseEntity<Object> cadastrar(StatusDTO objeto) throws Exception {

      if(statusRepository.existsByNome(objeto.getNome())) {
          return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Status. Já existe outro Status com o mesmo nome."));
      }

          Status status = new Status();
          status.setNome(objeto.getNome());

          Status objetoCriado = statusRepository.saveAndFlush(status);
          return ResponseEntity.created(
                          ServletUriComponentsBuilder
                                  .fromCurrentContextPath()
                                  .path("/{id}")
                                  .buildAndExpand(objetoCriado.getId())
                                  .toUri())
                  .build();

      }


    @Override
    public ResponseEntity<Object> listar() throws Exception {

        List<StatusDTO> statusDTOs = statusMapper.converterParaListaDeDtos(statusRepository.findAll());
        if (statusDTOs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Status cadastrados no Sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(statusDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, StatusDTO objeto) throws Exception {

        Status dadosDto = statusMapper.converterParaEntidade(objeto);
        Status paraEditar = statusRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Status com ID " + idObjeto + " não foi encontrado!"));


        if (statusRepository.existsByNome(objeto.getNome())){
            return  ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Status. Já existe outro Status com o mesmo nome"));
        }

        LocalDateTime dataHora = paraEditar.getDataCriacao();

        dadosDto.setDataCriacao(dataHora);
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        Status objetoAtualizado = statusRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(statusMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        Status status = statusRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Status com ID " + idObjeto + " não foi encontrado!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(statusMapper.converterParaDto(status)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {

        statusRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Status com ID " + idObjeto + " não foi encontrado!"));

        statusRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O Status foi excluído com sucesso."));
    }
}
