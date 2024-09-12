package com.br.Sac.service.impl;

import com.br.Sac.mapper.CustomObjectMapper;
import com.br.Sac.model.TipoContato;
import com.br.Sac.model.dto.TipoContatoDTO;
import com.br.Sac.repository.TipoContatoRepository;
import com.br.Sac.service.TipoContatoService;
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
public class TipoContatoServiceImpl implements TipoContatoService {

    @Autowired
    private TipoContatoRepository tipoContatoRepository;

    @Autowired
    private CustomObjectMapper<TipoContato, TipoContatoDTO> tipoContatoMapper;

    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {

        TipoContato objeto = tipoContatoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Tipo Contato com ID " + idObjeto + " não foi encontrado!"));

        objeto.setStatus(!objeto.isStatus());
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        TipoContato objetoAtualizado = tipoContatoRepository.saveAndFlush(objeto);

        TipoContatoDTO objetoDTO = tipoContatoMapper.converterParaDto(objetoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));

    }

    @Override
    public ResponseEntity<Object> cadastrar(TipoContatoDTO objeto) throws Exception {

        if (tipoContatoRepository.existsByNome(objeto.getNome())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Tipo Contato. Já existe outro Tipo Contato com o mesmo nome."));
        }

        TipoContato tipoContato = new TipoContato();
        tipoContato.setNome(objeto.getNome());

        TipoContato objetoCriado = tipoContatoRepository.saveAndFlush(tipoContato);
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

        List<TipoContatoDTO> tipoContatoDTOS = tipoContatoMapper.converterParaListaDeDtos(tipoContatoRepository.findAll());
        if(tipoContatoDTOS.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Tipo Contato cadastrados no Sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(tipoContatoDTOS));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, TipoContatoDTO objeto) throws Exception {

        TipoContato dadosDto = tipoContatoMapper.converterParaEntidade(objeto);
        TipoContato paraEditar = tipoContatoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Tipo Contato com ID " + idObjeto + " não foi encontrado!"));


        if (tipoContatoRepository.existsByNome(objeto.getNome())){
            return  ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Tipo Contato. Já existe outro Tipo Contato com o mesmo nome"));
        }

        LocalDateTime dataHora = paraEditar.getDataCriacao();

        dadosDto.setDataCriacao(dataHora);
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(dadosDto, paraEditar, "id", "status");
        TipoContato objetoAtualizado = tipoContatoRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(tipoContatoMapper.converterParaDto(objetoAtualizado)));

    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        TipoContato tipoContato = tipoContatoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Tipo Contato com ID " + idObjeto + " não foi encontrado!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(tipoContatoMapper.converterParaDto(tipoContato)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {

        tipoContatoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Tipo Contato com ID " + idObjeto + " não foi encontrado!"));

        tipoContatoRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O Tipo Contato foi excluído com sucesso."));
    }
}
