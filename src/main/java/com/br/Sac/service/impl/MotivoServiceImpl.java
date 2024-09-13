package com.br.Sac.service.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.Motivo;
import com.br.Sac.model.dto.MotivoDTO;
import com.br.Sac.repository.MotivoRepository;
import com.br.Sac.service.MotivoService;
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
public class MotivoServiceImpl implements MotivoService {

    @Autowired
    private MotivoRepository motivoRepository;

    @Autowired
    private CustomObjectMapper<Motivo, MotivoDTO> motivoMapper;

    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {


        Motivo objeto = motivoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Motivo com ID " + idObjeto + " não foi encontrado!"));

        objeto.setStatus(!objeto.isStatus());
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        Motivo objetoAtualizado = motivoRepository.saveAndFlush(objeto);

        MotivoDTO objetoDTO = motivoMapper.converterParaDto(objetoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));
    }

    @Override
    public ResponseEntity<Object> cadastrar(MotivoDTO objeto) throws Exception {


        if (motivoRepository.existsByNome(objeto.getNome())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Motivo. Já existe outro Motivo com o mesmo nome."));
        }

        Motivo motivo = new Motivo();
        motivo.setNome(objeto.getNome());

        Motivo objetoCriado = motivoRepository.saveAndFlush(motivo);
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

        List<MotivoDTO> motivoDTOs = motivoMapper.converterParaListaDeDtos(motivoRepository.findAll());
        if(motivoDTOs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Motivos cadastrados no Sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(motivoDTOs));
    }


    @Override
    public ResponseEntity<Object> editar(Long idObjeto, MotivoDTO objeto) throws Exception {

        Motivo dadosDto = motivoMapper.converterParaEntidade(objeto);
        Motivo paraEditar = motivoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Motivo com ID " + idObjeto + " não foi encontrado!"));


        if (motivoRepository.existsByNome(objeto.getNome())){
            return  ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Motivo. Já existe outro Motivo com o mesmo nome"));
        }

        LocalDateTime dataHora = paraEditar.getDataCriacao();

        dadosDto.setDataCriacao(dataHora);
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        dadosDto.setStatus(dadosDto.isStatus());
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        Motivo objetoAtualizado = motivoRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(motivoMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        Motivo motivo = motivoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Motivo com ID " + idObjeto + " não foi encontrado!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(motivoMapper.converterParaDto(motivo)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {

        motivoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Motivos com ID " + idObjeto + " não foi encontrado!"));

        motivoRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O Motivo foi excluído com sucesso."));
    }
}
