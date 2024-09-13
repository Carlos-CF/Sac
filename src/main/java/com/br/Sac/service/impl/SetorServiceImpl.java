package com.br.Sac.service.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.Setor;
import com.br.Sac.model.dto.SetorDTO;
import com.br.Sac.repository.SetorRepository;
import com.br.Sac.service.SetorService;
import com.br.Sac.service.util.ApiResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SetorServiceImpl implements SetorService {

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private CustomObjectMapper<Setor, SetorDTO> setorMapper;

    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {

        Setor objeto = setorRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Setor com ID " + idObjeto + " não foi encontrado!"));

        objeto.setStatus(!objeto.isStatus());
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        Setor objetoAtualizado = setorRepository.saveAndFlush(objeto);

        SetorDTO objetoDTO = setorMapper.converterParaDto(objetoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));

    }
    @Override
    public ResponseEntity<Object> cadastrar(SetorDTO objeto) throws Exception {

        if (setorRepository.existsByNome(objeto.getNome())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Setor. Já existe outro Setor com o mesmo nome."));
        }

        Setor setor = new Setor();
        setor.setNome(objeto.getNome());

        Setor objetoCriado = setorRepository.saveAndFlush(setor);
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

        List<SetorDTO> setorDTOs = setorMapper.converterParaListaDeDtos(setorRepository.findAll());
        if(setorDTOs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Setor cadastrados no Sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(setorDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, SetorDTO objeto) throws Exception {

        Setor dadosDto = setorMapper.converterParaEntidade(objeto);
        Setor paraEditar = setorRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Setor com ID " + idObjeto + " não foi encontrado!"));


        if (setorRepository.existsByNome(objeto.getNome())){
            return  ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Setor. Já existe outro Setor com o mesmo nome"));
        }

        LocalDateTime dataHora = paraEditar.getDataCriacao();

        dadosDto.setDataCriacao(dataHora);
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        dadosDto.setStatus(dadosDto.isStatus());
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        Setor objetoAtualizado = setorRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(setorMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        Setor setor = setorRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Setor com ID " + idObjeto + " não foi encontrado!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(setorMapper.converterParaDto(setor)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {

        setorRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Setor com ID " + idObjeto + " não foi encontrado!"));

        setorRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O Setor foi excluído com sucesso."));
    }
}
