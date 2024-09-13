package com.br.Sac.service.impl;


import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.TipoAtendimento;
import com.br.Sac.model.dto.TipoAtendimentoDTO;
import com.br.Sac.repository.TipoAtendimentoRepository;
import com.br.Sac.service.TipoAtendimentoService;
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
public class TipoAtendimentoServiceImpl implements TipoAtendimentoService {

    @Autowired
    private TipoAtendimentoRepository tipoAtendimentoRepository;

    @Autowired
    private CustomObjectMapper<TipoAtendimento, TipoAtendimentoDTO> tipoAtendimentoMapper;

    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {

        TipoAtendimento objeto = tipoAtendimentoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Tipo Atendimento com ID " + idObjeto + " não foi encontrado!"));

        objeto.setStatus(!objeto.isStatus());
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        TipoAtendimento objetoAtualizado = tipoAtendimentoRepository.saveAndFlush(objeto);

        TipoAtendimentoDTO objetoDTO = tipoAtendimentoMapper.converterParaDto(objetoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));

    }

    @Override
    public ResponseEntity<Object> cadastrar(TipoAtendimentoDTO objeto) throws Exception {

        if (tipoAtendimentoRepository.existsByNome(objeto.getNome())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Tipo Atendimento. Já existe outro Tipo Atendimento com o mesmo nome."));
        }

        TipoAtendimento tipoAtendimento = new TipoAtendimento();
        tipoAtendimento.setNome(objeto.getNome());

        TipoAtendimento objetoCriado = tipoAtendimentoRepository.saveAndFlush(tipoAtendimento);
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

        List<TipoAtendimentoDTO> tipoAtendimentoDTOs = tipoAtendimentoMapper.converterParaListaDeDtos(tipoAtendimentoRepository.findAll());
        if(tipoAtendimentoDTOs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Tipo Atendimento cadastrados no Sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(tipoAtendimentoDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, TipoAtendimentoDTO objeto) throws Exception {

        TipoAtendimento dadosDto = tipoAtendimentoMapper.converterParaEntidade(objeto);
        TipoAtendimento paraEditar = tipoAtendimentoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Tipo Atendimento com ID " + idObjeto + " não foi encontrado!"));


        if (tipoAtendimentoRepository.existsByNome(objeto.getNome())){
            return  ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Tipo Atendimento. Já existe outro Tipo Atendimento com o mesmo nome"));
        }

        LocalDateTime dataHora = paraEditar.getDataCriacao();

        dadosDto.setDataCriacao(dataHora);
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        dadosDto.setStatus(dadosDto.isStatus());
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        TipoAtendimento objetoAtualizado = tipoAtendimentoRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(tipoAtendimentoMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        TipoAtendimento tipoAtendimento = tipoAtendimentoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Tipo Atendimento com ID " + idObjeto + " não foi encontrado!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(tipoAtendimentoMapper.converterParaDto(tipoAtendimento)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {


        tipoAtendimentoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Tipo Atendimento com ID " + idObjeto + " não foi encontrado!"));

        tipoAtendimentoRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O Tipo Atendimento foi excluído com sucesso."));
    }
}
