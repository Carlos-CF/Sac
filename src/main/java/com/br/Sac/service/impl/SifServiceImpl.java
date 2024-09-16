package com.br.Sac.service.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.Sif;
import com.br.Sac.model.dto.SifDTO;
import com.br.Sac.repository.SifRepository;
import com.br.Sac.service.SifService;
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
public class SifServiceImpl implements SifService {

    @Autowired
    private SifRepository sifRepository;

    @Autowired
    private CustomObjectMapper<Sif, SifDTO> sifMapper;

    @Override
    public ResponseEntity<Object> cadastrar(SifDTO objeto) throws Exception {

        if (sifRepository.existsByNumero(objeto.getNumero())){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Sif. Já existe outro Sif com o mesmo numero."));
        }

        if (sifRepository.existsByNome(objeto.getNome())){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Sif. Já existe outro Sif com o mesmo nome."));
        }

        Sif sif = new Sif();
        sif.setNumero(objeto.getNumero());
        sif.setNome(objeto.getNome());

        Sif objetoCriado = sifRepository.saveAndFlush(sif);
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

        List<SifDTO> sifDTOs = sifMapper.converterParaListaDeDtos(sifRepository.findAll());
        if (sifDTOs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Sif cadastrados no sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(sifDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, SifDTO objeto) throws Exception {

        Sif dadosDto = sifMapper.converterParaEntidade(objeto);
        Sif paraEditar = sifRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Sif com ID " + idObjeto + " não foi encontrado!"));

        if (sifRepository.existsByNumero(objeto.getNumero())){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Sif. Já existe outro Sif com o mesmo numero."));
        }

        if (sifRepository.existsByNome(objeto.getNome())){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Sif. Já existe outro Sif com o mesmo nome."));
        }

        LocalDateTime dataHora = paraEditar.getDataCriacao();

        dadosDto.setDataCriacao(dataHora);
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        Sif objetoAtualizado = sifRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(sifMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        Sif sif = sifRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Sif com ID " + idObjeto + " não encontrado!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(sifMapper.converterParaDto(sif)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {

        sifRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Sif com ID " + idObjeto + " não foi encontrado!"));

        sifRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O Sif foi excluído com sucesso."));
    }
}
