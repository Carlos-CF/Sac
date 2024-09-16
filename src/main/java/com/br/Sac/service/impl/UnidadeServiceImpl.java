package com.br.Sac.service.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.Unidade;
import com.br.Sac.model.dto.UnidadeDTO;
import com.br.Sac.repository.UnidadeRepository;
import com.br.Sac.service.UnidadeService;
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
public class UnidadeServiceImpl implements UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private CustomObjectMapper<Unidade, UnidadeDTO> unidadeMapper;

    @Override
    public ResponseEntity<Object> cadastrar(UnidadeDTO objeto) throws Exception {

        if (unidadeRepository.existsByNome(objeto.getNome())){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar a Unidade. Já existe outra Unidade com o mesmo nome."));
        }

        Unidade unidade = new Unidade();
        unidade.setNome(objeto.getNome());

        Unidade objetoCriado = unidadeRepository.saveAndFlush(unidade);
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

        List<UnidadeDTO> unidadeDTOs = unidadeMapper.converterParaListaDeDtos(unidadeRepository.findAll());
        if (unidadeDTOs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Status cadastrados no Sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(unidadeDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, UnidadeDTO objeto) throws Exception {

        Unidade dadosDto = unidadeMapper.converterParaEntidade(objeto);
        Unidade paraEditar = unidadeRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("A unidade com ID " + idObjeto + " não foi encontrado!"));

        if (unidadeRepository.existsByNome(objeto.getNome())){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar Unidade. Já existe outra Unidade com o mesmo nome"));
        }

        LocalDateTime dataHora = paraEditar.getDataCriacao();

        dadosDto.setDataCriacao(dataHora);
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(dadosDto, paraEditar,"id");
        Unidade objetoAtualizado = unidadeRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(unidadeMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        Unidade unidade = unidadeRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("A unidade com ID " + idObjeto + " não foi encontrado!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(unidadeMapper.converterParaDto(unidade)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {

        unidadeRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("A Unidade com ID " + idObjeto + " não foi encontrado!"));

        unidadeRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("A Unidade foi excluído com sucesso."));
    }
}
