package com.br.Sac.service.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.Motivo;
import com.br.Sac.model.SubMotivo;
import com.br.Sac.model.dto.SubMotivoDTO;
import com.br.Sac.repository.MotivoRepository;
import com.br.Sac.repository.SubMotivoRepository;
import com.br.Sac.service.SubMotivoService;
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
public class SubMotivoServiceImpl implements SubMotivoService {

    @Autowired
    private SubMotivoRepository subMotivoRepository;

    @Autowired
    private MotivoRepository motivoRepository;

    @Autowired
    private CustomObjectMapper<SubMotivo, SubMotivoDTO> subMotivoMapper;

    @Override
    public ResponseEntity<Object> listarPorMotivo(Long idObjeto) throws Exception {

        List<SubMotivoDTO> subMotivoDTOs = subMotivoMapper.converterParaListaDeDtos(subMotivoRepository.findByMotivoId(idObjeto));

        Motivo motivo = motivoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Motivo com ID " + idObjeto + " não foi encontrado!"));

        if(subMotivoDTOs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe SubMotivos cadastrados nesse Motivo"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(subMotivoDTOs));
    }

    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {

        SubMotivo objeto = subMotivoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O SubMotivo com ID "+ idObjeto + " não foi encontrado!"));

        objeto.setStatus(!objeto.isStatus());
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        SubMotivo objetoAtualizado = subMotivoRepository.saveAndFlush(objeto);

        SubMotivoDTO objetoDTO = subMotivoMapper.converterParaDto(objetoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));
    }

    @Override
    public ResponseEntity<Object> cadastrar(SubMotivoDTO objeto) throws Exception {

        if (subMotivoRepository.existsByNome(objeto.getNome())){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o SubMotivo. Já existe outro SubMotivo com o mesmo nome."));
        }

        SubMotivo subMotivo = new SubMotivo();
        subMotivo.setNome(objeto.getNome());
        subMotivo.setMotivo(objeto.getMotivo());

        SubMotivo objetoCriado = subMotivoRepository.saveAndFlush(subMotivo);
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

        List<SubMotivoDTO> subMotivoDTOs = subMotivoMapper.converterParaListaDeDtos(subMotivoRepository.findAll());
        if(subMotivoDTOs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe SubMotivo cadastrados no sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(subMotivoDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, SubMotivoDTO objeto) throws Exception {

        SubMotivo dadosDto = subMotivoMapper.converterParaEntidade(objeto);
        SubMotivo paraEditar = subMotivoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O SubMotivo com ID " + idObjeto + " não foi encontrado!"));

        if (subMotivoRepository.existsByNome(objeto.getNome())){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o SubMotivo. Já existe outro SubMotivo com o mesmo nome."));
        }

        LocalDateTime dataHora = paraEditar.getDataCriacao();

        dadosDto.setDataCriacao(dataHora);
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        dadosDto.setStatus(dadosDto.isStatus());
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        SubMotivo objetoAtualizado = subMotivoRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(subMotivoMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        SubMotivo subMotivo = subMotivoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O SubMotivo com ID " + idObjeto + " não foi encontrado!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(subMotivoMapper.converterParaDto(subMotivo)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {

        subMotivoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O SubMotivo com ID " + idObjeto + " não foi encontrado!"));

        subMotivoRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O SubMotivo foi excluído com sucesso."));
    }
}
