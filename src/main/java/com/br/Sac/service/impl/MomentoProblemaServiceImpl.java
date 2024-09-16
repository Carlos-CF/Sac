package com.br.Sac.service.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.MomentoProblema;
import com.br.Sac.model.dto.MomentoProblemaDTO;
import com.br.Sac.repository.MomentoProblemaRepository;
import com.br.Sac.service.MomentoProblemaService;
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
public class MomentoProblemaServiceImpl implements MomentoProblemaService {

    @Autowired
    private MomentoProblemaRepository momentoProblemaRepository;

    @Autowired
    private CustomObjectMapper<MomentoProblema, MomentoProblemaDTO> momentoProblemaMapper;

    @Override
    public ResponseEntity<Object> cadastrar(MomentoProblemaDTO objeto) throws Exception {

        if(momentoProblemaRepository.existsByNome(objeto.getNome())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Momento Problema. Já existe outro Momento Problema com o mesmo nome."));
        }

        MomentoProblema momentoProblema = new MomentoProblema();
        momentoProblema.setNome(objeto.getNome());

        MomentoProblema objetoCriado = momentoProblemaRepository.saveAndFlush(momentoProblema);
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

        List<MomentoProblemaDTO> momentoProblemaDTOs = momentoProblemaMapper.converterParaListaDeDtos(momentoProblemaRepository.findAll());
        if (momentoProblemaDTOs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Momento Problema cadastrados no Sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(momentoProblemaDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, MomentoProblemaDTO objeto) throws Exception {

        MomentoProblema dadosDto = momentoProblemaMapper.converterParaEntidade(objeto);
        MomentoProblema paraEditar = momentoProblemaRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Momento Problema com ID " + idObjeto + " não foi encontrado!"));


        if (momentoProblemaRepository.existsByNome(objeto.getNome())){
            return  ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Momento Problema. Já existe outro Momento Problema com o mesmo nome"));
        }

        LocalDateTime dataHora = paraEditar.getDataCriacao();

        dadosDto.setDataCriacao(dataHora);
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        MomentoProblema objetoAtualizado = momentoProblemaRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(momentoProblemaMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        MomentoProblema momentoProblema = momentoProblemaRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Momento Problema com ID " + idObjeto + " não foi encontrado!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(momentoProblemaMapper.converterParaDto(momentoProblema)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {

        momentoProblemaRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Momento Problema com ID " + idObjeto + " não foi encontrado!"));

        momentoProblemaRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O Momento Problema foi excluído com sucesso."));
    }
}
